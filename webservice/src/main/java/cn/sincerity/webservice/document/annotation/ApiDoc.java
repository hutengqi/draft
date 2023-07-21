package cn.sincerity.webservice.document.annotation;

import java.lang.annotation.*;

/**
 * ApiDoc: API 接口文档生成入口注解
 *
 * <p>当前仅用于 @Controller 和 @RestController 的 Http 处理类</p>
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiDoc {

    String group();

    String desc() default "";
}
