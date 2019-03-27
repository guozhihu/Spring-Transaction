package com.spring.axon.tran9springaxon.account.query;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/26 14:33
 */
@Entity(name = "tb_account")
public class AccountEntity {
    
    @Id
    private String id;
    private String name;
    
    private Double deposit;
    
    public AccountEntity() {
    }
    
    public AccountEntity(String id, String name) {
        this.id = id;
        this.name = name;
        this.deposit = 0d;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getDeposit() {
        return deposit;
    }
    
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
}

