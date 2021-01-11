package se.sylwan.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class Plane {
	
	@Id
	private String name;
	
	private Integer numberOfSeats ;
	
	@JsonIgnoreProperties({"plane"})
	@OneToMany(mappedBy = "plane")
	private List<Travel> travelDestinations;
	
//	@OneToOne(fetch=FetchType.LAZY, mappedBy = "plane")
//	private Travel TravelDestination;
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy = "plane")
//	@JsonIgnore//travelSeat
//	private List<Seat> seat;

	public Plane() {
		super();
	}

	public Plane( Integer numberOfSeats) {
		super();
		this.numberOfSeats = numberOfSeats;
		
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


//	public Travel getTravelDestination() {
//		return TravelDestination;
//	}
//
//	public void setTravelDestination(Travel travelDestination) {
//		TravelDestination = travelDestination;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Travel> getTravelDestinations() {
		return travelDestinations;
	}

	public void setTravelDestinations(List<Travel> travelDestinations) {
		this.travelDestinations = travelDestinations;
	}

//	public List<Seat> getSeat() {
//		return seat;
//	}
//
//	public void setSeat(List<Seat> seat) {
//		this.seat = seat;
//	} 
	
}
