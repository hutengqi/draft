package cn.sincerity.webservice.domian;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Cat
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cat extends BaseEntity {

    @ApiModelProperty("猫的奔跑")
    private String run;
}
