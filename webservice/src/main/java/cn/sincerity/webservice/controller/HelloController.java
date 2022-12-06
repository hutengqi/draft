package cn.sincerity.webservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description：用于测试的 Controller
 * Create by Ht7_Sincerity on 2021/10/19
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("name")
    public ResponseEntity<String> hello(@RequestParam("名称") String name){
        return ResponseEntity.ok("Hello" + name);
    }
}
