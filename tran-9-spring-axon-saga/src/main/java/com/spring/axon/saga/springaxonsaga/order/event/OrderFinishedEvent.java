package com.spring.axon.saga.springaxonsaga.order.event;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:21
 */
public class OrderFinishedEvent {
    private String orderId;
    
    public OrderFinishedEvent() {
    }
    
    public OrderFinishedEvent(String orderId) {
        this.orderId = orderId;
    }
    
    public String getOrderId() {
        return orderId;
    }
}
