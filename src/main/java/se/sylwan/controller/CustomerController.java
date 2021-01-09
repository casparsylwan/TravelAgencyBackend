package se.sylwan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import se.sylwan.exception.ResourceAlreadyExsistException;
import se.sylwan.exception.ResourceNotFoundException;
import se.sylwan.model.Customer;
import se.sylwan.model.Travel;
import se.sylwan.repository.CustomerRepository;
import se.sylwan.repository.TravelRepositiory;
import se.sylwan.security.AuthenticationRequest;
import se.sylwan.security.AuthenticationResponse;
import se.sylwan.security.CustomerDetailsService;
import se.sylwan.security.JwtUtil;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {
		
	@Autowired
	private AuthenticationManager autenticationManger;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TravelRepositiory travelRepository;
	
	@GetMapping("/customer/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email)
	{
		
		Customer customer = customerRepository.findById(email)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with email could not be found: " + email));
		return ResponseEntity.ok(customer);
	}
	
	@GetMapping("/customers/all")
	public List<Customer> getAllCustomers()
	{		
		return customerRepository.findAll();
	}
	
	@PostMapping("/customer/update")
	public Customer updateCustomer(@RequestBody Customer customerUpdate)
	{
		
		System.out.println("CASPAR CASPAR");
		Customer customer = customerRepository.findByEmail(customerUpdate.getEmail()).
							orElseThrow(() -> new ResourceNotFoundException("Customer could not be found: Loggin again! "));
		
		travelRepository.saveAll(customerUpdate.getTravelOrders());
		
//		customer.getTravelOrders().addAll(customerUpdate.getTravelOrders());
//		for(Travel travel: customerUpdate.getTravelOrders())
//		{
//			travel.getCustomer().add(customer);
//			if( travelRepository.existsById(travel.getId()))
//			{
//				travelRepository.save(travel);
//			}  
//		}
		
		
//		customerRepository.save(customer);
		
							
		return customer;
	}
	
	
	
	@PostMapping("/customer/new")
	public Customer saveCustomer(@RequestBody Customer customer)
	{
		
		if(customerRepository.existsById(customer.getEmail()))
		{
			throw new ResourceAlreadyExsistException("Email already exsist take an other email" + customer.getEmail());
		}
		customerRepository.save(customer);
		
		return customer;
	}
	
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			autenticationManger.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);			
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = customerDetailsService.loadUserByUsername(authenticationRequest.getUsername()); 
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	
	
	//Start server
	@GetMapping(value="/ping" , produces = MediaType.TEXT_PLAIN_VALUE)
	public String ping()
	{
		//customerRepository.save(new Customer("Cas","Cas","Cas","Cas"));
		return "PING";
	}

}
