package cn.sincerity.shardingsphere.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_order")
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty("订单号")
    private Long orderId;

    @ApiModelProperty("订单总金额")
    private Integer orderPrice;

    @ApiModelProperty("用戶id")
    private Long userId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
            "orderId=" + orderId +
            ", orderPrice=" + orderPrice +
            ", userId=" + userId +
        "}";
    }
}
