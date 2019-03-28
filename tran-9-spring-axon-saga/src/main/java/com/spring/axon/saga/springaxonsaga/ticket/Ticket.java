package com.spring.axon.saga.springaxonsaga.ticket;

import com.spring.axon.saga.springaxonsaga.ticket.command.OrderTicketMoveCommand;
import com.spring.axon.saga.springaxonsaga.ticket.command.OrderTicketPreserveCommand;
import com.spring.axon.saga.springaxonsaga.ticket.command.OrderTicketUnlockCommand;
import com.spring.axon.saga.springaxonsaga.ticket.command.TicketCreateCommand;
import com.spring.axon.saga.springaxonsaga.ticket.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Ticket {
    
    private static final Logger LOG = LoggerFactory.getLogger(Ticket.class);
    
    @AggregateIdentifier
    private String id;
    
    private String name;
    
    private String lockUser;
    
    private String owner;
    
    public Ticket() {
    }
    
    @CommandHandler
    public Ticket(TicketCreateCommand command) {
        apply(new TicketCreatedEvent(command.getTicketId(), command.getName()));
    }
    
    // 处理锁票的Command
    @CommandHandler
    public void handle(OrderTicketPreserveCommand command) {
        if (this.owner != null) { // 如果该票已经被人买走了，触发锁票失败事件
            LOG.error("Ticket is owned.");
            apply(new OrderTicketPreserveFailedEvent(command.getOrderId()));
        } else if (this.lockUser != null && this.lockUser.equals(command.getCustomerId())) { // 如果该票被自己锁了，即重复锁票请求，啥都不干
            LOG.info("duplicated command");
        } else if (this.lockUser == null) { // 该票还未被人锁住，触发锁票事件
            apply(new OrderTicketPreservedEvent(command.getOrderId(), command.getCustomerId(), command.getTicketId()));
        } else { // 其余情况，如该票被别人锁了，触发锁票失败事件
            apply(new OrderTicketPreserveFailedEvent(command.getOrderId()));
        }
    }
    
    @CommandHandler
    public void handle(OrderTicketUnlockCommand command) {
        if (this.lockUser == null) {
            LOG.error("Invalid command, ticket not locked");
        } else if (!this.lockUser.equals(command.getCustomerId())) {
            LOG.error("Invalid command, ticket not locked by:{}", command.getCustomerId());
        } else {
            apply(new OrderTicketUnlockedEvent(command.getTicketId()));
        }
    }
    
    // 锁票之后的交票操作
    @CommandHandler
    public void handle(OrderTicketMoveCommand command) {
        if (this.lockUser == null) { // 如果系统发生错误，交票时发现未锁票
            LOG.error("Invalid command, ticket not locked");
        } else if (!this.lockUser.equals(command.getCustomerId())) { // 发现锁票的用户不是自己
            LOG.error("Invalid command, ticket not locked by:{}", command.getCustomerId());
        } else {
            // 触发交票请求事件
            apply(new OrderTicketMovedEvent(command.getOrderId(), command.getTicketId(), command.getCustomerId()));
        }
    }
    
    @EventSourcingHandler
    public void onCreate(TicketCreatedEvent event) {
        this.id = event.getTicketId();
        this.name = event.getName();
        LOG.info("Executed event:{}", event);
    }
    
    @EventSourcingHandler
    public void onPreserve(OrderTicketPreservedEvent event) {
        this.lockUser = event.getCustomerId();
        LOG.info("Executed event:{}", event);
    }
    
    @EventSourcingHandler
    public void onUnlock(OrderTicketUnlockedEvent event) {
        this.lockUser = null;
        LOG.info("Executed event:{}", event);
    }
    
    @EventSourcingHandler
    public void onMove(OrderTicketMovedEvent event) {
        this.lockUser = null;
        this.owner = event.getCustomerId();
        LOG.info("Executed event:{}", event);
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLockUser() {
        return lockUser;
    }
    
    public String getOwner() {
        return owner;
    }
}
