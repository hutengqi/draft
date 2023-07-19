package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.annotation.ApiRemark;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static cn.sincerity.webservice.document.resolver.AbstractApiResolver.HEAD_GENERATOR;

/**
 * MapTypeValueGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class MapTypeGenerator extends AbstractTypeGenerator {


    @Override
    boolean judge(Class<?> clz) {
        return Map.class.isAssignableFrom(clz);
    }

    @Override
    Object generateDefaultValue(Class<?> clz, Type type) {
        MapArgType mapArgType = MapArgType.from(type);
        Object key = HEAD_GENERATOR.getDefaultValue(mapArgType.getKeyClz(), mapArgType.getKeyType());
        Object value = HEAD_GENERATOR.getDefaultValue(mapArgType.getValueClz(), mapArgType.getValueType());
        return Collections.singletonMap(key, value);
    }

    @Override
    public void generateApiFields(Class<?> clz, Type type, Field field, List<ApiField> apiFields, FieldType fieldType) {
        MapArgType mapArgType = MapArgType.from(type);
        apiFields.add(generateMapField(clz, mapArgType, field, fieldType));
        HEAD_GENERATOR.fillApiFields(mapArgType.getKeyClz(), mapArgType.getKeyType(), null, apiFields, FieldType.MAP_KEY);
        HEAD_GENERATOR.fillApiFields(mapArgType.getValueClz(), mapArgType.getValueType(), null, apiFields, FieldType.MAP_VALUE);
    }

    private ApiField generateMapField(Class<?> clz, MapArgType mapArgType, Field field,FieldType fieldType) {
        if (field == null)
            return ApiField.builder()
                    .name(fieldType.getName())
                    .type(generateMapTypeName(clz, mapArgType))
                    .desc(fieldType.getDesc())
                    .require(false)
                    .build();

        return ApiField.builder()
                .name(field.getName())
                .type(generateMapTypeName(clz, mapArgType))
                .desc(extractValue(field, ApiModelProperty::value))
                .require(extractValue4Require(field))
                .remark(extractValue(field, ApiRemark::remark))
                .build();
    }

    public String generateMapTypeName(Class<?> clz, MapArgType mapArgType) {
        return clz.getSimpleName() + "<" + mapArgType.getKeyClz().getSimpleName() + "," + mapArgType.getValueClz().getSimpleName() + ">";
    }

    @Data
    static class MapArgType {

        private Type keyType;

        private Class<?> keyClz;

        private Type valueType;

        private Class<?> valueClz;

        static MapArgType from(Type mapType) {
            MapArgType mapArgType = new MapArgType();
            ParameterizedType parameterizedType = (ParameterizedType) mapType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            mapArgType.keyType = actualTypeArguments[0];
            mapArgType.keyClz = getClassFromType(mapArgType.keyType);
            mapArgType.valueType = actualTypeArguments[1];
            mapArgType.valueClz = getClassFromType(mapArgType.valueType);
            return mapArgType;
        }
    }
}
