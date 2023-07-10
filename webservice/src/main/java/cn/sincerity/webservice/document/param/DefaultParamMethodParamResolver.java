package cn.sincerity.webservice.document.param;

import cn.sincerity.webservice.document.ApiField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DefaultParamMethodParamResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public class DefaultParamMethodParamResolver extends AbstractMethodParamResolver implements MethodParamResolver {

    @Override
    public boolean support(Annotation[][] parameterAnnotations) {
        return true;
    }

    @Override
    public String resolve4Request(Parameter[] parameters) {
        return Arrays.stream(parameters)
                .map(parameter -> parameter.getName() + "=")
                .collect(Collectors.joining("&"));

    }

    @Override
    public List<ApiField> resolve4Document(Parameter[] parameters) {
        return null;
    }

    @Override
    public String paramType() {
        return DEFAULT_PARAM_TYPE;
    }
}
