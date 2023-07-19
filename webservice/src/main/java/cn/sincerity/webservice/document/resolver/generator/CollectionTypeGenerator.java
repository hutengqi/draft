package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.annotation.ApiRemark;
import cn.sincerity.webservice.document.resolver.AbstractApiResolver;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.Field;
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

        CollectionArgType collectionArgType = CollectionArgType.from(type);
        Object element = AbstractApiResolver.HEAD_GENERATOR.getDefaultValue(collectionArgType.getArgClz(), collectionArgType.getArgType());

        if (List.class.isAssignableFrom(clz))
            return Collections.singletonList(element);

        return Collections.singleton(element);
    }

    @Override
    public void generateApiFields(Class<?> clz, Type type, Field field, List<ApiField> apiFields, FieldType fieldType) {
        CollectionArgType collectionArgType = CollectionArgType.from(type);
        apiFields.add(generateListField(clz, collectionArgType.getArgClz(), field, fieldType));
        AbstractApiResolver.HEAD_GENERATOR.fillApiFields(collectionArgType.getArgClz(), collectionArgType.getArgType(), null, apiFields, FieldType.ELEMENT);
    }

    private ApiField generateListField(Class<?> clz, Class<?> argClz, Field field, FieldType fieldType) {
        if (field == null)
            return ApiField.builder()
                    .name(fieldType.getName())
                    .desc(fieldType.getDesc())
                    .type(generateTypeName(clz, argClz))
                    .require(true)
                    .build();

        return ApiField.builder()
                .name(field.getName())
                .desc(extractValue(field, ApiModelProperty::value))
                .type(generateTypeName(clz, argClz))
                .remark(extractValue(field, ApiRemark::remark))
                .build();
    }

    private String generateTypeName(Class<?> clz, Class<?> argClz) {
        return clz.getSimpleName() + "<" + argClz.getSimpleName() + ">";
    }

    @Data
    static class CollectionArgType {

        private Class<?> argClz;

        private Type argType;

        static CollectionArgType from(Type type) {
            CollectionArgType collectionArgType = new CollectionArgType();
            ParameterizedType parameterizedType = (ParameterizedType) type;
            collectionArgType.argType = parameterizedType.getActualTypeArguments()[0];
            collectionArgType.argClz = getClassFromType(collectionArgType.argType);
            return collectionArgType;
        }
    }
}
