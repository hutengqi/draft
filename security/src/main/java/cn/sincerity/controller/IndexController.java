package cn.sincerity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 *
 * @author Ht7_Sincerity
 * @date 2023/6/20
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping
    public String index() {
        return "Index,Security!";
    }

}
