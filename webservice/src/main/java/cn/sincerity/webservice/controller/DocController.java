package cn.sincerity.webservice.controller;

import cn.sincerity.webservice.document.DocumentCreator;
import cn.sincerity.webservice.document.model.ApiDocument;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * DocController
 *
 * @author Ht7_Sincerity
 * @date 2023/7/7
 */
@RestController
@RequestMapping("/document")
public class DocController {

    @Resource
    private DocumentCreator documentCreator;

    @GetMapping
    public List<ApiDocument> generate() {
      return documentCreator.create();
    }
}
