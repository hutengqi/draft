package cn.sincerity.webservice.serialization;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * CustomerJson
 *
 * @author Ht7_Sincerity
 * @date 2023/6/8
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = CustomFieldSerializer.class)
public @interface CustomJson {


}
