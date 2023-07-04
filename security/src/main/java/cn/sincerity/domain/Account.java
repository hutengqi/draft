package cn.sincerity.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Account: 账户信息
 *
 * @author Ht7_Sincerity
 * @date 2023/6/21
 */
@Data
@Entity
public class Account {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码只写，不参与序列化。
     * 参与反序列化，但不参与对象编辑，修改密码需要通过单独接口处理。
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
