package cn.sincerity.shardingsphere.controller;


import cn.sincerity.shardingsphere.domain.entity.Order;
import cn.sincerity.shardingsphere.domain.entity.OrderInfo;
import cn.sincerity.shardingsphere.service.OrderInfoService;
import cn.sincerity.shardingsphere.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderInfoService orderInfoService;

    @PostMapping
    public void add() {
        Order order = new Order();
        order.setUserId(1L);
        order.setOrderPrice(100);
        orderService.save(order);
        List<OrderInfo> infos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            OrderInfo info = new OrderInfo();
            info.setOrderId(order.getOrderId());
            info.setShopingName("黄金1号竹子");
            info.setShopingPrice(8888);
            infos.add(info);
        }
        orderInfoService.saveBatch(infos);
    }
}
