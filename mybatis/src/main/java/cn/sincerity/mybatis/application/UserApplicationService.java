package cn.sincerity.mybatis.application;

import cn.sincerity.mybatis.domain.dao.UserMapper;
import cn.sincerity.mybatis.domain.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserApplicationService
 *
 * @author Ht7_Sincerity
 * @date 2023/8/10
 */
@Service
public class UserApplicationService {

    @Resource
    private UserMapper userMapper;

    public void getUserById(Long id) {
        User user = userMapper.getUserById(id);
        System.out.println(user.getUserId());
    }
}
