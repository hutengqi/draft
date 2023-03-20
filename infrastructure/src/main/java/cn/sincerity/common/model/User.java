package cn.sincerity.common.model;

import java.io.Serializable;

/**
 * User
 *
 * @author Ht7_Sincerity
 * @since 2023/2/26
 */
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer sex;

    private Integer age;

    private String mobile;

    public static User newInstance(Integer param) {
        User user = new User();
        user.id = 100L + param;
        user.name = "小明" + param;
        user.age = 26;
        user.sex = 1;
        user.mobile = "18838292213";
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
