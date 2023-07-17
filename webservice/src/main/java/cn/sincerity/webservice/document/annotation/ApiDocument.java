package cn.sincerity.webservice.document.annotation;

import java.lang.annotation.*;

/**
 * ApiDocument
 *
 * @author Ht7_Sincerity
 * @date 2023/7/7
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiDocument {


    String group();
}
