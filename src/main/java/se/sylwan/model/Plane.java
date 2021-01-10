package se.sylwan.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Plane {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Integer numberOfSeats ;
	
	@OneToOne(mappedBy = "plane")
	private Travel TravelDestination;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "plane")
	private List<Seat> seat;

	public Plane() {
		super();
	}

	public Plane(Integer id, Integer numberOfSeats) {
		super();
		this.id = id;
		this.numberOfSeats = numberOfSeats;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


	public Travel getTravelDestination() {
		return TravelDestination;
	}

	public void setTravelDestination(Travel travelDestination) {
		TravelDestination = travelDestination;
	} 
	
	
	
}
