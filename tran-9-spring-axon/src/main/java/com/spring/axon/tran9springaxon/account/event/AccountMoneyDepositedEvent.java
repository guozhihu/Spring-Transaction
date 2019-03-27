package com.spring.axon.tran9springaxon.account.event;

/**
 * Author: zhihu
 * Description: 存款的事件Event
 * Date: Create in 2019/3/26 14:31
 */
public class AccountMoneyDepositedEvent {
    private String accountId;
    
    private Double amount;
    
    public AccountMoneyDepositedEvent(String accountId, Double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
