package cn.sincerity.webservice.document;

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
public @interface ApiRemark {

    String remark();
}