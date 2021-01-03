package se.sylwan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.sylwan.exception.ResourceAlreadyExsistException;
import se.sylwan.model.Airport;
import se.sylwan.repository.AirportRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TravelController {
	
	
	@Autowired
	private AirportRepository airportRepository;
	
	@GetMapping("/airports/all")
	public List<Airport> getAllAirports()
	{
		return airportRepository.findAll();
	}
	
	@PostMapping("/airport/new")
	public Airport registerNewAirport(@RequestBody Airport airport)
	{
		if(airportRepository.existsById(airport.getName()))
		{
			throw new ResourceAlreadyExsistException("Airport already exsist: " + airport.getName()); 
		}
		
		airportRepository.save(airport);
		
		return airport;
	}

}
