package cn.sincerity.webservice.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * TransactionTopicConsumer
 *
 * @author Ht7_Sincerity
 * @date 2022/11/13
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "transaction_topic", consumerGroup = "transaction_consumer")
public class TransactionTopicConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("[SINCERITY] 收到事务消息：{}", message);
    }
}
