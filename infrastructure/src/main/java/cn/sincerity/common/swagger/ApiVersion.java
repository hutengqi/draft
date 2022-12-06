package cn.sincerity.common.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description：Swagger 版本分组注解
 * Create by Ht7_Sincerity on 2021/10/19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiVersion {

    /**
     * 接口版本号(对应 Swagger 中的 group)
     * @return 接口所属的版本号
     */
    String[] group();
}
