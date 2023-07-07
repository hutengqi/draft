package cn.sincerity.webservice.domian;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Query
 *
 * @author Ht7_Sincerity
 * @date 2023/5/9
 */
@Data
@ApiModel("查询对象")
public class Query {

    @ApiModelProperty("主键")
    @NotNull(message = "id 不能为空")
    private Long id;
}
