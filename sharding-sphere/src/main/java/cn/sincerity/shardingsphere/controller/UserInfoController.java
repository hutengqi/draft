package cn.sincerity.shardingsphere.controller;


import cn.sincerity.shardingsphere.domain.entity.UserInfo;
import cn.sincerity.shardingsphere.service.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ht7_Sincerity
 * @since 2023-07-27
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping
    public void add() {
        for (int i = 0; i < 3; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("竹子" + i + "号");
            userInfo.setUserSex("男");
            userInfo.setUserAge(18 + i);
            userInfoService.save(userInfo);
        }
        for (int i = 0; i < 2; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("兔子" + i + "号");
            userInfo.setUserSex("女");
            userInfo.setUserAge(18 + i);
            userInfoService.save(userInfo);
        }
    }
}
