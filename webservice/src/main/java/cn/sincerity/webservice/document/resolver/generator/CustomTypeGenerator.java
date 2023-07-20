package cn.sincerity.webservice.document.resolver.generator;

import cn.hutool.core.util.ReflectUtil;
import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.ObjectMeta;
import cn.sincerity.webservice.document.resolver.AbstractApiResolver;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * CustomTypeValueGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class CustomTypeGenerator extends AbstractTypeGenerator {

    @Override
    public boolean support(Class<?> clz) {
        return true;
    }

    @Override
    public Object generateDefaultValue(ObjectMeta objectMeta) {
        Object obj;
        Class<?> clazz = objectMeta.getClazz();
        try {
            obj = clazz.newInstance();
            Field[] fields = ReflectUtil.getFields(clazz);
            for (Field field : fields) {
                field.setAccessible(true);
                ObjectMeta fieldObjectMeta = ObjectMeta.of(field.getType(), field.getGenericType());
                Object fieldValue = AbstractApiResolver.getDefaultValue(fieldObjectMeta);
                field.set(obj, fieldValue);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        DEFAULT_VALUE_MAP.put(clazz, obj);
        return obj;
    }

    @Override
    public void generateApiFields(FieldMeta fieldMeta, List<ApiField> apiFields) {
        ApiField customTypeField = handleCurrentField(fieldMeta);
        apiFields.add(customTypeField);
        Field[] fields = ReflectUtil.getFields(fieldMeta.getClazz());
        for (Field f : fields) {
            ApiModelProperty apiModelProperty = AnnotationUtils.getAnnotation(f, ApiModelProperty.class);
            if (apiModelProperty == null)
                continue;

            FieldMeta subFieldMeta = FieldMeta.of(f.getType(), f.getGenericType(), f, null);
            AbstractApiResolver.fillApiFields(subFieldMeta, apiFields);
        }
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
