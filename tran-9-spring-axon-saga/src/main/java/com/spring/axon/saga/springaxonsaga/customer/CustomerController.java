package com.spring.axon.saga.springaxonsaga.customer;

import com.spring.axon.saga.springaxonsaga.customer.command.CustomerChargeCommand;
import com.spring.axon.saga.springaxonsaga.customer.command.CustomerCreateCommand;
import com.spring.axon.saga.springaxonsaga.customer.command.CustomerDepositCommand;
import com.spring.axon.saga.springaxonsaga.customer.query.CustomerEntity;
import com.spring.axon.saga.springaxonsaga.customer.query.CustomerEntityRepository;
import com.spring.axon.saga.springaxonsaga.customer.query.CustomerId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Author: zhihu
 * Description:
 * Date: Create in 2019/3/28 8:02
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
    
    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
    
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;
    @Autowired
    private CustomerEntityRepository customerRepository;
    
    @PostMapping("")
    public CompletableFuture<Object> create(@RequestParam String name, @RequestParam String password) {
        LOG.info("Request to create account for: {}", name);
        UUID accountId = UUID.randomUUID();
        CustomerCreateCommand createCustomerCommand = new CustomerCreateCommand(accountId.toString(), name, password);
        return commandGateway.send(createCustomerCommand);
    }
    
    @PutMapping("/{accountId}/deposit/{amount}")
    public CompletableFuture<Object> depositMoney(@PathVariable String accountId, @PathVariable Double amount) {
        LOG.info("Request to withdraw {} from account {} ", amount, accountId);
        return commandGateway.send(new CustomerDepositCommand(accountId, amount));
    }
    
    @PutMapping("/{accountId}/withdraw/{amount}")
    public CompletableFuture<Object> withdrawMoney(@PathVariable String accountId, @PathVariable Double amount) {
        LOG.info("Request to withdraw {} from account {} ", amount, accountId);
        return commandGateway.send(new CustomerChargeCommand(accountId, amount));
    }
    
    
    @GetMapping("/{accountId}")
    public CustomerEntity getCustomerById(@PathVariable String accountId) {
        LOG.info("Request Customer with id: {}", accountId);
        return customerRepository.getOne(accountId);
    }
    
    @GetMapping("")
    public List<CustomerEntity> getAllCustomers() {
        LOG.info("Request all Customers");
        return customerRepository.findAll();
    }
    
    @GetMapping("/query/{customerId}")
    public Customer getAggregate(CustomerId customerId) throws ExecutionException, InterruptedException {
        return queryGateway.query(customerId, Customer.class).get();
    }
}
