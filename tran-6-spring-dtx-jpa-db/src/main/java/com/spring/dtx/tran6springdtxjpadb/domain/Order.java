package com.spring.dtx.tran6springdtxjpadb.domain;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/20 22:05
 */
public class Order {
    
    private Long id;
    
    private Long customerId;
    
    private String title;
    
    private Integer amount;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getAmount() {
        return amount;
    }
    
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

