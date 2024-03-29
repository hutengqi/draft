package cn.sincerity.webservice.controller;

import cn.sincerity.webservice.document.annotation.ApiDoc;
import cn.sincerity.webservice.domian.CustomJsonObject;
import cn.sincerity.webservice.domian.Family;
import cn.sincerity.webservice.domian.Query;
import cn.sincerity.webservice.sftp.SftpTemplate;
import com.jcraft.jsch.SftpException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Description：用于测试的 Controller
 * Create by Ht7_Sincerity on 2021/10/19
 */
@Slf4j
@Api
@RestController
@ApiDoc(group = "文档测试控制器")
@RequestMapping("hello")
public class HelloController {

    @Resource
    private SftpTemplate sftpTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("hello")
    @GetMapping(value = "name")
    public ResponseEntity<String> hello(@ApiParam("名称") @RequestParam("name") String name) {
        return ResponseEntity.ok("Hello " + name);
    }

    @ApiOperation("query")
    @GetMapping(value = "query")
    public ResponseEntity<Long> query(@Valid @RequestBody Query query) {
        return ResponseEntity.ok(query.getId());
    }

    @ApiOperation("json")
    @GetMapping(value = "json")
    public ResponseEntity<CustomJsonObject> json() {
        CustomJsonObject json = new CustomJsonObject();
        return ResponseEntity.ok(json);
    }

    @ApiOperation("family")
    @GetMapping(value = "family")
    public void family(@RequestBody Family family) {

    }

    @GetMapping("cache")
    public void cache(@RequestParam("key") String key, @RequestParam("value") Long value) {
        redisTemplate.opsForValue().set(key, value);
    }


    @GetMapping(value = "sftp")
    public ResponseEntity<String> sftp() {
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
