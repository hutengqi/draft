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
@TableName("order_info")
@ApiModel(value = "OrderInfo对象", description = "")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单详情号")
    private Long orderInfoId;

    @ApiModelProperty("订单号")
    private Long orderId;

    @ApiModelProperty("商品名称")
    private String shopingName;

    @ApiModelProperty("商品价格")
    private Integer shopingPrice;

    public Long getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Long orderInfoId) {
        this.orderInfoId = orderInfoId;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
        return "OrderInfo{" +
            "orderInfoId=" + orderInfoId +
            ", orderId=" + orderId +
            ", shopingName=" + shopingName +
            ", shopingPrice=" + shopingPrice +
        "}";
    }
}
