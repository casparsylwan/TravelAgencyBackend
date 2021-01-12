package se.sylwan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class Seat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
		
	private Integer seatNumber;
	
	@ManyToOne
	@JoinColumn(name = "passanger")
	@JsonIgnoreProperties({"travelOrders"})
	private Customer passanger;
	
	@ManyToOne
	@JoinColumn(name = "travel")
	private Travel travel; 
	
	public Seat() 
	{
		super();
	}

	public Seat(Integer id, Integer seatNumber) {
		super();
		this.id = id;
		this.seatNumber = seatNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Travel getTravel() {
		return travel;
	}

	public void setTravel(Travel travel) {
		this.travel = travel;
	}

	public Customer getPassanger() {
		return passanger;
	}

	public void setPassanger(Customer passanger) {
		this.passanger = passanger;
	}
	
}
