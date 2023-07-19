package cn.sincerity.webservice.document.resolver;

import cn.sincerity.webservice.document.ApiField;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DefaultParamMethodParamResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public class DefaultParamApiResolver extends AbstractApiResolver implements ApiResolver {

    @Override
    public boolean support(Annotation[][] parameterAnnotations) {
        return true;
    }

    @Override
    public String resolve2Json4Request(Method method) {
        Parameter[] parameters = method.getParameters();
        if (ObjectUtils.isEmpty(parameters)) {
            return "";
        }
        String[] parameterNames = getParameterNames(method);
        return Arrays.stream(parameterNames)
                .map(name -> name + "=")
                .collect(Collectors.joining("&"));

    }

    @Override
    public List<ApiField> resolve2Fields4Request(Method method) {
        Parameter[] parameters = method.getParameters();
        if (ObjectUtils.isEmpty(parameters)) {
            return Collections.emptyList();
        }
        String[] parameterNames = getParameterNames(method);
        int length = parameters.length;
        List<ApiField> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            String parameterName = parameterNames[i];
            Parameter parameter = parameters[i];
            ApiField apiField = ApiField.builder()
                    .name(parameterName)
                    .type(parameter.getType().getSimpleName())
                    .require(false)
                    .build();
            list.add(apiField);
        }
        return list;
    }

    @Override
    public String paramType() {
        return DEFAULT_PARAM_TYPE;
    }

    @Override
    public int getOrder() {
        return 100;
    }


    protected String[] getParameterNames(Method method) {
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        if (ObjectUtils.isEmpty(parameterNames)) {
            return new String[0];
        }
        return parameterNames;
    }
}
