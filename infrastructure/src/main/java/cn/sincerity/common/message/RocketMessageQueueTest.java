package cn.sincerity.common.message;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * RocketMQ 练习
 *
 * @author Ht7_Sincerity
 * @date 2022/10/29
 */
public class RocketMessageQueueTest {

    public static final String PRODUCER_GROUP_NAME = "sincerity_producer";

    public static final String CONSUMER_GROUP_NAME = "sincerity_consumer";

    public static final String MESSAGE_TOPIC = "sincerity";

    public static final String ORDER_TOPIC = "TopicTest";

    public static final String DELAY_TOPIC = "delay_topic";

    public static final String TRANSACTION_TOPIC = "transaction_topic";

    public static final String NAME_SRV_ADDR = "127.0.0.1:9876";

    public static void main(String[] args) {
//        DefaultMQProducer producer = initProducer();
//        try {
//            producer.start();
//            // 普通消息发送
////            syncSend(producer);
////            asyncSend(producer);
////            oneWaySend(producer);
//            // 顺序消息发送
////            orderlySend(producer);
//            // 延迟消息发送
//            delayMessageSend(producer);
//            // 批量消息发送
//            batchSend(producer);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            producer.shutdown();
//        }
        // 事务消息发送
        transactionMessageSend();
//        DefaultMQPushConsumer consumer = initPushConsumer();
//        pushConsumer(consumer, TRANSACTION_TOPIC);
//        pushOrderlyConsumer(consumer);
    }

    static void pushOrderlyConsumer(DefaultMQPushConsumer consumer) {
        try {
            consumer.subscribe(ORDER_TOPIC, "*");
            consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
                String threadName = Thread.currentThread().getName();
                msgs.forEach(messageExt -> {
                    byte[] body = messageExt.getBody();
                    String messageBody = new String(body, StandardCharsets.UTF_8);
                    System.out.printf("[%s] 接收到新消息：Tag:[%s] %s %n", threadName, messageExt.getTags(), messageBody);

                });
                return ConsumeOrderlyStatus.SUCCESS;
            });
            System.out.println("开始顺序消费消息");
            consumer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }

    static void pushConsumer(DefaultMQPushConsumer consumer, String topic) {
        try {
            consumer.subscribe(topic, "*");
            consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                String threadName = Thread.currentThread().getName();
                list.forEach(messageExt -> {
                    byte[] body = messageExt.getBody();
                    String messageBody = new String(body, StandardCharsets.UTF_8);
                    System.out.printf("[%s] 接收到新消息：Tag:[%s] %s %n", threadName, messageExt.getTags(), messageBody);
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            System.out.println("----------------------开始消费消息----------------------");
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    static void transactionMessageSend() {
        TransactionMQProducer producer = new TransactionMQProducer(PRODUCER_GROUP_NAME);
        producer.setNamesrvAddr(NAME_SRV_ADDR);
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("sincerity-transaction-msg-check-tread");
                    return thread;
                });
        producer.setExecutorService(executorService);
        producer.setTransactionListener(new SincerityTransactionListener());
        try {
            producer.start();
            for (int i = 0; i < 10; i++) {
                Message message = new Message(TRANSACTION_TOPIC, ("sincerity_transaction_message" + i).getBytes(StandardCharsets.UTF_8));
                message.setTransactionId(String.valueOf(i));
                TransactionSendResult sendResult = producer.sendMessageInTransaction(message, null);
                System.out.println("半事务消息 " + i + " 发送成功");
                Thread.sleep(10);
            }

            for (int i = 0; i < 100000; i++) {
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }

    static void batchSend(DefaultMQProducer producer) throws Exception {
        List<Message> messages = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            Message message = new Message(MESSAGE_TOPIC, "Batch", ("sincerity_batch_message" + i).getBytes(StandardCharsets.UTF_8));
            messages.add(message);
        }
        RocketMessageSplitter splitter = new RocketMessageSplitter(messages, 1024);
        while (splitter.hasNext()) {
            producer.send(splitter.next());
            System.out.println("一批消息发送中...");
        }

    }

    static void delayMessageSend(DefaultMQProducer producer) throws Exception {
        String[] delayDurations = {"1s", "5s", "10s", "30s"};
        for (int i = 0; i < 40; i++) {
            int level = i % delayDurations.length;
            Message msg = new Message(DELAY_TOPIC, delayDurations[level], ("sincerity_delay_message" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            msg.setDelayTimeLevel(level + 1);
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
    }

    static void orderlySend(DefaultMQProducer producer) throws Exception {
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD"};
        for (int i = 0; i < 40; i++) {
            int orderId = i % 4;
            Message msg = new Message(
                    ORDER_TOPIC,
                    tags[i % tags.length],
                    "KEY" + i,
                    ("sincerity_orderly_message" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, (mqs, msg1, arg) -> {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, orderId);
            System.out.printf("%s%n", sendResult);
        }
    }

    /**
     * 同步消息发送
     */
    static void syncSend(DefaultMQProducer producer) throws Exception {
        for (int i = 0; i < 100; i++) {
            Message message = new Message(MESSAGE_TOPIC, ("sincerity_sync_message: " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(message);
            System.out.printf("%s%n", result);
        }
    }

    /**
     * 异步消息发送
     */
    static void asyncSend(DefaultMQProducer producer) throws Exception {
        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 100; i++) {
            Message message = new Message(MESSAGE_TOPIC, ("sincerity_async_message: " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%s%n", sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.print("[异步发送失败]");
                    throwable.printStackTrace();

                }
            });

        }
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * 单向消息发送（无 ACK 回应）
     */
    static void oneWaySend(DefaultMQProducer producer) throws Exception {
        for (int i = 0; i < 100; i++) {
            Message message = new Message(MESSAGE_TOPIC, ("sincerity_one_way_message: " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(message);
        }
    }

    /**
     * 初始化生产者对象
     *
     * @return DefaultMQProducer
     */
    static DefaultMQProducer initProducer() {
        // 初始化一个 producer 并设置 Producer group name
        DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP_NAME);
        // 设置 NameServer 地址
        producer.setNamesrvAddr(NAME_SRV_ADDR);
        return producer;
    }

    static DefaultMQPushConsumer initPushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CONSUMER_GROUP_NAME);
        consumer.setNamesrvAddr(NAME_SRV_ADDR);
        return consumer;
    }
}
