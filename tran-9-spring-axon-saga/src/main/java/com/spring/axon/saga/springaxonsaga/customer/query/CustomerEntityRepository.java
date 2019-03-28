package com.spring.axon.saga.springaxonsaga.customer.query;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:11
 */
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, String> {
}
