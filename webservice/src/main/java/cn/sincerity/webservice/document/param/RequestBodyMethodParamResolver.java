package cn.sincerity.webservice.document.param;

import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.ApiRemark;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
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
    public String resolve4Request(Method method) {
        Parameter[] parameters = method.getParameters();
        super.checkParamTypesIsEmpty(parameters);
        Parameter parameter = parameters[0];
        try {
            Object o = parameter.getType().newInstance();
            super.initFieldValue(o);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(o);
        } catch (InstantiationException | IllegalAccessException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApiField> resolve4Document(Method method) {
        Parameter[] parameters = method.getParameters();
        super.checkParamTypesIsEmpty(parameters);
        Parameter parameter = parameters[0];
        Class<?> type = parameter.getType();
        List<ApiField> list = new ArrayList<>();
        generateApiField(type, list);
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

    private void generateApiField(Class<?> type, List<ApiField> list) {
        for (Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            ApiModelProperty apiModelProperty = AnnotationUtils.getAnnotation(field, ApiModelProperty.class);
            if (apiModelProperty == null)
                return;

            ApiField apiField = ApiField.builder()
                    .name(field.getName())
                    .desc(apiModelProperty.value())
                    .type(fieldType.getSimpleName())
                    .require(require(field, apiModelProperty.required()))
                    .build();

            ApiRemark apiRemark = AnnotationUtils.getAnnotation(field, ApiRemark.class);
            if (apiRemark != null) {
                apiField.setRemark(apiRemark.remark());
            }

            list.add(apiField);

            if (!primitiveType(fieldType)) {
                if (List.class.isAssignableFrom(fieldType)) {
                    ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                    Class<?> actualTypeArgument = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    generateApiField(actualTypeArgument, list);
                } else {
                    generateApiField(fieldType, list);
                }
            }
        }
    }

    private boolean require(Field field, boolean require) {
        return require
                || AnnotationUtils.getAnnotation(field, NotNull.class) == null
                || AnnotationUtils.getAnnotation(field, NotBlank.class) == null
                || AnnotationUtils.getAnnotation(field, NotEmpty.class) == null;
    }

}