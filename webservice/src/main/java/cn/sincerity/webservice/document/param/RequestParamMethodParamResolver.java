package cn.sincerity.webservice.document.param;

import cn.sincerity.webservice.document.ApiField;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Arrays;
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
        return null;
    }

    @Override
    public String paramType() {
        return REQUEST_PARAM;
    }
    @Override
    public int getOrder() {
        return 50;
    }
}
