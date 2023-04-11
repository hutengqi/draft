package cn.sincerity.webservice.domian;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * BeseEntity
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
@Data
public class BaseEntity {

    @NotNull(message = "主健不能为空")
    private Long id;
}
