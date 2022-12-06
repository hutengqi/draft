package cn.sincerity.webservice.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * SincerityInstantiationAwareBeanPostProcessor
 *
 * @author Ht7_Sincerity
 * @date 2022/10/24
 */
@Slf4j
@Component
public class SincerityInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        //log.info("[SINCERITY]InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation：实例化前置处理");
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        //log.info("[SINCERITY]InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation：实例化后置处理");
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
       // log.info("[SINCERITY]InstantiationAwareBeanPostProcessor#postProcessProperties：可以对 Bean 的属性值进行修改");
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }
}
