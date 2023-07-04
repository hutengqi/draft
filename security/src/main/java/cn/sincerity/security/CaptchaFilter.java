package cn.sincerity.security;

import cn.sincerity.exception.CaptchaNotMatchException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CaptchaFilter
 *
 * @author Ht7_Sincerity
 * @date 2023/6/30
 */
public class CaptchaFilter extends UsernamePasswordAuthenticationFilter {

    public static final String CAPTCHA_KEY = "captcha";

    private String captcha = CAPTCHA_KEY;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getMethod().equals("POST"))
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());

        String captcha = request.getParameter(getCaptcha());
        String sessionCaptcha = (String) request.getSession().getAttribute(getCaptcha());
        if (!ObjectUtils.isEmpty(captcha) && captcha.equals(sessionCaptcha))
            return super.attemptAuthentication(request, response);

        throw new CaptchaNotMatchException("验证码错误");
    }
}
