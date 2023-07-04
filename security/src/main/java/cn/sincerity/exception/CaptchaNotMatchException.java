package cn.sincerity.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * CaptchaException
 *
 * @author Ht7_Sincerity
 * @date 2023/6/30
 */
public class CaptchaNotMatchException extends AuthenticationException {

    public CaptchaNotMatchException(String msg) {
        super(msg);
    }

    public CaptchaNotMatchException(String msg, Throwable t) {
        super(msg, t);
    }
}
