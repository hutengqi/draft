package cn.sincerity.webservice.domian;

import lombok.Data;

import javax.validation.Valid;

/**
 * Family
 *
 * @author Ht7_Sincerity
 * @date 2023/4/10
 */
@Data
public class Family {

    @Valid
    private Cat cat;

    @Valid
    private Dog dog;
}
