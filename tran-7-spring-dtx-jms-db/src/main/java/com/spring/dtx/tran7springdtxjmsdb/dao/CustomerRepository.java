package com.spring.dtx.tran7springdtxjmsdb.dao;

import com.spring.dtx.tran7springdtxjmsdb.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByUsername(String username);
}