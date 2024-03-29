package cn.sincerity.shardingsphere.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("user_info")
@ApiModel(value = "UserInfo对象", description = "")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty("用戶id")
    private Long userId;

    @ApiModelProperty("用戶姓名")
    private String userName;

    @ApiModelProperty("用戶性別")
    private String userSex;

    @ApiModelProperty("用戶年齡")
    private Integer userAge;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "userId=" + userId +
            ", userName=" + userName +
            ", userSex=" + userSex +
            ", userAge=" + userAge +
        "}";
    }
}
