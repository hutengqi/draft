package cn.sincerity.webservice.document;

import cn.sincerity.webservice.document.annotation.ApiDoc;
import cn.sincerity.webservice.document.model.ApiDocument;
import cn.sincerity.webservice.document.model.ApiEnum;
import cn.sincerity.webservice.document.model.ApiField;
import cn.sincerity.webservice.document.model.ApiMethod;
import cn.sincerity.webservice.document.resolver.AbstractMethodResolver;
import cn.sincerity.webservice.document.resolver.MethodResolver;
import cn.sincerity.webservice.document.resolver.generator.TypeGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * DocumentCreator: API 文档创建
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
@Component
public class DocumentCreator implements InitializingBean, ApplicationContextAware {


    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

    public static final Set<ApiEnum> API_ENUMS = new HashSet<cn.sincerity.webservice.document.model.ApiEnum>();

    @Autowired
    private List<MethodResolver> resolvers;

    @Autowired
    private List<TypeGenerator> generators;

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        AnnotationAwareOrderComparator.sort(resolvers);
        AnnotationAwareOrderComparator.sort(generators);
        AbstractMethodResolver.setGenerators(generators);
    }

    public synchronized List<ApiDocument> create() {
        List<Class<?>> types = findTypeWithApiDoc();
        if (types.isEmpty()) {
            return Collections.emptyList();
        }

        List<ApiDocument> documents = new ArrayList<>(types.size());

        for (Class<?> type : types) {
            Controller controller = AnnotatedElementUtils.findMergedAnnotation(type, Controller.class);
            if (controller == null) {
                continue;
            }

            ApiDoc apiDoc = AnnotationUtils.findAnnotation(type, ApiDoc.class);
            if (apiDoc == null) {
                continue;
            }

            String group = apiDoc.group();

            RequestMapping typeMapping = AnnotatedElementUtils.findMergedAnnotation(type, RequestMapping.class);
            String typePath = getFirstPath(typeMapping);
            List<ApiMethod> apiMethods = apiMethods(type, typePath);

            List<ApiEnum> enums = new ArrayList<>(API_ENUMS);

            ApiDocument apiDocument = ApiDocument.of(group, apiMethods, enums);
            documents.add(apiDocument);

            API_ENUMS.clear();
        }

        return documents;
    }

    private List<Class<?>> findTypeWithApiDoc() {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(ApiDoc.class);
        if (map.isEmpty()) {
            return Collections.emptyList();
        }
        return map.values().stream()
                .map(Object::getClass)
                .collect(Collectors.toList());
    }

    private List<ApiMethod> apiMethods(Class<?> type, String typePath) {
        Method[] methods = type.getMethods();
        List<ApiMethod> apiMethods = new ArrayList<>(methods.length);
        Object bean = applicationContext.getBean(type);
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        for (Method method : methods) {
            method = AopUtils.getMostSpecificMethod(method, targetClass);
            ApiOperation apiOperation = AnnotationUtils.findAnnotation(method, ApiOperation.class);
            if (apiOperation == null)
                continue;

            RequestMapping methodMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
            if (methodMapping == null)
                continue;

            String methodPath = getFirstPath(methodMapping);
            String path = PATH_MATCHER.combine(typePath, methodPath);
            if (StringUtils.hasLength(path) && !path.startsWith("/")) {
                path = "/" + path;
            }

            RequestMethod requestMethod = getFirstRequestMethod(methodMapping);

            Annotation[][] parameterAnnotations = method.getParameterAnnotations();

            for (MethodResolver resolver : resolvers) {
                if (resolver.support(parameterAnnotations)) {

                    String param = resolver.resolve2Json4Request(method);
                    String paramType = resolver.paramType();
                    String data = resolver.resolve2Json4Response(method);
                    List<ApiField> requestFields = resolver.resolve2Fields4Request(method);
                    List<ApiField> responseFields = resolver.resolve2Fields4Response(method);

                    ApiMethod apiMethod = ApiMethod.of(apiOperation.value(), path, param, paramType, requestMethod.name(), data, requestFields, responseFields);

                    apiMethods.add(apiMethod);
                    break;
                }
            }
        }
        return apiMethods;
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
