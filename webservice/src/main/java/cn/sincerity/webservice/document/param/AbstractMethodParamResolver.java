package cn.sincerity.webservice.document.param;

import cn.hutool.core.util.ReflectUtil;
import cn.sincerity.webservice.document.PHandler;
import com.alibaba.fastjson.JSON;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * AbstractMethodParamResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public abstract class AbstractMethodParamResolver implements MethodParamResolver, Ordered {

    public static final Map<Class<?>, Object> DEFAULT_VALUE_MAP = new ConcurrentHashMap<>();

    static {
        DEFAULT_VALUE_MAP.put(String.class, "string");
        DEFAULT_VALUE_MAP.put(Byte.class, (byte) 0);
        DEFAULT_VALUE_MAP.put(Short.class, (short) 0);
        DEFAULT_VALUE_MAP.put(Integer.class, 0);
        DEFAULT_VALUE_MAP.put(Long.class, 0L);
        DEFAULT_VALUE_MAP.put(Float.class, 0.00f);
        DEFAULT_VALUE_MAP.put(Double.class, 0.00d);
        DEFAULT_VALUE_MAP.put(BigDecimal.class, new BigDecimal("0.00"));
        DEFAULT_VALUE_MAP.put(Boolean.class, false);
        DEFAULT_VALUE_MAP.put(LocalDate.class, LocalDate.now());
        DEFAULT_VALUE_MAP.put(LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public String resolve4Response(Method method) {
        Class<?> returnClz = method.getReturnType();
        Type returnType = method.getGenericReturnType();
        if (Void.class.isAssignableFrom(returnClz)) {
            return null;
        }
        return JSON.toJSONString(getDefaultValue(returnClz, returnType));
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

    private PHandler pHadnler;

    public Object getDefaultValue(Class<?> clz, Type genericType) {

        if (primitiveType(clz)) {
            return getDefaultValue4Cache(clz, () -> null);
        }

        if (List.class.isAssignableFrom(clz)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type argType = parameterizedType.getActualTypeArguments()[0];
            Class<?> argClz = (Class<?>) argType;
            Object element = getDefaultValue4Cache(argClz, () -> getDefaultValue(argClz, argType));
            return Collections.singletonList(element);
        }

        if (Map.class.isAssignableFrom(clz)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Type keyType = actualTypeArguments[0];
            Class<?> keyClz = (Class<?>) keyType;
            Type valueType = actualTypeArguments[1];
            Class<?> valueClz = (Class<?>) valueType;
            Object key = getDefaultValue4Cache(keyClz, () -> getDefaultValue(keyClz, keyType));
            Object value = getDefaultValue4Cache(valueClz, () -> getDefaultValue(valueClz, valueType));
            return Collections.singletonMap(key, value);
        }

        if (ResponseEntity.class.isAssignableFrom(clz)) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type argType = parameterizedType.getActualTypeArguments()[0];
            Class<?> argClz = (Class<?>) argType;
            Object object = getDefaultValue4Cache(argClz, () -> getDefaultValue(argClz, argType));
            return ResponseEntity.ok(object);
        }

        return getDefaultValue4Cache(clz, () -> {
            Object obj;
            try {
                obj = clz.newInstance();
                Field[] fields = ReflectUtil.getFields(clz);
                for (Field field : fields) {
                    field.setAccessible(true);
                    Class<?> fieldClz = field.getType();
                    Type fieldType = field.getGenericType();
                    Object fieldValue = getDefaultValue4Cache(fieldClz, () -> getDefaultValue(fieldClz, fieldType));
                    field.set(obj, fieldValue);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return obj;
        });
    }

    public Object getDefaultValue4Cache(Class<?> fieldType, Supplier<?> supplier) {
        for (Map.Entry<Class<?>, Object> entry : DEFAULT_VALUE_MAP.entrySet()) {
            if (entry.getKey().isAssignableFrom(fieldType)) {
                return entry.getValue();
            }
        }
        Object newInstance = supplier.get();
        if (newInstance != null)
            DEFAULT_VALUE_MAP.put(fieldType, newInstance);

        return newInstance;
    }

    protected boolean primitiveType(Class<?> type) {
        return type.isPrimitive()
                || CharSequence.class.isAssignableFrom(type)
                || Number.class.isAssignableFrom(type)
                || ChronoLocalDate.class.isAssignableFrom(type)
                || Boolean.class.isAssignableFrom(type);
    }
}
