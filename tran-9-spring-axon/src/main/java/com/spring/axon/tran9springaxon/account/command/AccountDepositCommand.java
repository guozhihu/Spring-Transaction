package com.spring.axon.tran9springaxon.account.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import javax.validation.constraints.Min;

/**
 * Author: zhihu
 * Description: 存款的业务Command
 * Date: Create in 2019/3/26 14:29
 */
public class AccountDepositCommand {
    @TargetAggregateIdentifier
    private String accountId;
    
    @Min(value = 1, message = "金额最小为1")
    private Double amount;
    
    public AccountDepositCommand(String accountId, Double amount) {
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
