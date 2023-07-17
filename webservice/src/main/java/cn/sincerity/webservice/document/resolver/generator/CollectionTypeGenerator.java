package cn.sincerity.webservice.document.resolver.generator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * ListTypeValueGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class CollectionTypeGenerator extends AbstractTypeGenerator {

    @Override
    boolean judge(Class<?> clz) {
        return Collection.class.isAssignableFrom(clz);
    }

    @Override
    Object generateDefaultValue(Class<?> clz, Type type) {

        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type argType = parameterizedType.getActualTypeArguments()[0];
        Class<?> argClz = super.getClassFromType(argType);
        Object element = getDefaultValue(argClz, argType);

        if (List.class.isAssignableFrom(clz))
            return Collections.singletonList(element);

        return Collections.singleton(element);
    }
}
