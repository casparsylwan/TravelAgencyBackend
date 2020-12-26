package se.sylwan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sylwan.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	
	List<Customer> findByFirstName(String name);
	
	List<Customer> findByLastName(String name);

}
