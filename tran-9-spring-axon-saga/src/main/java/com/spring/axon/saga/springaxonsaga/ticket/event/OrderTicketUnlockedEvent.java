package com.spring.axon.saga.springaxonsaga.ticket.event;

public class OrderTicketUnlockedEvent {

    private String ticketId;

    public OrderTicketUnlockedEvent(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}