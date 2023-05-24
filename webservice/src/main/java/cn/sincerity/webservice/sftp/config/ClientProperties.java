package cn.sincerity.webservice.sftp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClientProperties
 *
 * @author Ht7_Sincerity
 * @date 2023/5/22
 */
@Data
@ConfigurationProperties("sftp")
public class ClientProperties {

    /**
     * SFTP server host.
     */
    private String host = "172.16.3.33";

    /**
     * SFTP server port.
     */
    private int port = 22;

    /**
     * Login username of the sftp server.
     */
    private String username = "root";

    /**
     * Whether to enable host key login.
     */
    private boolean strictHostKeyChecking = false;
    /**
     * host key.
     */
    private String keyPath;

    /**
     * Login password or host key passphrase of the sftp server.
     */
    private String password = "hqecsHqMain-(123";

    /**
     * Specifies the timeout period for new session creation, in milliseconds.
     */
    private int connectTimeout = 0;

    /**
     * SSH kex algorithms.
     */
    private String kex;

    /**
     * Enable jsch log, Cannot be individually turned on or off for one of multiple hosts.
     */
    private boolean enabledLog = false;
}
