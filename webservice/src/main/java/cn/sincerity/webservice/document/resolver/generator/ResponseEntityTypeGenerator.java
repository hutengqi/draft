package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.resolver.AbstractApiResolver;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

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

    @Override
    public void generateApiFields(Class<?> clz, Type type, Field field, List<ApiField> apiFields, FieldType fieldType) {
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type argType = parameterizedType.getActualTypeArguments()[0];
        Class<?> argClz = (Class<?>) argType;
        AbstractApiResolver.HEAD_GENERATOR.fillApiFields(argClz, argType, null, apiFields, FieldType.DATA);
    }
}
