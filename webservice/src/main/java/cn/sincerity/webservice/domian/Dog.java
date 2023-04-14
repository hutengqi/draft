package cn.sincerity.webservice.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * Dog
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Dog extends BaseEntity {

    @NotBlank(message = "狗可以奔跑")
    private String run;
}
