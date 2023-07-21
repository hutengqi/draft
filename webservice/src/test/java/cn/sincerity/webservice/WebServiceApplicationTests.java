package cn.sincerity.webservice;

import cn.hutool.core.util.ReflectUtil;
import cn.sincerity.webservice.application.HelloApplicationService;
import cn.sincerity.webservice.controller.HelloController;
import cn.sincerity.webservice.document.resolver.generator.AbstractTypeGenerator;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@SpringBootTest
class WebServiceApplicationTests {

    @Resource
    HelloApplicationService helloApplicationService;

    @Test
    void contextLoads() {
        helloApplicationService.logTest();
    }

    @Test
    void annotation(){

    }
}
