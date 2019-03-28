package com.spring.axon.saga.springaxonsaga.order.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:19
 */
public class OrderFinishCommand {
    
    @TargetAggregateIdentifier
    private String orderId;
    
    
    public OrderFinishCommand(String orderId) {
        this.orderId = orderId;
    }
    
    public String getOrderId() {
        return orderId;
    }
}
