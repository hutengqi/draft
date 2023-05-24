package cn.sincerity.webservice.sftp;

import cn.sincerity.webservice.sftp.config.ClientProperties;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * SftpClient: Sftp 连接对象
 *
 * @author Ht7_Sincerity
 * @date 2023/5/19
 */
@Slf4j
public class SftpClient {

    /**
     * Sftp 通道对象
     */
    private final ChannelSftp channelSftp;

    /**
     * Sftp 会话对象
     */
    private final Session session;

    /**
     * 连接建立时的原始目录
     */
    private final String originalDir;



    public SftpClient(ClientProperties clientProperties) {
        try {
            JSch jSch = new JSch();
            session = jSch.getSession(clientProperties.getUsername(), clientProperties.getHost(), clientProperties.getPort());
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(clientProperties.getPassword());
            if (StringUtils.hasText(clientProperties.getKex())) {
                session.setConfig("kex", clientProperties.getKex());
            }
            session.connect(clientProperties.getConnectTimeout());
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            originalDir = channelSftp.pwd();
        } catch (Exception e) {
            disconnect();
            throw new IllegalStateException("failed to create sftp Client", e);
        }
    }

    /**
     * 断开 Sftp 连接
     */
    protected final void disconnect() {
        if (session != null)
            session.disconnect();

    }

    /**
     * 测试连接
     *
     * @return 连接是否成功
     */
    protected boolean test() {
        try {
            if (channelSftp.isConnected() && originalDir.equals(channelSftp.pwd())) {
                channelSftp.lstat(originalDir);
                return true;
            }
        } catch (Exception e) {
            log.info("SftpClient: test failed with exception: {}", e.getMessage());
        }
        return false;
    }

    /**
     * 重置连接
     *
     * @return 重置是否成功
     */
    protected boolean reset() {
        try {
            channelSftp.cd(originalDir);
            return true;
        } catch (Exception e) {
            log.info("SftpClient: reset failed with exception: {}", e.getMessage());
        }
        return false;
    }

    public ChannelSftp getChannelSftp() {
        return channelSftp;
    }
}
