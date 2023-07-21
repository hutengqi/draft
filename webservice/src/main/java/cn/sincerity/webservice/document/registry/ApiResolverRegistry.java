package cn.sincerity.webservice.document.registry;

import cn.sincerity.webservice.document.ApiDocUtils;
import cn.sincerity.webservice.document.resolver.MethodResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.classreading.MetadataReader;

import java.lang.reflect.Modifier;

/**
 * ApiResolverRegistry
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
@Configuration
public class ApiResolverRegistry implements BeanDefinitionRegistryPostProcessor {

    private static final String PACKAGE_REFERENCE = "com.hqins.insurance.flow.utils.document.resolver";

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        GenericApplicationContext context = new GenericApplicationContext();
        ClassPathBeanDefinitionScanner scanner = new ApiResolverScanner(context);
        ApiDocUtils.findCandidateAndRegister(registry, scanner, PACKAGE_REFERENCE);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    static class ApiResolverScanner extends ClassPathBeanDefinitionScanner {

        public ApiResolverScanner(BeanDefinitionRegistry registry) {
            super(registry, false);
        }

        @Override
        protected boolean isCandidateComponent(MetadataReader metadataReader) {
            return match(metadataReader);
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            return true;
        }

        private boolean match(MetadataReader metadataReader) {
            Class<?> clz;
            try {
                clz = Class.forName(metadataReader.getClassMetadata().getClassName());
            } catch (ClassNotFoundException e) {
                return false;
            }
            boolean isInterfaceOrAbstract = clz.isInterface() || Modifier.isAbstract(clz.getModifiers());
            return !isInterfaceOrAbstract && MethodResolver.class.isAssignableFrom(clz);
        }
    }
}
