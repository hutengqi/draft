package cn.sincerity.webservice.document.resolver.generator;

import cn.hutool.core.util.ReflectUtil;
import cn.sincerity.webservice.document.DocumentCreator;
import cn.sincerity.webservice.document.annotation.ApiProperty;
import cn.sincerity.webservice.document.model.ApiEnum;
import cn.sincerity.webservice.document.model.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.FieldType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.sincerity.webservice.document.ApiDocUtils.extractValue;
import static cn.sincerity.webservice.document.ApiDocUtils.extractValue4Require;


/**
 * AbstractTypeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
public abstract class AbstractTypeGenerator implements TypeGenerator, Ordered {

    protected static final Map<Class<?>, Object> DEFAULT_VALUE_MAP = new ConcurrentHashMap<>();

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

    public static Object getDefaultValueFormLocalCache(Class<?> clazz) {
        return DEFAULT_VALUE_MAP.get(clazz);
    }


    protected ApiField handleCurrentField(FieldMeta fieldMeta) {
        findEnum(fieldMeta);

        Field field = fieldMeta.getField();
        String typeName = fieldMeta.getTypeName();

        if (field == null) {
            FieldType fieldType = fieldMeta.getFieldType();
            return ApiField.of(fieldType.getName(), fieldType.getDesc(), typeName, true, "");
        }

        return ApiField.of(field.getName(), extractValue(field, ApiModelProperty::value), typeName, extractValue4Require(field), extractValue(field, ApiProperty::remark));
    }

    private void findEnum(FieldMeta fieldMeta) {
        Field field = fieldMeta.getField();
        if (field == null) {
            return;
        }

        Class<?> clazz = fieldMeta.getClazz();
        if (!clazz.isEnum()) {
            Class<?> enumClass = extractValue(field, ApiProperty::enumClass);
            if (enumClass != null && enumClass.isEnum()) {
                clazz = enumClass;
            }
        }
        if (!clazz.isEnum()) {
            return;
        }

        ApiModel apiModel = AnnotationUtils.findAnnotation(clazz, ApiModel.class);
        if (apiModel == null) {
            return;
        }

        String enumTypeName = apiModel.value();
        String codeMethod = extractValue(field, ApiProperty::codeMethod);
        String descMethod = extractValue(field, ApiProperty::descMethod);

        if (StringUtils.isBlank(descMethod)) {
            return;
        }

        for (Object enumConstant : clazz.getEnumConstants()) {
            Object code = ReflectUtil.invoke(enumConstant, codeMethod);
            Object desc = ReflectUtil.invoke(enumConstant, descMethod);

            ApiEnum build = ApiEnum.of(enumTypeName, code.toString(), desc.toString());

            DocumentCreator.API_ENUMS.add(build);
        }
    }

    protected static Class<?> getClassFromType(Type type) {
        if (type instanceof ParameterizedType)
            return (Class<?>) ((ParameterizedType) type).getRawType();

        return (Class<?>) type;
    }
}
