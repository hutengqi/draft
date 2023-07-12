package cn.sincerity.webservice.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User
 *
 * @author Ht7_Sincerity
 * @date 2022/11/13
 */
@Data
public class User {

    @ApiModelProperty("用户主键")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @JsonIgnore
    private String password;

    @ApiModelProperty("用户姓名")
    private String name;
}
