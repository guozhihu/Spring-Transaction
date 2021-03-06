package com.spring.cloud.axon.user.event;

public class CustomerDepositedEvent {

    private String customerId;
    private Double amount;

    public CustomerDepositedEvent(String customerId, Double amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Double getAmount() {
        return amount;
    }

}
