package com.spring.trans.springtransjta.dao;

import com.spring.trans.springtransjta.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/10 1:38
 */
// 继承JpaRepository中的方法，其中已经包含基本的CRUD
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Customer findOneByUsername(String username);
}
