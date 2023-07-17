package cn.sincerity.webservice.document.resolver.generator;

import cn.hutool.core.util.ReflectUtil;
import cn.sincerity.webservice.document.resolver.AbstractApiResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * CustomTypeValueGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class CustomTypeGenerator extends AbstractTypeGenerator {

    @Override
    boolean judge(Class<?> clz) {
        return true;
    }

    @Override
    public void setNext(AbstractTypeGenerator generator) {
        throw new IllegalArgumentException("this type generator must be the last one.");
    }

    @Override
    Object generateDefaultValue(Class<?> clz, Type type) {
        Object obj;
        try {
            obj = clz.newInstance();
            Field[] fields = ReflectUtil.getFields(clz);
            for (Field field : fields) {
                field.setAccessible(true);
                Class<?> fieldClz = field.getType();
                Type fieldType = field.getGenericType();
                Object fieldValue = AbstractApiResolver.HEAD_GENERATOR.getDefaultValue(fieldClz, fieldType);
                field.set(obj, fieldValue);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        DEFAULT_VALUE_MAP.put(clz, obj);
        return obj;
    }
}
