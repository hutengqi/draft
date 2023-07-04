package cn.sincerity.security;

import cn.sincerity.domain.Account;
import cn.sincerity.domain.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * CustomUserDetailService: 自定义用户信息获取服务
 *
 * @author Ht7_Sincerity
 * @date 2023/6/21
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Resource
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            return LoginAccount.builder()
                    .account(account)
                    .build();
        }
        return null;
    }
}
