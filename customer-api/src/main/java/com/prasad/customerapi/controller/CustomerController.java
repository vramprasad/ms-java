package com.prasad.customerapi.controller;

import com.prasad.customerapi.model.Customer;
import com.prasad.customerapi.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/healthcheck")
    ResponseEntity<String> healthcheck() throws InterruptedException {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        log.info("Inside CustomerController --> healthcheck");
        String responseText = "customer-api healthcheck @ " + timeStamp + " - All OK";
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body(responseText.toString());
    }

    @GetMapping("/listAll")
    public List<Customer> getAllCustomers() {
        log.info("Inside CustomerController --> getAllCustomers");
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        log.info("Inside CustomerController --> getCustomer");
        return customerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/addCustomer")
    public ResponseEntity createCustomer(@RequestBody Customer customer) throws URISyntaxException {
        log.info("Inside CustomerController --> createCustomer");
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        log.info("Inside CustomerController --> updateCustomer");
        Customer savedCustomer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        log.info("Request customer : "+ customer.toString());
        log.info("From DB : "+ savedCustomer.toString());
        savedCustomer.setCustEmail(customer.getCustEmail());
        savedCustomer.setCustName(customer.getCustName());
        customerRepository.save(savedCustomer);
        log.info("Customer ID : "+ savedCustomer.getId() + " updated with new details");
        return ResponseEntity.ok(savedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        log.info("Inside CustomerController --> deleteCustomer");
        customerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
