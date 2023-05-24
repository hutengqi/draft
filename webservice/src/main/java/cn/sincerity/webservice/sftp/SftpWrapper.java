package cn.sincerity.webservice.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import jodd.util.StringUtil;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * SftpWrapper: Sftp 操作包装类
 *
 * @author Ht7_Sincerity
 * @date 2023/5/24
 */
public class SftpWrapper {

    private static final String SEPARATOR = "/";

    private final ChannelSftp channelSftp;

    public SftpWrapper(ChannelSftp channelSftp) {
        this.channelSftp = channelSftp;
    }

    public InputStream download(String prefix, String dirPath, String fileName) {
        if (StringUtil.isBlank(dirPath)) {
            throw new IllegalStateException("sftp 文件目录地址为空！");
        }
        if (StringUtil.isBlank(fileName)) {
            throw new IllegalStateException("sftp 文件名为空！");
        }
        InputStream inputStream;
        try {
            channelSftp.cd(prefix + SEPARATOR + dirPath);
            inputStream = channelSftp.get(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("下载 sftp 文件出错，请稍后重试");
        }
        return inputStream;
    }

    public String uploadFromLocal(String prefix, String outPath, String fileName, String localPath) {
        File file = new File(localPath);
        if (!file.exists()) {
            throw new IllegalStateException("文件不存在！");
        }
        String sftpUrl;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            sftpUrl = upload(prefix, outPath, fileName, fileInputStream);
            Files.delete(Paths.get(localPath));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("上传本地文件到Sftp失败！");

        }
        return sftpUrl;
    }

    public String uploadFromHttp(String prefix, String outPath, String fileName, String httpUrl) {
        String sftpUrl;
        try {
            URL url = new URL(httpUrl);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            sftpUrl = upload(prefix, outPath, fileName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("获取远程文件失败！");
        }
        return sftpUrl;
    }

    public String upload(String prefix, String dirPath, String fileName, InputStream inputStream) {
        if (StringUtil.isBlank(dirPath)) {
            throw new IllegalStateException("sftp 文件目录地址为空！");
        }
        if (StringUtil.isBlank(fileName)) {
            throw new IllegalStateException("sftp 文件名为空！");
        }
        String path = prefix + SEPARATOR + dirPath;
        try {
            cdAndMkdir(path);
            channelSftp.put(inputStream, fileName);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("上传 sftp 文件出错，请稍后重试");
        }
        return dirPath + SEPARATOR + fileName;
    }


    public final void cdAndMkdir(String path) throws Exception {
        Assert.hasLength(path, "path must not be null");
        try {
            cd(path);
        } catch (SftpException e) {
            if (e.id != ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                throw new SftpException(e.id, "failed to change remote directory '" + path + "'." + e.getMessage(), e.getCause());
            }
            if (path.startsWith(SEPARATOR)) {
                cd(SEPARATOR);
            }
            String[] dirs = path.split(SEPARATOR);
            for (String dir : dirs) {
                if ("".equals(dir)) {
                    continue;
                } else if (!isDir(dir)) {
                    mkdir(dir);
                }
                cd(dir);
            }
        }
    }

    public void cd(String path) throws SftpException {
        try {
            channelSftp.cd(path);
        } catch (SftpException e) {
            throw new SftpException(e.id, "failed to change remote directory '" + path + "'." + e.getMessage(), e.getCause());
        }
    }

    public boolean isDir(String dir) throws SftpException {
        try {
            return channelSftp.lstat(dir).isDir();
        } catch (SftpException e) {
            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                return false;
            }
            throw new SftpException(e.id, "cannot check status for dir '" + dir + "'." + e.getMessage(), e.getCause());
        }
    }

    public void mkdir(String path) throws SftpException {
        try {
            this.channelSftp.mkdir(path);
        } catch (SftpException e) {
            if (e.id != ChannelSftp.SSH_FX_FAILURE || !exists(path)) {
                throw new SftpException(e.id, "failed to create remote directory '" + path + "'." + e.getMessage(), e.getCause());
            }
        }
    }

    public boolean exists(String path) throws SftpException {
        try {
            this.channelSftp.lstat(path);
            return true;
        } catch (SftpException e) {
            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                return false;
            }
            throw new SftpException(e.id, "cannot check status for path '" + path + "'." + e.getMessage(), e.getCause());
        }
    }
}
