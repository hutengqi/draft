package cn.sincerity.webservice.controller;

import cn.sincerity.webservice.document.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private DocumentService documentService;

    @GetMapping
    public void generate() {
        documentService.generate();
    }
}
