package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.DocumentCreator;
import cn.sincerity.webservice.document.model.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.FieldType;
import cn.sincerity.webservice.document.model.ObjectMeta;
import cn.sincerity.webservice.document.resolver.AbstractMethodResolver;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * ResponseEntityTypeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
public class ResponseEntityTypeGenerator extends AbstractTypeGenerator {
    @Override
    public boolean support(Class<?> clz) {
        return ResponseEntity.class.isAssignableFrom(clz);
    }

    @Override
    public Object generateDefaultValue(ObjectMeta objectMeta) {
        Type type = objectMeta.getType();
        Type argType = ((ParameterizedType) type).getActualTypeArguments()[0];
        Class<?> argClazz = (Class<?>) argType;
        ObjectMeta argMeta = ObjectMeta.of(argClazz, argType);
        Object obj = AbstractMethodResolver.getDefaultValue(argMeta);
        return ResponseEntity.ok(obj);
    }

    @Override
    public void generateApiFields(FieldMeta fieldMeta, List<ApiField> apiFields) {
        Type type = fieldMeta.getType();
        Type argType = ((ParameterizedType) type).getActualTypeArguments()[0];
        Class<?> argClazz = (Class<?>) argType;
        FieldMeta argMeta = FieldMeta.of(argClazz, argType, null, FieldType.DATA);
        AbstractMethodResolver.fillApiFields(argMeta, apiFields);
    }

    @Override
    public int getOrder() {
        return 5;
    }
}
