package se.sylwan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.sylwan.exception.ResourceNotFoundException;
import se.sylwan.model.Customer;
import se.sylwan.repository.CustomerRepository;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/customer/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email)
	{
		Customer customer = customerRepository.findById(email)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with email could not be found: " + email));
		
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping("/customer/new")
	public void saveCustomer(@RequestBody Customer customer)
	{
		customerRepository.save(customer);
	}
	
	@GetMapping("/ping")
	public String ping()
	{
		return "PING";
	}

}
