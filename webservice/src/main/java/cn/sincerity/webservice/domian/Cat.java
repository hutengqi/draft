package cn.sincerity.webservice.domian;

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

    private String run;
}
