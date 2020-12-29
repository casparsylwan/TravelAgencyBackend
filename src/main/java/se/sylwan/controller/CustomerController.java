package se.sylwan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import se.sylwan.exception.ResourceNotFoundException;
import se.sylwan.model.Customer;
import se.sylwan.repository.CustomerRepository;
import se.sylwan.security.AuthenticationRequest;
import se.sylwan.security.AuthenticationResponse;
import se.sylwan.security.CustomerDetailsService;
import se.sylwan.security.CustomersDetail;
import se.sylwan.security.JwtUtil;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
		
	@Autowired
	private AuthenticationManager autenticationManger;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
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
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authticationRequest) throws Exception{
		try {
			autenticationManger.authenticate(
				new UsernamePasswordAuthenticationToken(authticationRequest.getUsername(), authticationRequest.getPassword())
				);			
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("Incorrect username or password", e);
		}
		final CustomersDetail customersDetail = (CustomersDetail) customerDetailsService.loadUserByUsername(authticationRequest.getUsername()); 
		final String jwt = jwtTokenUtil.generateToken(customersDetail);
		System.out.println(jwt);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	
	
	//Start server
	@GetMapping("/ping")
	public String ping()
	{
		customerRepository.save(new Customer("Cas","Cas","Cas","Cas"));
		return "PING";
	}

}
