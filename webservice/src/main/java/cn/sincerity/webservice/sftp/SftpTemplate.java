package cn.sincerity.webservice.sftp;

import com.jcraft.jsch.SftpException;
import org.springframework.util.Assert;

import java.io.InputStream;

/**
 * SftpTemplate
 *
 * @author Ht7_Sincerity
 * @date 2023/5/24
 */
public class SftpTemplate {

    private final SftpPool sftpPool;

    public SftpTemplate(SftpPool sftpPool) {
        this.sftpPool = sftpPool;
    }

    public <T> T execute(SftpCallback<T> action) throws SftpException {
        Assert.notNull(action, "Callback object must not be null");
        SftpClient sftpClient = null;
        try {
            sftpClient = sftpPool.borrowObject();
            return action.doInSftp(sftpClient.getChannelSftp());
        } finally {
            if (sftpClient != null) {
                if (sftpClient.reset()) {
                    sftpPool.returnObject(sftpClient);
                } else {
                    sftpPool.invalidateObject(sftpClient);
                }
            }
        }
    }

    public String uploadFromLocal(String prefix, String outPath, String fileName, String localPath) throws SftpException {
        return this.execute(channelSftp -> new SftpWrapper(channelSftp)
                .uploadFromLocal(prefix, outPath, fileName, localPath));
    }

    public String uploadFromHttp(String prefix, String outPath, String fileName, String httpUrl) throws SftpException {
        return this.execute(channelSftp -> new SftpWrapper(channelSftp)
                .uploadFromHttp(prefix, outPath, fileName, httpUrl));
    }

    public InputStream download(String prefix, String dirPath, String fileName) throws SftpException {
        return this.execute(channelSftp -> new SftpWrapper(channelSftp)
                .download(prefix, dirPath, fileName));
    }
}
