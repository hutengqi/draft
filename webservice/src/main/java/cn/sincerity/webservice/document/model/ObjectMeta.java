package cn.sincerity.webservice.document.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

/**
 * ObjectType: 元素类型信息
 *
 * @author Ht7_Sincerity
 * @date 2023/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectMeta {

    private Class<?> clazz;

    private Type type;

    public static ObjectMeta of(Class<?> clazz, Type type) {
        ObjectMeta meta = new ObjectMeta();
        meta.clazz = clazz;
        meta.type = type;
        return meta;
    }
}
