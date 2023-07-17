package cn.sincerity.webservice.document;

import cn.sincerity.webservice.controller.HelloController;
import cn.sincerity.webservice.document.resolver.DefaultParamApiResolver;
import cn.sincerity.webservice.document.resolver.ApiResolver;
import cn.sincerity.webservice.document.resolver.RequestBodyApiResolver;
import cn.sincerity.webservice.document.resolver.RequestParamApiResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * DocumentService
 *
 * @author Ht7_Sincerity
 * @date 2023/7/7
 */
@Service
public class DocumentService {

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    private static final List<ApiResolver> API_RESOLVERS = new ArrayList<>();

    static {
        API_RESOLVERS.add(new RequestBodyApiResolver());
        API_RESOLVERS.add(new RequestParamApiResolver());
        API_RESOLVERS.add(new DefaultParamApiResolver());
        AnnotationAwareOrderComparator.sort(API_RESOLVERS);
    }

    public void generate() {
        List<ApiInformation> list = new ArrayList<>();
        Class<HelloController> type = HelloController.class;

        RequestMapping typeMapping = AnnotatedElementUtils.findMergedAnnotation(type, RequestMapping.class);
        String typePath = getFirstPath(typeMapping);

        Method[] methods = type.getMethods();
        for (Method method : methods) {

            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (apiOperation == null)
                continue;

            // 接口名称
            ApiInformation apiInformation = new ApiInformation();
            apiInformation.setName(apiOperation.value());

            // 请求路径
            RequestMapping methodMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
            if (methodMapping == null)
                continue;

            String methodPath = getFirstPath(methodMapping);
            String path = pathMatcher.combine(typePath, methodPath);
            if (StringUtils.hasLength(path) && !path.startsWith("/")) {
                path = "/" + path;
            }
            apiInformation.setPath(path);

            // 请求方式
            RequestMethod requestMethod = getFirstRequestMethod(methodMapping);
            apiInformation.setRequestMethod(requestMethod.name());

            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (ApiResolver apiResolver : API_RESOLVERS) {
                if (apiResolver.support(parameterAnnotations)) {
                    apiInformation.setParams(apiResolver.resolve2Json4Request(method));
                    apiInformation.setParamType(apiResolver.paramType());
                    apiInformation.setApiFields(apiResolver.resolve4Document(method));
                    apiInformation.setResponse(apiResolver.resolve2Json4Response(method));

                    break;
                }
            }

            list.add(apiInformation);
        }
        list.forEach(e -> System.out.println(e.toString()));
    }

    private static String getFirstPath(RequestMapping mapping) {
        if (mapping == null) {
            return "/";
        }
        String[] paths = mapping.path();
        if (ObjectUtils.isEmpty(paths) || !StringUtils.hasLength(paths[0])) {
            return "/";
        }
        return paths[0];
    }

    private static RequestMethod getFirstRequestMethod(RequestMapping mapping) {
        RequestMethod[] methods = mapping.method();
        if (ObjectUtils.isEmpty(methods)) {
            throw new IllegalArgumentException("RequestMethod must have value.");
        }
        return methods[0];
    }
}
