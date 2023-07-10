package cn.sincerity.webservice.document.param;

import cn.sincerity.webservice.document.ApiField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.List;

/**
 * MethodParamResolver: 方法参数解析器
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public interface MethodParamResolver {

    String REQUEST_PARAM = "form-data";

    String REQUEST_BODY = "json";

    String DEFAULT_PARAM_TYPE = "url";

    boolean support(Annotation[][] parameterAnnotations);

    String resolve4Request(Parameter[] parameters);

    List<ApiField> resolve4Document(Parameter[] parameters);

    String paramType();
}
