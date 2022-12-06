package cn.sincerity.webservice.controller;

import cn.sincerity.webservice.message.RocketMQProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * MessageController
 *
 * @author Ht7_Sincerity
 * @date 2022/11/13
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private RocketMQProducer producer;

    @PostMapping
    public void send(@RequestParam(name = "name") String name) {
        producer.send("sincerity", name);
    }
}
