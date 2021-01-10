package se.sylwan.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import se.sylwan.exception.ResourceAlreadyExsistException;
import se.sylwan.exception.ResourceNotFoundException;
import se.sylwan.model.Airport;
import se.sylwan.model.Plane;
import se.sylwan.model.Seat;
import se.sylwan.model.Travel;
import se.sylwan.repository.AirportRepository;
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
	
	@GetMapping("/aiplanes/all")
	public List<Plane> getAllPlanes()
	{
		return planeRepository.findAll();
	}
	
	@PostMapping("/airplane/new")
	public Plane createPlane(@RequestBody Plane plane)
	{
		
		if(planeRepository.existsById(plane.getId()))
		{
			throw new ResourceAlreadyExsistException("Plane already exsist: " + plane.getName() + " with id."); 
		}
		
		planeRepository.save(plane);
		
		return plane;
	}
	
	@PostMapping("/Seat/new/{planeId}")
	public Plane createSeat(@PathVariable Integer planeId, @RequestBody Seat seat)
	{
		Plane plane = planeRepository.findById(planeId).orElseThrow(() -> new ResourceNotFoundException("Wrong plane id: " + planeId));
		
		if(seatRepository.existsById(seat.getId()))
		{
			throw new ResourceAlreadyExsistException("Seat already exsist: " + seat.getSeatNumber() + " with id."); 
		}
		
		seatRepository.save(seat);
		plane.getSeat().add(seat);
		planeRepository.save(plane);
		
		return plane;
	}
	
	@GetMapping("/travel/all")
	public List<Travel> getAllTravel()
	{
		return travelRepositiory.findAll();
	}
	
	@PostMapping("/travel/new")
	public Travel createNewTravel(@RequestBody Travel travel)
	{
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
