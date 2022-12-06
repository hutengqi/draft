package cn.sincerity.webservice.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * HelloApplicationService
 *
 * @author Ht7_Sincerity
 * @date 2022/11/13
 */
@Slf4j
@Service
public class HelloApplicationService {

    public void logTest(){
        log.info("hello, world");
    }
}
