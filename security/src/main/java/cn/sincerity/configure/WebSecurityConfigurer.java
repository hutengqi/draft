package cn.sincerity.configure;

import cn.sincerity.security.CaptchaLoginFilter;
import cn.sincerity.security.CustomAuthenticationFailureHandler;
import cn.sincerity.security.CustomAuthenticationSuccessHandler;
import cn.sincerity.security.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .mvcMatchers("/login.html").permitAll()
                .mvcMatchers("/captcha.png").permitAll()
                .mvcMatchers("/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/hello")
                .loginProcessingUrl("/doLogin")
                //.successHandler(new CustomAuthenticationSuccessHandler())
                //.failureUrl("/login.html")
                //.failureForwardUrl("/login.html")
                .failureHandler(new CustomAuthenticationFailureHandler())
                .and()
                .logout()
                //.logoutUrl("/logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                //.logoutSuccessUrl("/login.html")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .and()
                .csrf().disable();

        http.addFilterAt(captchaLoginFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CaptchaLoginFilter captchaLoginFilter(AuthenticationManager authenticationManager) {
        CaptchaLoginFilter captchaLoginFilter = new CaptchaLoginFilter(authenticationManager);
        captchaLoginFilter.setFilterProcessesUrl("/doLogin");
        captchaLoginFilter.setUsernameParameter("username");
        captchaLoginFilter.setPasswordParameter("password");
        captchaLoginFilter.setCaptcha("captcha");
        captchaLoginFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
        captchaLoginFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
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
