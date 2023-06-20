package cn.sincerity.configure;

import cn.sincerity.security.CustomAuthenticationFailureHandler;
import cn.sincerity.security.CustomAuthenticationSuccessHandler;
import cn.sincerity.security.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                .mvcMatchers("/login.html").permitAll()
                .mvcMatchers("/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                //.successForwardUrl("/index")
                .successHandler(new CustomAuthenticationSuccessHandler())
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
        return http.build();
    }
}
