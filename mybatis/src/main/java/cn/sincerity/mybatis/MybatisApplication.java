package cn.sincerity.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

/**
 * MybatisApplication
 *
 * @author Ht7_Sincerity
 * @date 2023/8/10
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.sincerity.mybatis.domain.dao", annotationClass = Repository.class)
public class MybatisApplication {
    public static void main(String[] args) {
       SpringApplication.run(MybatisApplication.class, args);
    }
}