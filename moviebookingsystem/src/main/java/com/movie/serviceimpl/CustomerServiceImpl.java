package com.movie.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.Customer;
import com.movie.repository.CustomerRepository;
import com.movie.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Integer isCustomerPresent(Customer customer) {
		 Customer customer1 = customerRepository.getCustomerByEmailAndName(customer.getEmail(),customer.getName());
	     return customer1!=null ? customer1.getId(): null ;
	}
}
