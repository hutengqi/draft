package cn.sincerity.webservice.controller;

import cn.sincerity.webservice.domian.Family;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ValidatorController
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
@RestController
@RequestMapping("valid")
public class ValidatorController {

    @PostMapping("/family")
    public void valid(@Valid @RequestBody Family family) {
        System.out.println("Finished");
    }
}
