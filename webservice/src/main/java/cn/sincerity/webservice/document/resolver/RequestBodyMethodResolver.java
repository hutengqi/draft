package cn.sincerity.webservice.document.resolver;

import cn.sincerity.webservice.document.model.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.FieldType;
import cn.sincerity.webservice.document.model.ObjectMeta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * RequestBodyMethodResolver: RequestBody 参数方式的 解析器
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
public class RequestBodyMethodResolver extends AbstractMethodResolver {


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

            ObjectMeta objectMeta = ObjectMeta.of(parameter.getType(), null);
            Object o = AbstractMethodResolver.getDefaultValue(objectMeta);

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

        FieldMeta fieldMeta = FieldMeta.of(clz, type, null, FieldType.PARAM);
        AbstractMethodResolver.fillApiFields(fieldMeta, list);

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
