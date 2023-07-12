package cn.sincerity.webservice.document.param;

import com.alibaba.fastjson.JSON;
import org.springframework.core.Ordered;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.time.chrono.ChronoLocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * AbstractMethodParamResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public abstract class AbstractMethodParamResolver implements MethodParamResolver, Ordered {

    @Override
    public String resolve4Response(Method method) {
        Class<?> returnType = method.getReturnType();
        if (Void.class.isAssignableFrom(returnType)) {
            return null;
        }
        if (primitiveType(returnType)) {
            return returnType.getSimpleName();
        }
        if (List.class.isAssignableFrom(returnType)) {
            ParameterizedType parameterizedType = (ParameterizedType) method.getGenericReturnType();
            Class<?> elementType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
            initFieldValue(elementType);
            return JSON.toJSONString(elementType);
        }
        // 自定义类型
        // List
        // Map
        // 自定义泛型容器
        return null;
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

    protected void initFieldValue(Object value) {
        Class<?> type = value.getClass();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            if (primitiveType(fieldType)) {
                // maybe set default value
            } else {
                try {
                    Object fieldObject;
                    if (List.class.isAssignableFrom(fieldType)) {
                        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                        Class<?> actualTypeArgument = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                        fieldObject = actualTypeArgument.newInstance();
                        field.set(value, Collections.singletonList(fieldObject));
                    } else if (Map.class.isAssignableFrom(fieldType)) {

                        continue;

                    } else {
                        fieldObject = fieldType.newInstance();
                        field.set(value, fieldObject);
                    }
                    initFieldValue(fieldObject);
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
