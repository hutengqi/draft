package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.ApiField;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * ListTypeValueGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class ListTypeValueGenerator extends AbstractTypeValueGenerator {


    @Override
    boolean support(Class<?> type) {
        return List.class.isAssignableFrom(type);
    }

    @Override
    Object generateDefaultValue(Class<?> clz, Type type) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type argType = parameterizedType.getActualTypeArguments()[0];
        Class<?> argClz = (Class<?>) argType;
        Object element = getDefaultValue(argClz, type);
        return Collections.singletonList(element);
    }

    @Override
    public List<ApiField> generateFields() {
        return null;
    }
}
