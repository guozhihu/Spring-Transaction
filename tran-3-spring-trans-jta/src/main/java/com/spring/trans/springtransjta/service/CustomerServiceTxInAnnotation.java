package com.spring.trans.springtransjta.service;

import com.spring.trans.springtransjta.dao.CustomerRepository;
import com.spring.trans.springtransjta.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 
    // 使用注解的方式声明事务存在
    @Transactional
    public Customer create(Customer customer) {
        LOG.info("CustomerService In Annotation create customer:{}", customer.getUsername());
        if (customer.getId() != null) {
            throw new RuntimeException("用户已经存在");
        }
        customer.setUsername("Annotation:" + customer.getUsername());
        return customerRepository.save(customer);
    }
}
