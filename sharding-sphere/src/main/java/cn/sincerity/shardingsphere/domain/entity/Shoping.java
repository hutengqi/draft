package cn.sincerity.shardingsphere.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ht7_Sincerity
 * @since 2023-07-27
 */
@TableName("shoping")
@ApiModel(value = "Shoping对象", description = "")
public class Shoping implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    private Long shopingId;

    @ApiModelProperty("商品名称")
    private String shopingName;

    @ApiModelProperty("商品价格")
    private Integer shopingPrice;

    public Long getShopingId() {
        return shopingId;
    }

    public void setShopingId(Long shopingId) {
        this.shopingId = shopingId;
    }
    public String getShopingName() {
        return shopingName;
    }

    public void setShopingName(String shopingName) {
        this.shopingName = shopingName;
    }
    public Integer getShopingPrice() {
        return shopingPrice;
    }

    public void setShopingPrice(Integer shopingPrice) {
        this.shopingPrice = shopingPrice;
    }

    @Override
    public String toString() {
        return "Shoping{" +
            "shopingId=" + shopingId +
            ", shopingName=" + shopingName +
            ", shopingPrice=" + shopingPrice +
        "}";
    }
}
