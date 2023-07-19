package cn.sincerity.webservice.document.resolver.generator;

import cn.hutool.core.util.ReflectUtil;
import cn.sincerity.webservice.document.ApiField;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.swagger.annotations.ApiModelProperty;
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
public abstract class AbstractTypeGenerator {

    public static final Map<Class<?>, Object> DEFAULT_VALUE_MAP = new ConcurrentHashMap<>();

    private AbstractTypeGenerator next;

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

    public void setNext(AbstractTypeGenerator generator) {
        this.next = generator;
    }

    public Object getDefaultValue(Class<?> clz, Type type) {
        for (Map.Entry<Class<?>, Object> entry : DEFAULT_VALUE_MAP.entrySet()) {
            if (entry.getKey().isAssignableFrom(clz)) {
                return entry.getValue();
            }
        }

        if (judge(clz)) {
            return generateDefaultValue(clz, type);
        }

        if (next != null) {
            return next.getDefaultValue(clz, type);
        }

        return null;
    }

    public void fillApiFields(Class<?> clz, Type type, Field field, List<ApiField> apiFields, FieldType fieldType) {
        if (judge(clz)) {
            generateApiFields(clz, type, field, apiFields, fieldType);
            return;
        }

        if (next != null) {
            next.fillApiFields(clz, type, field, apiFields,fieldType);
        }
    }


    abstract boolean judge(Class<?> clz);

    abstract Object generateDefaultValue(Class<?> clz, Type type);

    public abstract void generateApiFields(Class<?> clz, Type type, Field field, List<ApiField> apiFields, FieldType fieldType);

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
