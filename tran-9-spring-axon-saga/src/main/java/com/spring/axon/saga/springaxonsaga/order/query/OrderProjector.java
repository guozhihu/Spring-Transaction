package com.spring.axon.saga.springaxonsaga.order.query;

import com.spring.axon.saga.springaxonsaga.order.event.OrderCreatedEvent;
import com.spring.axon.saga.springaxonsaga.order.event.OrderFailedEvent;
import com.spring.axon.saga.springaxonsaga.order.event.OrderFinishedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:26
 */
@Service
public class OrderProjector {
    
    @Autowired
    private OrderEntityRepository repository;
    
    @EventHandler
    public void on(OrderCreatedEvent event) {
        OrderEntity order = new OrderEntity();
        order.setId(event.getOrderId());
        order.setCustomerId(event.getCustomerId());
        order.setTicketId(event.getTicketId());
        order.setAmount(event.getAmount());
        order.setCreatedDate(event.getCreatedDate());
        order.setTitle(event.getTitle());
        order.setStatus("NEW");
        repository.save(order);
    }
    
    @EventHandler
    public void on(OrderFinishedEvent event) {
        OrderEntity order = repository.getOne(event.getOrderId());
        order.setStatus("FINISH");
        repository.save(order);
    }
    
    @EventHandler
    public void on(OrderFailedEvent event) {
        OrderEntity order = repository.getOne(event.getOrderId());
        order.setStatus("FAILED");
        order.setReason(event.getReason());
        repository.save(order);
    }
}

