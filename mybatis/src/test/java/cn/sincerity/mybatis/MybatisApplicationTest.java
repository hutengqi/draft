package cn.sincerity.mybatis;

import cn.sincerity.mybatis.application.UserApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * MybatisApplicationTest
 *
 * @author Ht7_Sincerity
 * @date 2023/8/10
 */
@SpringBootTest
public class MybatisApplicationTest {

    @Resource
    private UserApplicationService userApplicationService;


    @Test
    void getUserById() {
        userApplicationService.getUserById(1L);
    }
}
