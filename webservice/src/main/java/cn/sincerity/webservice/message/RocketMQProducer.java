package cn.sincerity.webservice.message;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * RocketMQProducer
 *
 * @author Ht7_Sincerity
 * @date 2022/11/13
 */
@Component
public class RocketMQProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void send(String topic, String message) {
        rocketMQTemplate.send(topic, MessageBuilder.withPayload(message).build());
    }
}
