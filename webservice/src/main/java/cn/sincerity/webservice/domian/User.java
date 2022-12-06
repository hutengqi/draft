package cn.sincerity.webservice.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * User
 *
 * @author Ht7_Sincerity
 * @date 2022/11/13
 */
@Data
public class User {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String name;
}
