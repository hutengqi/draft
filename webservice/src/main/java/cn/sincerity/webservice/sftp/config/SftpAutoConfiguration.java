package cn.sincerity.webservice.sftp.config;

import cn.sincerity.webservice.sftp.JschLogger;
import cn.sincerity.webservice.sftp.SftpPool;
import cn.sincerity.webservice.sftp.SftpTemplate;
import com.jcraft.jsch.JSch;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SftpAutoConfiguration
 *
 * @author Ht7_Sincerity
 * @date 2023/5/22
 */
@Configuration
@EnableConfigurationProperties({ClientProperties.class, PoolProperties.class})
public class SftpAutoConfiguration {

    @Bean
    public SftpPool sftpPool(ClientProperties clientProperties, PoolProperties poolProperties) {
        JSch.setLogger(new JschLogger(clientProperties.isEnabledLog()));
        return new SftpPool(clientProperties, poolProperties);
    }


    @Bean
    public SftpTemplate sftpTemplate(SftpPool sftpPool) {
        return new SftpTemplate(sftpPool);
    }
}
