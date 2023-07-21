package cn.sincerity.webservice.document.resolver;

import cn.sincerity.webservice.document.model.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.FieldType;
import cn.sincerity.webservice.document.model.ObjectMeta;
import cn.sincerity.webservice.document.resolver.generator.AbstractTypeGenerator;
import cn.sincerity.webservice.document.resolver.generator.TypeGenerator;
import com.alibaba.fastjson.JSON;
import org.springframework.core.Ordered;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AbstractApiResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
public abstract class AbstractMethodResolver implements MethodResolver, Ordered {

    private static final List<TypeGenerator> generators = new ArrayList<>();

    public static void setGenerators(List<TypeGenerator> generators) {
        AbstractMethodResolver.generators.addAll(generators);
    }

    @Override
    public String resolve2Json4Response(Method method) {
        Class<?> returnClz = method.getReturnType();
        if (Void.class == returnClz || void.class == returnClz) {
            return "";
        }

        Type returnType = method.getGenericReturnType();
        ObjectMeta objectMeta = ObjectMeta.of(returnClz, returnType);
        Object defaultValue = getDefaultValue(objectMeta);

        return JSON.toJSONString(defaultValue);
    }

    public static Object getDefaultValue(ObjectMeta objectMeta) {
        Class<?> clazz = objectMeta.getClazz();
        Object cache = AbstractTypeGenerator.getDefaultValueFormLocalCache(clazz);
        if (cache != null)
            return cache;

        for (TypeGenerator generator : generators) {
            if (generator.support(clazz))
                return generator.generateDefaultValue(objectMeta);
        }

        return null;
    }

    @Override
    public List<ApiField> resolve2Fields4Response(Method method) {
        Class<?> returnClz = method.getReturnType();

        if (Void.class == returnClz || void.class == returnClz) {
            return Collections.emptyList();
        }

        Type returnType = method.getGenericReturnType();
        List<ApiField> apiFields = new ArrayList<>();

        FieldMeta fieldMeta = FieldMeta.of(returnClz, returnType, null, FieldType.DATA);
        fillApiFields(fieldMeta, apiFields);

        return apiFields;
    }

    public static void fillApiFields(FieldMeta fieldMeta, List<ApiField> apiFields) {
        for (TypeGenerator generator : generators) {
            if (generator.support(fieldMeta.getClazz())) {
                generator.generateApiFields(fieldMeta, apiFields);
                return;
            }
        }
    }

    protected boolean supportByAnnotationType(Annotation[][] parameterAnnotations, Class<?> annotationType) {
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                if (annotationType.isInstance(annotation))
                    return true;
            }
        }
        return false;
    }

    protected void checkParamTypesIsEmpty(Parameter[] parameters) {
        if (ObjectUtils.isEmpty(parameters)) {
            throw new IllegalArgumentException("params must be not empty.");
        }
    }
}
