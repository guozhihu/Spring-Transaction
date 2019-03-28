package com.spring.axon.saga.springaxonsaga.customer.event;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:08
 */
public class OrderPayFailedEvent {
    
    private String orderId;
    
    public OrderPayFailedEvent(String orderId) {
        this.orderId = orderId;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    @Override
    public String toString() {
        return "OrderPayFailedEvent{" +
            "orderId='" + orderId + '\'' +
            '}';
    }
}
