package cn.sincerity.shardingsphere;

import cn.sincerity.shardingsphere.domain.entity.Shoping;
import cn.sincerity.shardingsphere.service.ShopingService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * ShardingSphereApplicationTests
 *
 * @author Ht7_Sincerity
 * @date 2023/7/27
 */
@SpringBootTest
public class ShardingSphereApplicationTests {

    @Resource
    private ShopingService shopingService;

    // 测试数据插入的方法
    @Test
    void insertSelective() {
        Shoping shoping = new Shoping();
        shoping.setShopingId(11111111L);
        shoping.setShopingName("黄金零号竹子");
        shoping.setShopingPrice(8888);
        shopingService.save(shoping);
    }
}
