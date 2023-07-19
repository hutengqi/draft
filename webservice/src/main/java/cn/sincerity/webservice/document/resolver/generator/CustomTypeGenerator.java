package cn.sincerity.webservice.document.resolver.generator;

import cn.hutool.core.util.ReflectUtil;
import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.annotation.ApiElement;
import cn.sincerity.webservice.document.annotation.ApiRemark;
import cn.sincerity.webservice.document.resolver.AbstractApiResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * CustomTypeValueGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class CustomTypeGenerator extends AbstractTypeGenerator {

    @Override
    boolean judge(Class<?> clz) {
        return true;
    }

    @Override
    public void setNext(AbstractTypeGenerator generator) {
        throw new IllegalArgumentException("this type generator must be the last one.");
    }

    @Override
    public Object generateDefaultValue(Class<?> clz, Type type) {
        Object obj;
        try {
            obj = clz.newInstance();
            Field[] fields = ReflectUtil.getFields(clz);
            for (Field field : fields) {
                field.setAccessible(true);
                Class<?> fieldClz = field.getType();
                Type fieldType = field.getGenericType();
                Object fieldValue = AbstractApiResolver.HEAD_GENERATOR.getDefaultValue(fieldClz, fieldType);
                field.set(obj, fieldValue);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        DEFAULT_VALUE_MAP.put(clz, obj);
        return obj;
    }

    @Override
    public void generateApiFields(Class<?> clz, Type type, Field field, List<ApiField> apiFields, FieldType fieldType) {
        apiFields.add(generateCustomField(clz, field, fieldType));
        Field[] fields = ReflectUtil.getFields(clz);
        for (Field f : fields) {
            ApiModelProperty apiModelProperty = AnnotationUtils.getAnnotation(f, ApiModelProperty.class);
            if (apiModelProperty == null)
                continue;

            AbstractApiResolver.HEAD_GENERATOR.fillApiFields(f.getType(), f.getGenericType(), f, apiFields, null);
        }
    }

    private ApiField generateCustomField(Class<?> clz, Field field, FieldType fieldType) {
        if (field == null)
            return ApiField.builder()
                    .name(fieldType.getName())
                    .type(clz.getSimpleName())
                    .desc(fieldType.getDesc())
                    .build();

        return ApiField.builder()
                .name(field.getName())
                .type(clz.getSimpleName())
                .desc(extractValue(field, ApiModelProperty::value))
                .require(extractValue4Require(field))
                .remark(extractValue(field, ApiRemark::remark))
                .build();
    }
}
