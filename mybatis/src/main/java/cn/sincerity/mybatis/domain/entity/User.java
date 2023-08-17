package cn.sincerity.mybatis.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * User
 *
 * @author Ht7_Sincerity
 * @date 2023/8/10
 */
@Data
public class User {

    private Long id;

    private String userId;

    private String userHead;

    private Date createTime;

    private Date updateTime;
}
