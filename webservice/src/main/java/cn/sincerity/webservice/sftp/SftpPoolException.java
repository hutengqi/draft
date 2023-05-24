package cn.sincerity.webservice.sftp;

import org.springframework.core.NestedRuntimeException;

/**
 * SftpPoolException : Sftp连接池异常
 *
 * @author Ht7_Sincerity
 * @date 2023/5/24
 */
public class SftpPoolException extends NestedRuntimeException {

    public SftpPoolException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
