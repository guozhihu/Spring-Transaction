package com.spring.axon.tran9springaxon.account.event;

/**
 * Author: zhihu
 * Description: 创建账户的事件Event
 * Date: Create in 2019/3/26 14:32
 */
public class AccountCreatedEvent {
    private String accountId;
    private String name;
    
    public AccountCreatedEvent(String accountId, String nam) {
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
