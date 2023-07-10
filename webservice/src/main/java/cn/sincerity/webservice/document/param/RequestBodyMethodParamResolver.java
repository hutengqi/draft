package cn.sincerity.webservice.document.param;

import cn.sincerity.webservice.document.ApiField;
import com.alibaba.fastjson.JSON;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * RequestBodyMethodParamResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public class RequestBodyMethodParamResolver extends AbstractMethodParamResolver implements MethodParamResolver {
    @Override
    public boolean support(Annotation[][] parameterAnnotations) {
        return super.supportByAnnotationType(parameterAnnotations, RequestBody.class);
    }

    @Override
    public String resolve4Request(Parameter[] parameters) {
        super.checkParamTypesIsEmpty(parameters);
        Parameter parameter = parameters[0];
        try {
            return JSON.toJSONString(parameter.getType().newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApiField> resolve4Document(Parameter[] parameters) {
        return null;
    }

    @Override
    public String paramType() {
        return REQUEST_BODY;
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
