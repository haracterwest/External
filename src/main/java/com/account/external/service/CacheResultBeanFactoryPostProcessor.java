package com.account.external.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


@Component
public class CacheResultBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CacheResultBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype() &&
                    beanFactory.getBean(beanName).getClass().isAnnotationPresent(CacheResult.class)) {
                logger.info("Bean with prototype scope and @CacheResult found: " + beanName);
            }
        }
    }
}
