package cn.sincerity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LoginController
 *
 * @author Ht7_Sincerity
 * @date 2023/6/20
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login.html")
    public String login() {
        return "login";
    }
}
