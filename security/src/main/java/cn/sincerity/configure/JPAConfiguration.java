package cn.sincerity.configure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * JPAConfiguration
 *
 * @author Ht7_Sincerity
 * @date 2023/6/21
 */
@Configuration
@EntityScan(basePackages = {"cn.sincerity.domain"})
public class JPAConfiguration {
}
