package cn.sincerity.webservice.document.param;

import cn.sincerity.webservice.document.ApiField;
import org.springframework.core.Ordered;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * AbstractMethodParamResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public abstract class AbstractMethodParamResolver implements MethodParamResolver, Ordered {

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
