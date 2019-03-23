package com.spring.trans.springtransjtamulti.web;

import com.spring.trans.springtransjtamulti.dao.CustomerRepository;
import com.spring.trans.springtransjtamulti.domain.Customer;
import com.spring.trans.springtransjtamulti.service.CustomerServiceTxInAnnotation;
import com.spring.trans.springtransjtamulti.service.CustomerServiceTxInCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/10 1:53
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerResource.class);
    
    @Autowired
    JmsTemplate jmsTemplate;
    
    @Autowired
    private CustomerServiceTxInAnnotation customerService;
    @Autowired
    private CustomerServiceTxInCode customerServiceInCode;
    @Autowired
    private CustomerRepository customerRepository;
    
    // 调用声明式事务实现的service层
    @PostMapping("/annotation")
    public Customer createInAnnotation(@RequestBody Customer customer) {
        LOG.info("CustomerResource create in annotation create customer:{}", customer.getUsername());
        return customerService.create(customer);
    }
    
    // 调用编程式事务实现的service层
    @PostMapping("/code")
    public Customer createInCode(@RequestBody Customer customer) {
        LOG.info("CustomerResource create in code create customer:{}", customer.getUsername());
        return customerServiceInCode.create(customer);
    }
    
    @Transactional
    @PostMapping("/message/annotation")
    public void createMsgWithListener(@RequestParam String userName) {
        jmsTemplate.convertAndSend("customer:msg:new", userName);
    }
    
    @Transactional
    @PostMapping("/message/code")
    public void createMsgDirect(@RequestParam String userName) {
        jmsTemplate.convertAndSend("customer:msg2:new", userName);
    }
    
    @GetMapping("")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
