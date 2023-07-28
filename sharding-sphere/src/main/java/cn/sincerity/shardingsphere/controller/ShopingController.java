package cn.sincerity.shardingsphere.controller;


import cn.sincerity.shardingsphere.domain.entity.Shoping;
import cn.sincerity.shardingsphere.service.ShopingService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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

    @GetMapping("{id}")
    public Shoping get(@PathVariable Long id) {
        return shopingService.getById(id);
    }

    @GetMapping("all")
    public List<Shoping> all() {
        LambdaQueryWrapper<Shoping> wrapper = Wrappers.<Shoping>lambdaQuery()
                .orderByAsc(Shoping::getShopingId);
        return shopingService.list(wrapper);
    }

    @PostMapping
    public void insert() {
        List<Shoping> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Shoping shoping = new Shoping();
            shoping.setShopingName("黄金" + i + "号竹子");
            shoping.setShopingPrice(1111 * i);
            list.add(shoping);
        }
        shopingService.saveBatch(list);
    }
}
