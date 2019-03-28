package com.spring.axon.saga.springaxonsaga.order;

import com.spring.axon.saga.springaxonsaga.order.command.OrderCreateCommand;
import com.spring.axon.saga.springaxonsaga.order.query.OrderEntity;
import com.spring.axon.saga.springaxonsaga.order.query.OrderEntityRepository;
import com.spring.axon.saga.springaxonsaga.order.query.OrderId;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:28
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;
    @Autowired
    private OrderEntityRepository orderEntityRepository;
    
    @PostMapping("")
    public void create(@RequestBody Order order) {
        UUID orderId = UUID.randomUUID();
        OrderCreateCommand command = new OrderCreateCommand(orderId.toString(), order.getCustomerId(),
            order.getTitle(), order.getTicketId(), order.getAmount());
        commandGateway.send(command, LoggingCallback.INSTANCE);
    }
    
    @GetMapping("/query/{orderId}")
    public Order get(@PathVariable String orderId) throws ExecutionException, InterruptedException {
        return queryGateway.query(new OrderId(orderId), Order.class).get();
    }
    
    @GetMapping("/{orderId}")
    public OrderEntity getView(@PathVariable String orderId) {
        return orderEntityRepository.getOne(orderId);
    }
}

