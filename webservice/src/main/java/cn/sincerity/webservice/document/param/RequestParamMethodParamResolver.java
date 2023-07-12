package cn.sincerity.webservice.document.param;

import cn.sincerity.webservice.document.ApiField;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiParam;
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
    public String resolve4Request(Method method) {
        Parameter[] parameters = method.getParameters();
        if (ObjectUtils.isEmpty(parameters)) {
            return JSON.toJSONString(Collections.emptyList());
        }
        List<String> names = new ArrayList<>(parameters.length);
        for (Parameter parameter : parameters) {
            RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
            if (requestParam == null) {
                continue;
            }
            names.add(requestParam.value());
        }
        return JSON.toJSONString(names);
    }

    @Override
    public List<ApiField> resolve4Document(Method method) {
        Parameter[] parameters = method.getParameters();
        if (ObjectUtils.isEmpty(parameters)) {
            return Collections.emptyList();
        }

        List<ApiField> list = new ArrayList<>(parameters.length);
        for (Parameter parameter : parameters) {
            RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
            if (requestParam == null) {
                continue;
            }
            String desc = null;
            ApiParam apiParam = parameter.getAnnotation(ApiParam.class);
            if (apiParam != null) {
                desc = apiParam.value();
            }
            ApiField apiField = ApiField.builder()
                    .name(requestParam.value())
                    .desc(desc)
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
}
