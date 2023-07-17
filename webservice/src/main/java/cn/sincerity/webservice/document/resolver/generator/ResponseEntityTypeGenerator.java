package cn.sincerity.webservice.document.resolver.generator;

import org.springframework.http.ResponseEntity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ResponseEntityTypeValueGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class ResponseEntityTypeGenerator extends AbstractTypeGenerator {

    @Override
    boolean judge(Class<?> clz) {
        return ResponseEntity.class.isAssignableFrom(clz);
    }

    @Override
    Object generateDefaultValue(Class<?> clz, Type type) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type argType = parameterizedType.getActualTypeArguments()[0];
        Class<?> argClz = (Class<?>) argType;
        Object object = getDefaultValue(argClz, type);
        return ResponseEntity.ok(object);
    }
}
