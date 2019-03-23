package com.spring.trans.springtransjta.service;

import com.spring.trans.springtransjta.dao.CustomerRepository;
import com.spring.trans.springtransjta.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Author: zhihu
 * Description: 编程式事务-代码方式实现事务
 * Date: Create in 2019/3/10 1:46
 */
@Service
public class CustomerServiceTxInCode {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceTxInCode.class);
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    public Customer create(Customer customer) {
        LOG.info("CustomerService In Code create customer:{}", customer.getUsername());
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // 设置事务隔离级别
        // def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        // 设置事务传播行为
         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
        // def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 设置事务超时时间
        def.setTimeout(15);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            customer.setUsername("Code:" + customer.getUsername());
            customerRepository.save(customer);
            if (customer.getId() != null) {
                throw new RuntimeException("用户已经存在");
            }
            transactionManager.commit(status);
            return customer;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
