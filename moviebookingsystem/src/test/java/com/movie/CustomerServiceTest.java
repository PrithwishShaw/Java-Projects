package com.movie;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.movie.entity.Customer;
import com.movie.repository.CustomerRepository;
import com.movie.service.CustomerService;

@SpringBootTest
class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;

	@Test
	void saveCustomer()
	{
		Customer customer= new Customer();
		customer.setId(1);
		customer.setName("Prithwish");
		customer.setEmail("prithvi@gmail.com");
		
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		assertThat(customerService.saveCustomer(customer)).isEqualTo(customer);
		
	}
	
	@Test
	void isCustomerPresent()
	{
		Customer customer= new Customer();
		customer.setId(1);
		customer.setName("Prithwish");
		customer.setEmail("prithvi@gmail.com");
		
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		assertThat(customerService.isCustomerPresent(customer)).isNull();
	}
}
