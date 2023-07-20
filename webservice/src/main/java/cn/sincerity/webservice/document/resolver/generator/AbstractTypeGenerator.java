package cn.sincerity.webservice.document.resolver.generator;

import cn.hutool.core.util.ReflectUtil;
import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.annotation.ApiProperty;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.FieldType;
import cn.sincerity.webservice.document.model.ObjectMeta;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AbstractTypeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/13
 */
public abstract class AbstractTypeGenerator implements Ordered {

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

    public static Object getDefaultValueFormLocalCache(Class<?> clazz){
        return DEFAULT_VALUE_MAP.get(clazz);
    }

    public abstract boolean support(Class<?> clz);

    public abstract Object generateDefaultValue(ObjectMeta objectMeta);

    public abstract void generateApiFields(FieldMeta fieldMeta, List<ApiField> apiFields);

    protected ApiField handleCurrentField(FieldMeta fieldMeta) {
        Field field = fieldMeta.getField();
        String typeName = fieldMeta.getTypeName();

        if (field == null) {
            FieldType fieldType = fieldMeta.getFieldType();
            return ApiField.builder()
                    .name(fieldType.getName())
                    .desc(fieldType.getDesc())
                    .type(typeName)
                    .require(true)
                    .build();
        }

        return ApiField.builder()
                .name(field.getName())
                .type(typeName)
                .desc(extractValue(field, ApiModelProperty::value))
                .remark(extractValue(field, ApiProperty::remark))
                .require(extractValue4Require(field))
                .build();
    }

    private void findEnum(FieldMeta fieldMeta) {
        Field field = fieldMeta.getField();
        if (field == null)
            return;

        Class<?> clazz = fieldMeta.getClazz();
        if (!clazz.isEnum()) {
            Class<?> enumClass = extractValue(field, ApiProperty::enumClass);
            if (enumClass != null && enumClass.isEnum())
                clazz = enumClass;
        }
        if (!clazz.isEnum())
            return;


        for (Object enumConstant : clazz.getEnumConstants()) {

        }
    }

    protected static Class<?> getClassFromType(Type type) {
        if (type instanceof ParameterizedType)
            return (Class<?>) ((ParameterizedType) type).getRawType();

        return (Class<?>) type;
    }

    @SuppressWarnings("unchecked")
    public static <A extends Annotation, R> R extractValue(AnnotatedElement annotatedElement, SFunction<A, R> sFunction) {
        LambdaMeta lambdaMeta = LambdaUtils.extract(sFunction);
        Class<A> instantiatedClass = (Class<A>) lambdaMeta.getInstantiatedClass();
        A annotation = AnnotationUtils.getAnnotation(annotatedElement, instantiatedClass);
        if (annotation == null)
            return null;


        String methodName = lambdaMeta.getImplMethodName();
        return ReflectUtil.invoke(annotation, methodName);
    }

    protected boolean extractValue4Require(Field field) {
        Boolean required = extractValue(field, ApiModelProperty::required);
        if (required == null)
            required = false;

        return require(field, required);
    }

    protected boolean require(Field field, boolean require) {
        return require
                || AnnotationUtils.getAnnotation(field, NotNull.class) != null
                || AnnotationUtils.getAnnotation(field, NotBlank.class) != null
                || AnnotationUtils.getAnnotation(field, NotEmpty.class) != null;
    }

    protected boolean primitiveType(Class<?> type) {
        return type.isPrimitive()
                || CharSequence.class.isAssignableFrom(type)
                || Number.class.isAssignableFrom(type)
                || ChronoLocalDate.class.isAssignableFrom(type)
                || Boolean.class.isAssignableFrom(type);
    }
}
