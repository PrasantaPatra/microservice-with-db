package com.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping("/addCustomer")
    public void save(@RequestBody Customer customer) {
        repository.save(customer);
    }

    @GetMapping("/findAll")
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        list = repository.findAll();
        return list;
    }

    @GetMapping("/getby/{firstname}")
    public Customer findByFirstName(@PathVariable String firstname) {
        return repository.findByFirstName(firstname);
    }

    @PutMapping("/updateCustomer")
    public void update(@RequestBody Customer customer) {
        repository.save(customer);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

}
