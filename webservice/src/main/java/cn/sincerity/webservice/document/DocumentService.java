package cn.sincerity.webservice.document;

import cn.sincerity.webservice.controller.HelloController;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public void generate() {
        List<ApiInformation> list = new ArrayList<>();
        Class<HelloController> clz = HelloController.class;
        Method[] methods = clz.getMethods();
        for (Method method : methods) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (apiOperation == null)
                continue;

            String apiName = apiOperation.value();

            GetMapping annotation = method.getAnnotation(GetMapping.class);
            String[] value = annotation.value();

            String params = "";
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes != null && parameterTypes[0] != null) {
                params = parameterTypes[0].toString();
            }

            ApiInformation apiInformation = ApiInformation.builder()
                    .name(apiName)
                    .url(value[0])
                    .params(params)
                    .build();
            list.add(apiInformation);
        }
        list.forEach(e -> System.out.println(e.toString()));
    }

//    public RequestMapping getRequestMappingViaMethod(Method method) {
//        GetMapping getMapping = method.getAnnotation(GetMapping.class);
//        if (getMapping != null) {
//            getMapping.annotationType()
//        }
//    }
}
