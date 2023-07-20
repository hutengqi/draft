package cn.sincerity.webservice.document.registry;

import cn.sincerity.webservice.document.resolver.ApiResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * ApiResolverBeanDefinitionRegistry
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
@Configuration
public class ApiResolverRegistry implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private static final ConfigurationPropertyName PREFIX = ConfigurationPropertyName.of("api.resolver.packages");

    private static final Bindable<List<String>> CONTENT = Bindable.listOf(String.class);

    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        GenericApplicationContext context = new GenericApplicationContext();
        ClassPathBeanDefinitionScanner scanner = new ApiResolverScanner(context);
        Binder binder = Binder.get(applicationContext.getEnvironment());
        List<String> packages = binder.bind(PREFIX, CONTENT).orElseGet(ArrayList::new);
        packages.add(0, "cn.sincerity.webservice.document.resolver");
        packages.forEach(packageName -> {
            Set<BeanDefinition> candidates = scanner.findCandidateComponents(packageName);
            try {
                for (BeanDefinition candidate : candidates) {
                    Class<?> clz = Class.forName(candidate.getBeanClassName());
                    registry.registerBeanDefinition(clz.getName(), BeanDefinitionBuilder.genericBeanDefinition(clz).getBeanDefinition());
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    static class ApiResolverScanner extends ClassPathBeanDefinitionScanner {

        public ApiResolverScanner(BeanDefinitionRegistry registry) {
            super(registry, false);
        }

        @Override
        protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
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
            return !isInterfaceOrAbstract && ApiResolver.class.isAssignableFrom(clz);
        }
    }
}
