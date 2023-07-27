package cn.sincerity.shardingsphere.controller;


import cn.sincerity.shardingsphere.domain.entity.Shoping;
import cn.sincerity.shardingsphere.service.ShopingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ht7_Sincerity
 * @since 2023-07-27
 */
@RestController
@RequestMapping("/shoping")
public class ShopingController {

    @Resource
    private ShopingService shopingService;

    @GetMapping
    public void insert() {
        List<Shoping> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Shoping shoping = new Shoping();
            shoping.setShopingId((long) i);
            shoping.setShopingName("黄金" + i + "号竹子");
            shoping.setShopingPrice(1111 * i);
            list.add(shoping);
        }
        shopingService.saveBatch(list);
    }
}
