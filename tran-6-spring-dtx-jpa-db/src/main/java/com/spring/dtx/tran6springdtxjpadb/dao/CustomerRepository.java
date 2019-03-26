package com.spring.dtx.tran6springdtxjpadb.dao;

import com.spring.dtx.tran6springdtxjpadb.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/21 11:16
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findOneByUsername(String username);
}
