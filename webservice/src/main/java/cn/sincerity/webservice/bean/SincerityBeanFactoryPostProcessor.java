package cn.sincerity.webservice.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * SincerityBeanFactoryPostProcessor
 *
 * @author Ht7_Sincerity
 * @date 2022/10/24
 */
@Slf4j
@Component
public class SincerityBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // log.info("[SINCERITY]BeanFactoryPostProcessor#postProcessBeanFactory：可以修改 BeanDefinition 的定义信息");
    }
}
