package com.spring.axon.saga.springaxonsaga.order.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:20
 */
public class OrderFailCommand {
    
    @TargetAggregateIdentifier
    private String orderId;
    
    private String reason;
    
    public OrderFailCommand(String orderId, String reason) {
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

