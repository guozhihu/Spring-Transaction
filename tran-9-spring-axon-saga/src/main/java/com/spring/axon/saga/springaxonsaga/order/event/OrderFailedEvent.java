package com.spring.axon.saga.springaxonsaga.order.event;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:21
 */
public class OrderFailedEvent {
    private String reason;
    private String orderId;
    
    public OrderFailedEvent() {
    }
    
    public OrderFailedEvent(String orderId, String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public String getReason() {
        return reason;
    }
}
