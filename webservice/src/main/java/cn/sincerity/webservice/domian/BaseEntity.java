package cn.sincerity.webservice.domian;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * BeseEntity
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
@Data
@NoArgsConstructor
public class BaseEntity {

    @NotNull(message = "主健不能为空")
    private Long id;
}
