package cn.sincerity.common.message;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义 RocketMQ 的事务监听器
 *
 * @author Ht7_Sincerity
 * @date 2022/11/10
 */
public class SincerityTransactionListener implements TransactionListener {

    private final AtomicInteger transactionIndex = new AtomicInteger(0);

    private final ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String transactionId = msg.getTransactionId();
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(transactionId, status);
        System.out.printf("[TransactionId:%s, Status:%s] 执行本地事务 %n", transactionId, status);
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        String transactionId = msg.getTransactionId();
        Integer status = localTrans.get(transactionId);
        System.out.printf("[TransactionId:%s, Status:%s] 检查本地事务 %n", transactionId, status);
        if (status != null) {
            switch (status) {
                case 0:
                    return LocalTransactionState.UNKNOW;
                case 2:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                default:
                    return LocalTransactionState.COMMIT_MESSAGE;
            }
        }
        return LocalTransactionState.ROLLBACK_MESSAGE;
    }
}
