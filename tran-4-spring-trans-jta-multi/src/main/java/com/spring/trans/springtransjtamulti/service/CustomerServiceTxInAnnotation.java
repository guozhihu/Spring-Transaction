package com.spring.trans.springtransjtamulti.service;

import com.spring.trans.springtransjtamulti.dao.CustomerRepository;
import com.spring.trans.springtransjtamulti.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: zhihu
 * Description: 声明式事务-标签方式实现事务
 * Date: Create in 2019/3/10 1:41
 */
@Service
public class CustomerServiceTxInAnnotation {
    
    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceTxInAnnotation.class);
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JmsTemplate jmsTemplate;
    
    // 使用注解的方式声明事务存在
    @Transactional
    public Customer create(Customer customer) {
        LOG.info("CustomerService In Annotation create customer:{}", customer.getUsername());
        if (customer.getId() != null) {
            throw new RuntimeException("用户已经存在");
        }
        customer.setUsername("Annotation:" + customer.getUsername());
        jmsTemplate.convertAndSend("customer:msg:reply", customer.getUsername() + " created.");
        return customerRepository.save(customer);
    }
    
    @Transactional
    @JmsListener(destination = "customer:msg:new")
    public void createByListener(String name) {
        LOG.info("CustomerService In Annotation by Listener create customer:{}", name);
        Customer customer = new Customer();
        customer.setUsername("Annotation: " + name);
        customer.setRole("USER");
        customer.setPassword("111111");
        customerRepository.save(customer);
        if (name.contains("error")) {
            System.out.println(10 / 0);
        }
        jmsTemplate.convertAndSend("customer:msg:reply", customer.getUsername() + " created.");
    }
}
