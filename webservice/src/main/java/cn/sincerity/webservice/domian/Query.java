package cn.sincerity.webservice.domian;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Query
 *
 * @author Ht7_Sincerity
 * @date 2023/5/9
 */
@Data
public class Query {

    @NotNull(message = "id 不能为空")
    private Long id;
}
