package se.sylwan.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;

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
		
		customer.orElseThrow(() -> new UsernameNotFoundException("Email not found: " + userName));
		Customer customerExist = null;
		
		if(customer.isPresent())
		{
			customerExist = customer.get();
		}
		
//		 customer.map(CustomersDetail::new).get();
		
		
		return new User(customerExist.getEmail(), customerExist.getPassword(),
                new ArrayList<>());
	}

}
