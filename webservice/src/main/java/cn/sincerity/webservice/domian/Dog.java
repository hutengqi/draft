package cn.sincerity.webservice.domian;

import cn.sincerity.webservice.document.annotation.ApiElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Dog
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dog extends BaseEntity {

    @NotBlank(message = "狗可以奔跑")
    @ApiModelProperty("狗的奔跑")
    private String run;
}
