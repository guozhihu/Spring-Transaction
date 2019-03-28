package com.spring.axon.saga.springaxonsaga.customer.event;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:06
 */
public class CustomerCreatedEvent {
    
    private String customerId;
    private String name;
    private String password;
    
    public CustomerCreatedEvent() {
    }
    
    public CustomerCreatedEvent(String customerId, String name, String password) {
        this.customerId = customerId;
        this.name = name;
        this.password = password;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    
}

