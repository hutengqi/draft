package cn.sincerity.webservice.domian;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Family
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
@Data
public class Family {

    @Valid
    @ApiModelProperty("猫")
    @NotNull(message = "cat 不能为空")
    private Cat cat;

    @Valid
    @ApiModelProperty("狗")
    @NotEmpty(message = "dogs 不能为空")
    private List<Dog> dogs;

    @ApiModelProperty("用户列表")
    private Map<String, User> userMap;

    public static Family defaultFamily() {
        Family family = new Family();
        Cat cat1 = new Cat();
        cat1.setRun("cat run");
        Dog dog1 = new Dog();
        dog1.setRun("dog run");
        family.setCat(cat1);
        family.setDogs(Collections.singletonList(dog1));
        return family;
    }
}
