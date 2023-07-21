package cn.sincerity.webservice.document.registry;

import cn.sincerity.webservice.document.ApiDocUtils;
import cn.sincerity.webservice.document.resolver.generator.TypeGenerator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * TypeGeneratorRegistry
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
@Configuration
public class TypeGeneratorRegistry implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {


    private static final ConfigurationPropertyName PREFIX = ConfigurationPropertyName.of("api.generator.packages");

    private static final Bindable<List<String>> CONTENT = Bindable.listOf(String.class);

    private ApplicationContext applicationContext;


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        GenericApplicationContext context = new GenericApplicationContext();
        TypeGeneratorScanner scanner = new TypeGeneratorScanner(context);
        Binder binder = Binder.get(applicationContext.getEnvironment());
        List<String> packages = binder.bind(PREFIX, CONTENT).orElseGet(ArrayList::new);
        packages.add("com.hqins.insurance.flow.utils.document.resolver.generator");
        packages.forEach(reference -> {
            ApiDocUtils.findCandidateAndRegister(registry, scanner, reference);
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    static class TypeGeneratorScanner extends ClassPathBeanDefinitionScanner {

        public TypeGeneratorScanner(BeanDefinitionRegistry registry) {
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
            return !isInterfaceOrAbstract && TypeGenerator.class.isAssignableFrom(clz);
        }
    }
}
