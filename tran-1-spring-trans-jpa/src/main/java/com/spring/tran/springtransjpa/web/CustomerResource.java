package com.spring.tran.springtransjpa.web;

import com.spring.tran.springtransjpa.dao.CustomerRepository;
import com.spring.tran.springtransjpa.domain.Customer;
import com.spring.tran.springtransjpa.service.CustomerServiceTxInAnnotation;
import com.spring.tran.springtransjpa.service.CustomerServiceTxInCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    // @Transactional
    public Customer createInCode(@RequestBody Customer customer) {
        LOG.info("CustomerResource create in code create customer:{}", customer.getUsername());
        return customerServiceInCode.create(customer);
    }

    @GetMapping("")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

}
