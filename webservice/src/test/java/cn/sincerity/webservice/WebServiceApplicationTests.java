package cn.sincerity.webservice;

import cn.sincerity.webservice.application.HelloApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class WebServiceApplicationTests {

    @Resource
    HelloApplicationService helloApplicationService;

    @Test
    void contextLoads() {
        helloApplicationService.logTest();
    }
}
