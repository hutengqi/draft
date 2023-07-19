package cn.sincerity.webservice.document.resolver;

import cn.sincerity.webservice.document.ApiField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * ApiResolver: 参数解析器
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
public interface ApiResolver {

    String REQUEST_PARAM = "form-data";

    String REQUEST_BODY = "json";

    String DEFAULT_PARAM_TYPE = "url";

    boolean support(Annotation[][] parameterAnnotations);

    String resolve2Json4Request(Method method);

    String resolve2Json4Response(Method method);

    List<ApiField> resolve2Fields4Request(Method method);

    List<ApiField> resolve2Fields4Response(Method method);

    String paramType();
}
