package com.spring.cloud.axon.ticket.event.saga;

public class OrderTicketPreservedEvent {

    private String orderId;
    private String customerId;
    private String ticketId;

    public OrderTicketPreservedEvent(String orderId, String customerId, String ticketId) {
        this.orderId = orderId;
        this.ticketId = ticketId;
        this.customerId = customerId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getOrderId() {
        return orderId;
    }

}
