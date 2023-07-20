package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.FieldType;
import cn.sincerity.webservice.document.model.ObjectMeta;
import cn.sincerity.webservice.document.resolver.AbstractApiResolver;
import org.springframework.http.ResponseEntity;

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
    public boolean support(Class<?> clz) {
        return ResponseEntity.class.isAssignableFrom(clz);
    }

    @Override
    public Object generateDefaultValue(ObjectMeta objectMeta) {
        ParameterizedType parameterizedType = (ParameterizedType) objectMeta.getType();
        Type argType = parameterizedType.getActualTypeArguments()[0];
        Class<?> argClz = (Class<?>) argType;

        ObjectMeta argMeta = ObjectMeta.of(argClz, argType);
        Object object = AbstractApiResolver.getDefaultValue(argMeta);

        return ResponseEntity.ok(object);
    }

    @Override
    public void generateApiFields(FieldMeta fieldMeta, List<ApiField> apiFields) {
        ParameterizedType parameterizedType = (ParameterizedType) fieldMeta.getType();
        Type argType = parameterizedType.getActualTypeArguments()[0];
        Class<?> argClz = (Class<?>) argType;

        FieldMeta argMeta = FieldMeta.of(argClz, argType, null, FieldType.DATA);
        AbstractApiResolver.fillApiFields(argMeta, apiFields);
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
