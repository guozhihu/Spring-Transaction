package com.spring.dtx.tran6springdtxjpadb.web;

import com.spring.dtx.tran6springdtxjpadb.domain.Order;
import com.spring.dtx.tran6springdtxjpadb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/20 23:04
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
    
    @Autowired
    private CustomerService customerService;
    
    @PostMapping("/order")
    public void create(@RequestBody Order order) {
        customerService.createOrder(order);
    }
    
    @GetMapping("/{id}")
    public Map userInfo(@PathVariable Long id) {
        return customerService.userInfo(id);
    }
    
}
