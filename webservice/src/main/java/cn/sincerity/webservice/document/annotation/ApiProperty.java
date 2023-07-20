package cn.sincerity.webservice.document.annotation;

import java.lang.annotation.*;

/**
 * ApiRemark
 *
 * @author Ht7_Sincerity
 * @date 2023/7/11
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiProperty {

    String remark() default "";

    Class<?> enumClass() default Object.class;

    String codeMethod() default "name";

    String descMethod() default "";
}
