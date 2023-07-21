package cn.sincerity.webservice.document.annotation;

import java.lang.annotation.*;

/**
 * ApiProperty
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiProperty {

    String remark() default "";

    Class<?> enumClass() default Object.class;

    String codeMethod() default "name";

    String descMethod() default "";

    String defaultValue() default "";
}
