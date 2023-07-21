package cn.sincerity.webservice.document;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.annotation.AnnotationUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * ApiDocUtils: API文档 工具类
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
public class ApiDocUtils {

    @SuppressWarnings("unchecked")
    public static <A extends Annotation, R> R extractValue(AnnotatedElement annotatedElement, SFunction<A, R> sFunction) {
        LambdaMeta lambdaMeta = LambdaUtils.extract(sFunction);
        Class<A> instantiatedClass = (Class<A>) lambdaMeta.getInstantiatedClass();
        A annotation = AnnotationUtils.getAnnotation(annotatedElement, instantiatedClass);
        if (annotation == null)
            return null;


        String methodName = lambdaMeta.getImplMethodName();
        return ReflectUtil.invoke(annotation, methodName);
    }


    public static boolean extractValue4Require(Field field) {
        Boolean required = extractValue(field, ApiModelProperty::required);
        if (required == null)
            required = false;

        return require(field, required);
    }

    private static boolean require(Field field, boolean require) {
        return require
                || AnnotationUtils.getAnnotation(field, NotNull.class) != null
                || AnnotationUtils.getAnnotation(field, NotBlank.class) != null
                || AnnotationUtils.getAnnotation(field, NotEmpty.class) != null;
    }

    public static void findCandidateAndRegister(BeanDefinitionRegistry registry, ClassPathBeanDefinitionScanner scanner, String reference) {
        Set<BeanDefinition> candidates = scanner.findCandidateComponents(reference);
        try {
            for (BeanDefinition candidate : candidates) {
                Class<?> clazz = Class.forName(candidate.getBeanClassName());
                registry.registerBeanDefinition(clazz.getName(), BeanDefinitionBuilder.genericBeanDefinition(clazz).getBeanDefinition());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
