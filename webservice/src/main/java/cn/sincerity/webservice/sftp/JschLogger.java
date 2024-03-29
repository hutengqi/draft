package cn.sincerity.webservice.sftp;

import com.jcraft.jsch.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JschLogger: Logger for sftp
 *
 * @author Ht7_Sincerity
 * @date 2023/5/24
 */
public class JschLogger implements Logger {

    private static final Log log = LogFactory.getLog("com.jcraft.jsch");
    private final boolean enabled;

    public JschLogger(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled(int level) {
        if (!enabled) {
            return false;
        }
        switch (level) {
            case Logger.INFO:
                return log.isInfoEnabled();
            case Logger.WARN:
                return log.isWarnEnabled();
            case Logger.DEBUG:
                return log.isDebugEnabled();
            case Logger.ERROR:
                return log.isErrorEnabled();
            case Logger.FATAL:
                return log.isFatalEnabled();
            default:
                return false;
        }
    }

    @Override
    public void log(int level, String message) {
        switch (level) {
            case Logger.INFO:
                log.info(message);
            case Logger.WARN:
                log.warn(message);
            case Logger.DEBUG:
                log.debug(message);
            case Logger.ERROR:
                log.error(message);
            case Logger.FATAL:
                log.fatal(message);
            default:
        }
    }
}
