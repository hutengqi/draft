package cn.sincerity.webservice.document.resolver;

import cn.sincerity.webservice.document.model.ApiField;
import io.swagger.annotations.ApiParam;
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

import static cn.sincerity.webservice.document.ApiDocUtils.extractValue;


/**
 * DefaultParamMethodResolver: 默认地址参数类型的解析器
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
public class DefaultParamMethodResolver extends AbstractMethodResolver {


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

        int length = parameters.length;
        List<ApiField> list = new ArrayList<>();
        String[] parameterNames = getParameterNames(method);

        for (int i = 0; i < length; i++) {

            String parameterName = parameterNames[i];
            Parameter parameter = parameters[i];

            ApiField apiField = ApiField.of(
                    parameterName,
                    extractValue(parameter, ApiParam::value),
                    parameter.getType().getSimpleName(),
                    false,
                    ""
            );

            list.add(apiField);
        }

        return list;
    }

    protected String[] getParameterNames(Method method) {
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);

        if (ObjectUtils.isEmpty(parameterNames)) {
            return new String[0];
        }

        return parameterNames;
    }

    @Override
    public String paramType() {
        return DEFAULT_PARAM_TYPE;
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
