package cn.sincerity.webservice.document.resolver;

import cn.sincerity.webservice.document.resolver.generator.*;
import com.alibaba.fastjson.JSON;
import org.springframework.core.Ordered;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.time.chrono.ChronoLocalDate;

/**
 * AbstractMethodParamResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public abstract class AbstractApiResolver implements ApiResolver, Ordered {

    public static final AbstractTypeGenerator HEAD_GENERATOR = new BasicTypeGenerator();

    static {
        CollectionTypeGenerator collectionGenerator = new CollectionTypeGenerator();
        HEAD_GENERATOR.setNext(collectionGenerator);
        MapTypeGenerator mapGenerator = new MapTypeGenerator();
        collectionGenerator.setNext(mapGenerator);
        ResponseEntityTypeGenerator responseGenerator = new ResponseEntityTypeGenerator();
        mapGenerator.setNext(responseGenerator);
        CustomTypeGenerator customGenerator = new CustomTypeGenerator();
        responseGenerator.setNext(customGenerator);
    }

    @Override
    public String resolve2Json4Response(Method method) {
        Class<?> returnClz = method.getReturnType();
        Type returnType = method.getGenericReturnType();
        if (Void.class == returnClz || void.class == returnClz) {
            return null;
        }
        Object defaultValue = HEAD_GENERATOR.getDefaultValue(returnClz, returnType);
        return JSON.toJSONString(defaultValue);
    }

    protected boolean supportByAnnotationType(Annotation[][] parameterAnnotations, Class<?> annotationType) {
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                if (annotationType.isInstance(annotation))
                    return true;
            }
        }
        return false;
    }

    protected void checkParamTypesIsEmpty(Parameter[] parameters) {
        if (ObjectUtils.isEmpty(parameters)) {
            throw new IllegalArgumentException("params must be not empty.");
        }
    }

    protected boolean primitiveType(Class<?> type) {
        return type.isPrimitive()
                || CharSequence.class.isAssignableFrom(type)
                || Number.class.isAssignableFrom(type)
                || ChronoLocalDate.class.isAssignableFrom(type)
                || Boolean.class.isAssignableFrom(type);
    }
}
