package com.movie.service;

import com.movie.entity.Customer;

public interface CustomerService {
	
	Customer saveCustomer(Customer customer);
	Integer isCustomerPresent(Customer customer);
}
