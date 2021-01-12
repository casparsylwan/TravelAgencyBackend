package se.sylwan.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.sylwan.exception.ResourceAlreadyExsistException;
import se.sylwan.exception.ResourceNotFoundException;
import se.sylwan.model.Airport;
import se.sylwan.model.Customer;
import se.sylwan.model.Plane;
import se.sylwan.model.Seat;
import se.sylwan.model.Travel;
import se.sylwan.repository.AirportRepository;
import se.sylwan.repository.CustomerRepository;
import se.sylwan.repository.PlaneRepository;
import se.sylwan.repository.SeatRepository;
import se.sylwan.repository.TravelRepositiory;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TravelController {
	
	@Autowired
	private TravelRepositiory travelRepositiory;
	
	@Autowired
	private AirportRepository airportRepository;
	
	@Autowired
	private PlaneRepository planeRepository;
	
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/airports/all")
	public List<Airport> getAllAirports()
	{
		return airportRepository.findAll();
	}
	
	@PostMapping("/airport/new")
	public Airport registerNewAirport(@RequestBody Airport airport)
	{
		
		airportRepository.save(airport);
		
		return airport;
	}
	
	@GetMapping("/airplane/{planeName}")
	public Plane getPlaneByName(@PathVariable String planeName)
	{
		 Plane plane = planeRepository.findById(planeName)
				 .orElseThrow(() -> new ResourceNotFoundException("Plane with name could not be found: id= " + planeName));
		return plane;
	}
	
	@GetMapping("/airplanes/all")
	public List<Plane> getAllPlanes()
	{
		return planeRepository.findAll();
	}
	
	@PostMapping("/airplane/new")
	public Plane createPlane(@RequestBody Plane plane)
	{
		
		planeRepository.save(plane);
		
		return plane;
	}
	
	@PostMapping("/seat/new/{travelId}")
	public Travel createSeat(@PathVariable long travelId, @RequestBody Seat seat)
	{
		Travel travel = travelRepositiory.findById(travelId)
				.orElseThrow(() -> new ResourceNotFoundException("Travel with id could not be found: id= " + travelId));
		seat.setTravel(travel);
		seatRepository.save(seat);
		travel.getPassangerList().add(seat);
		travelRepositiory.save(travel);	
		travel = travelRepositiory.findById(travel.getId()).orElseThrow(() -> new ResourceNotFoundException("Travel with id could not be found: id= " + travelId));
		return travel;
	}
	
	@GetMapping("/travel/all")
	public List<Travel> getAllTravel()
	{
		return travelRepositiory.findAll();
	}
	
	@PostMapping("/travel/customer")
	public List<Travel> getCustomersTravel(@RequestBody Iterable<Integer> seatList)
	{
		
		List<Travel> travelSet = new ArrayList<>(); 
		List<Seat> seats = seatRepository.findAllById(seatList);
		seats.forEach(seat -> travelSet.add(seat.getTravel()));
		
		return travelSet;
	}
	
	@GetMapping("/travel/customerorder/{email}")
	public List<Seat> getCustomersTravelByParam(@RequestParam String email)
	{
		
		Customer customer = customerRepository.findById(email)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with email could not be found: " + email));
		
		return customer.getTravelOrders();
	}
	
	@PostMapping("/travel/new")
	public Travel createNewTravel(@RequestBody Travel travel)
	{
		System.out.println("Hej!");
		travelRepositiory.save(travel);
		return travel;
	}
	
	@PostMapping("/travel/delete/{id}")
	public void deleteTravel(@RequestBody Travel travel, @PathVariable long id)
	{
		if(travel.getId() == id)
		{
			travelRepositiory.delete(travel);
		}
		else
		{
			throw new ResourceAlreadyExsistException("Travel id is wrong: " + travel.getId());
		}		
	}
}
