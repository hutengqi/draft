package cn.sincerity.webservice.document.annotation;

import java.lang.annotation.*;

/**
 * ApiElement
 *
 * @author Ht7_Sincerity
 * @date 2023/7/13
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiElement {

    String name();
}
