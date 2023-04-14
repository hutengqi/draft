package cn.sincerity.webservice.domian;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Family
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
@Data
public class Family {

    @Valid
    @NotNull(message = "cat 不能为空")
    private Cat cat;

    @Valid
    @NotEmpty(message = "dogs 不能为空")
    private List<Dog> dogs;
}
