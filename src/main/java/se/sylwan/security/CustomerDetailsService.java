package se.sylwan.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import se.sylwan.model.Customer;
import se.sylwan.repository.CustomerRepository;

@Service
public class CustomerDetailsService implements UserDetailsService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<Customer> customer = customerRepository.findById(userName);
		System.out.println("Customer");
		customer.orElseThrow(() -> new UsernameNotFoundException("Email not found: " + userName));
		
		return customer.map(CustomersDetail::new).get();
	}

}
