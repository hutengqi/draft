package cn.sincerity.webservice.controller;

import cn.sincerity.webservice.domian.CustomJsonObject;
import cn.sincerity.webservice.domian.Query;
import cn.sincerity.webservice.sftp.SftpTemplate;
import com.jcraft.jsch.SftpException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Description：用于测试的 Controller
 * Create by Ht7_Sincerity on 2021/10/19
 */
@Slf4j
@Api
@RestController
@RequestMapping("hello")
public class HelloController {

    @Resource
    private SftpTemplate sftpTemplate;

    @ApiOperation("hello")
    @GetMapping("name/{name}")
    public ResponseEntity<String> hello(@PathVariable String name) {
        return ResponseEntity.ok("Hello " + name);
    }

    @ApiOperation("query")
    @GetMapping("query")
    public ResponseEntity<Long> query(@Valid @RequestBody Query query) {
        return ResponseEntity.ok(query.getId());
    }

    @ApiOperation("json")
    @GetMapping("json")
    public ResponseEntity<CustomJsonObject> json() {
        CustomJsonObject json = new CustomJsonObject("json");
        return ResponseEntity.ok(json);
    }

    @GetMapping("sftp")
    public ResponseEntity<String> sftp(){
        String url = "https://hq-prd-e-zine.oss-cn-szfinance.aliyuncs.com/agent/uat/product/double/record/sign/e8fd35ea27a04b84a2b5c333f5ccd562.png";
        String path = null;
        try {
            log.info(url);
            path = sftpTemplate.uploadFromHttp("/alidata1/sftp/sincerity", "/test", "sign_test.png", url);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(path);
    }
}
