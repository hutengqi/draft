package cn.sincerity.webservice.document.resolver;

import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.resolver.generator.FieldType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * RequestBodyMethodParamResolver
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public class RequestBodyApiResolver extends AbstractApiResolver implements ApiResolver {
    @Override
    public boolean support(Annotation[][] parameterAnnotations) {
        return super.supportByAnnotationType(parameterAnnotations, RequestBody.class);
    }

    @Override
    public String resolve2Json4Request(Method method) {
        Parameter[] parameters = method.getParameters();
        super.checkParamTypesIsEmpty(parameters);
        Parameter parameter = parameters[0];
        try {
            Object o = HEAD_GENERATOR.getDefaultValue(parameter.getType(), null);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApiField> resolve2Fields4Request(Method method) {
        Parameter[] parameters = method.getParameters();
        super.checkParamTypesIsEmpty(parameters);
        Parameter parameter = parameters[0];
        Class<?> clz = parameter.getType();
        Type type = parameter.getParameterizedType();
        List<ApiField> list = new ArrayList<>();
        AbstractApiResolver.HEAD_GENERATOR.fillApiFields(clz, type, null, list, FieldType.PARAM);
        return list;
    }

    @Override
    public String paramType() {
        return REQUEST_BODY;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
