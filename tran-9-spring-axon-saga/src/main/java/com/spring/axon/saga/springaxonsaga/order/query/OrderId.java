package com.spring.axon.saga.springaxonsaga.order.query;

import org.axonframework.common.Assert;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:22
 */
public class OrderId {
    
    private final String identifier;
    private final int hashCode;
    
    public OrderId(String identifier) {
        Assert.notNull(identifier, () -> "Identifier may not be null");
        this.identifier = identifier;
        this.hashCode = identifier.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        OrderId customerId = (OrderId) o;
        return identifier.equals(customerId.identifier);
        
    }
    
    @Override
    public int hashCode() {
        return hashCode;
    }
    
    @Override
    public String toString() {
        return identifier;
    }
    
}
