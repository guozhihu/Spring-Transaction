package com.spring.axon.tran9springaxon.account.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Author: zhihu
 * Description: 创建账户的业务Command
 * Date: Create in 2019/3/26 14:30
 */
public class AccountCreateCommand {
    
    @TargetAggregateIdentifier
    private String accountId;
    
    private String name;
    
    public AccountCreateCommand(String accountId, String name) {
        this.accountId = accountId;
        this.name = name;
    }
    
    public String getAccountId() {
        return accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
