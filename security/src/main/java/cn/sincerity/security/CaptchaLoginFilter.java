package cn.sincerity.security;

import cn.sincerity.exception.CaptchaNotMatchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * CaptchaFilter
 *
 * @author Ht7_Sincerity
 * @date 2023/6/30
 */
public class CaptchaLoginFilter extends UsernamePasswordAuthenticationFilter {

    public static final String CAPTCHA_KEY = "captcha";

    private String captcha = CAPTCHA_KEY;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public CaptchaLoginFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST"))
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());

        try {
            Map<String, String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            String captcha = userInfo.get(getCaptcha());
            String sessionCaptcha = (String) request.getSession().getAttribute("captcha");
            if(Objects.equals(captcha, sessionCaptcha)){
                return super.attemptAuthentication(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new CaptchaNotMatchException("验证码错误");
    }
}
