package cn.sincerity.webservice.document.model;

import lombok.Data;

import java.lang.reflect.Type;

/**
 * ObjectMeta: 元素的类型信息
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
@Data
public class ObjectMeta {

    private Class<?> clazz;

    private Type type;

    public ObjectMeta() {
    }

    public ObjectMeta(Class<?> clazz, Type type) {
        this.clazz = clazz;
        this.type = type;
    }

    public static ObjectMeta of(Class<?> clazz, Type type) {
        ObjectMeta meta = new ObjectMeta();
        meta.clazz = clazz;
        meta.type = type;
        return meta;
    }
}
