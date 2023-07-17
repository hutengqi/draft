package cn.sincerity.webservice.document.resolver.generator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

import static cn.sincerity.webservice.document.resolver.AbstractApiResolver.HEAD_GENERATOR;

/**
 * MapTypeValueGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class MapTypeGenerator extends AbstractTypeGenerator {


    @Override
    boolean judge(Class<?> clz) {
        return Map.class.isAssignableFrom(clz);
    }

    @Override
    Object generateDefaultValue(Class<?> clz, Type type) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Type keyType = actualTypeArguments[0];
        Class<?> keyClz = super.getClassFromType(keyType);
        Type valueType = actualTypeArguments[1];
        Class<?> valueClz = super.getClassFromType(valueType);
        Object key = HEAD_GENERATOR.getDefaultValue(keyClz, keyType);
        Object value = HEAD_GENERATOR.getDefaultValue(valueClz, valueType);
        return Collections.singletonMap(key, value);
    }
}
