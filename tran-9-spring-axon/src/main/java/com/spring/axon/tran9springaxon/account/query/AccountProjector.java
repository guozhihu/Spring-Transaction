package com.spring.axon.tran9springaxon.account.query;

import com.spring.axon.tran9springaxon.account.event.AccountCreatedEvent;
import com.spring.axon.tran9springaxon.account.event.AccountMoneyDepositedEvent;
import com.spring.axon.tran9springaxon.account.event.AccountMoneyWithdrawnEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/26 14:32
 */
@Service
public class AccountProjector {
    
    @Autowired
    private AccountEntityRepository accountEntityRepository;
    
    @EventHandler
    public void on(AccountCreatedEvent event) {
        AccountEntity account = new AccountEntity(event.getAccountId(), event.getName());
        accountEntityRepository.save(account);
    }
    
    @EventHandler
    public void on(AccountMoneyDepositedEvent event) {
        String accountId = event.getAccountId();
        AccountEntity accountView = accountEntityRepository.getOne(accountId);
        accountView.setDeposit(accountView.getDeposit() + event.getAmount());
        accountEntityRepository.save(accountView);
    }
    
    @EventHandler
    public void on(AccountMoneyWithdrawnEvent event) {
        String accountId = event.getAccountId();
        AccountEntity accountView = accountEntityRepository.getOne(accountId);
        accountView.setDeposit(accountView.getDeposit() - event.getAmount());
        accountEntityRepository.save(accountView);
    }
}

