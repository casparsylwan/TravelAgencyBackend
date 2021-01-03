package se.sylwan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.sylwan.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, String>{
	
	List<Airport> findByNameIgnoreCaseContaining(String name);
	
	List<Airport> findByCountryIgnoreCaseContaining(String country);
	
	List<Airport> findByCityIgnoreCaseContaining(String city);
	

}
