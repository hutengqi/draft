package cn.sincerity.webservice.document.resolver;

import cn.sincerity.webservice.document.ApiEnum;
import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.FieldType;
import cn.sincerity.webservice.document.model.ObjectMeta;
import cn.sincerity.webservice.document.resolver.generator.*;
import com.alibaba.fastjson.JSON;
import org.springframework.core.Ordered;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AbstractMethodParamResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public abstract class AbstractApiResolver implements ApiResolver, Ordered {

    private static final List<AbstractTypeGenerator> generators = new ArrayList<>();

    public static final List<ApiEnum> API_ENUMS = new ArrayList<>();

    public static void setGenerators(List<AbstractTypeGenerator> generators) {
        AbstractApiResolver.generators.addAll(generators);
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

        for (AbstractTypeGenerator generator : generators) {
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
        for (AbstractTypeGenerator generator : generators) {
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
