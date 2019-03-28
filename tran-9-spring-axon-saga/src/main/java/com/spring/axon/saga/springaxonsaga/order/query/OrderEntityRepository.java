package com.spring.axon.saga.springaxonsaga.order.query;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:23
 */
public interface OrderEntityRepository extends JpaRepository<OrderEntity, String> {
}
