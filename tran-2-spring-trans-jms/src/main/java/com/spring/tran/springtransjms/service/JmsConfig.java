package com.spring.tran.springtransjms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;


/**
 * Author: zhihu
 * Description:
 * 1.创建一个JmsTransactionManager作为PlatformTransactionManager的实现；
 * 2.将这个JmsTransactionManager配置到JmsTemplate和JmsListener中，程序中的所有JmsListener都将受这个JmsTransactionManager的控制；
 * Date: Create in 2019/3/16 23:26
 */
@EnableJms // 在官方文档中已经说明需要支持@JmsListener注解，则需要在任意@Configuration类添加@EnableJms注解，同时还需要配置DefaultJmsListenerContainerFactory的Bean实例
@Configuration
public class JmsConfig {
    
    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);
    
    @Bean
    public JmsTemplate initJmsTemplate(ConnectionFactory connectionFactory) {
        LOG.debug("init jms template with converter.");
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory); // JmsTemplate使用的connectionFactory跟JmsTransactionManager使用的必须是同一个，不能在这里封装成caching之类的。
        return template;
    }
    
    // 这个用于设置 @JmsListener使用的containerFactory
    @Bean
    public JmsListenerContainerFactory<?> msgFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer,
                                                     PlatformTransactionManager transactionManager) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setTransactionManager(transactionManager);
        factory.setCacheLevelName("CACHE_CONNECTION");
        factory.setReceiveTimeout(10000L);
        configurer.configure(factory, connectionFactory);
        return factory;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }
}
