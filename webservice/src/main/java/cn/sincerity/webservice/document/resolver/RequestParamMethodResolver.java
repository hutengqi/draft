package cn.sincerity.webservice.document.resolver;

import cn.sincerity.webservice.document.model.ApiField;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiParam;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cn.sincerity.webservice.document.ApiDocUtils.extractValue;


/**
 * RequestParamMethodResolver: RequestParam 参数方式的解析器
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
public class RequestParamMethodResolver extends AbstractMethodResolver {

    @Override
    public boolean support(Annotation[][] parameterAnnotations) {
        return super.supportByAnnotationType(parameterAnnotations, RequestParam.class);
    }

    @Override
    public String resolve2Json4Request(Method method) {
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
    public List<ApiField> resolve2Fields4Request(Method method) {
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

            ApiField apiField = ApiField.of(
                    requestParam.value(),
                    extractValue(parameter, ApiParam::value),
                    parameter.getType().getSimpleName(),
                    requestParam.required(),
                    ""
            );

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
