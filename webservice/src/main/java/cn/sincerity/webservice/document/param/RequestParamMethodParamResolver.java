package cn.sincerity.webservice.document.param;

import cn.sincerity.webservice.document.ApiField;
import com.alibaba.fastjson.JSON;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PathVariableMethodParamResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public class RequestParamMethodParamResolver extends AbstractMethodParamResolver implements MethodParamResolver {

    @Override
    public boolean support(Annotation[][] parameterAnnotations) {
        return super.supportByAnnotationType(parameterAnnotations, RequestParam.class);
    }

    @Override
    public String resolve4Request(Parameter[] parameters) {
        List<String> params = Arrays.stream(parameters)
                .map(Parameter::getName)
                .collect(Collectors.toList());
        return JSON.toJSONString(params);
    }

    @Override
    public List<ApiField> resolve4Document(Parameter[] parameters) {
        if (ObjectUtils.isEmpty(parameters)) {
            return Collections.emptyList();
        }
        Method method = (Method) parameters[0].getDeclaringExecutable();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        int length = parameters.length;
        List<ApiField> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            Parameter parameter = parameters[i];
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            RequestParam requestParam = (RequestParam)findRequestParam(parameterAnnotation);
            if (requestParam == null)
                continue;

            ApiField apiField = ApiField.builder()
                    .name(requestParam.name())
                    .type(parameter.getType().getSimpleName())
                    .require(requestParam.required())
                    .build();
            list.add(apiField);
        }
        return list;
    }

    @Override
    public String paramType() {
        return REQUEST_PARAM;
    }

    @Override
    public int getOrder() {
        return 1;
    }

    private Annotation findRequestParam(Annotation[] parameterAnnotation) {
        for (Annotation annotation : parameterAnnotation) {
            if (annotation instanceof RequestParam){
                return annotation;
            }
        }
        return null;
    }
}
