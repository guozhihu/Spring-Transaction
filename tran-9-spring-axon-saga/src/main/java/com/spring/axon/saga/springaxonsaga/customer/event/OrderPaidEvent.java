package com.spring.axon.saga.springaxonsaga.customer.event;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:07
 */
public class OrderPaidEvent {
    
    private String orderId;
    private String customerId;
    private Double amount;
    
    public OrderPaidEvent() {
    }
    
    public OrderPaidEvent(String orderId, String customerId, Double amount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public String getCustomerId() {
        return customerId;
    }
}

