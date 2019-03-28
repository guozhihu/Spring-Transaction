package com.spring.axon.saga.springaxonsaga.customer.event;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:08
 */
public class CustomerChargedEvent {
    
    private String customerId;
    
    private Double amount;
    
    public CustomerChargedEvent() {
    }
    
    public CustomerChargedEvent(String customerId, Double amount) {
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
