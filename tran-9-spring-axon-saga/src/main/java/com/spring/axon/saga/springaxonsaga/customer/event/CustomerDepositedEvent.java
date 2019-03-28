package com.spring.axon.saga.springaxonsaga.customer.event;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:07
 */
public class CustomerDepositedEvent {
    
    private String customerId;
    private Double amount;
    
    public CustomerDepositedEvent() {
    }
    
    public CustomerDepositedEvent(String customerId, Double amount) {
        this.customerId = customerId;
        this.amount = amount;
    }
    
    public String getCustomerId() {
        return customerId;
    }
    
    public Double getAmount() {
        return amount;
    }
    
}

