package cn.sincerity.configure;

import cn.sincerity.security.CaptchaLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurityConfigurer: 自定义 Spring Security 配置
 *
 * @author Ht7_Sincerity
 * @date 2023/6/9
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .rememberMe()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
                .csrf().disable();

        //.mvcMatchers("/login.html").permitAll()
        //.mvcMatchers("/captcha.png").permitAll()
        //.loginPage("/login.html")
        //.loginProcessingUrl("/doLogin")
        //.successForwardUrl("/hello")
        //.failureForwardUrl("/login.html")
        //.logoutUrl("/logout")
        //.failureHandler(new CustomAuthenticationFailureHandler())
        // .logoutSuccessUrl("/login.html")
        //.logoutSuccessHandler(new CustomLogoutSuccessHandler())
        // http.addFilterAt(captchaLoginFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CaptchaLoginFilter captchaLoginFilter(AuthenticationManager authenticationManager) {
        CaptchaLoginFilter captchaLoginFilter = new CaptchaLoginFilter(authenticationManager);
        captchaLoginFilter.setFilterProcessesUrl("/doLogin");
        captchaLoginFilter.setUsernameParameter("username");
        captchaLoginFilter.setPasswordParameter("password");
        captchaLoginFilter.setCaptcha("captcha");
        return captchaLoginFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
