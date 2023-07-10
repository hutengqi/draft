package cn.sincerity.webservice.document;

import cn.sincerity.common.util.JsonUtil;
import cn.sincerity.webservice.controller.HelloController;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DocumentService
 *
 * @author Ht7_Sincerity
 * @date 2023/7/7
 */
@Service
public class DocumentService {

    public void generate() {
        List<ApiInformation> list = new ArrayList<>();
        Class<HelloController> clz = HelloController.class;
        RequestMapping clzRequestMapping = AnnotationUtils.findAnnotation(clz, RequestMapping.class);
        if (clzRequestMapping == null)
            return;
        List<String> clzPaths = Arrays.asList(clzRequestMapping.value());

        Method[] methods = clz.getMethods();
        for (Method method : methods) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (apiOperation == null)
                continue;

            String apiName = apiOperation.value();

            RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);

            assert requestMapping != null;
            String[] value = requestMapping.value();

            String params = "";
            List<Class<?>> paramClzList = Arrays.asList(method.getParameterTypes());

            ApiInformation apiInformation = ApiInformation.builder()
                    .name(apiName)
                    .url(value[0])
                    .params(params)
                    .build();
            list.add(apiInformation);
        }
        list.forEach(e -> System.out.println(e.toString()));
    }
}
