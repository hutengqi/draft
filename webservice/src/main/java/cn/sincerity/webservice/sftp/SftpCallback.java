package cn.sincerity.webservice.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

/**
 * SftpCallback
 *
 * @author Ht7_Sincerity
 * @date 2023/5/24
 */
@FunctionalInterface
public interface SftpCallback<T> {


    T doInSftp(ChannelSftp channelSftp) throws SftpException;
}
