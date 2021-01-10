package se.sylwan.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Seat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JsonIgnoreProperties({"seat"})
	private Plane plane;
	
	private Integer seatNumber;
	
	@ManyToOne
	@JsonIgnoreProperties({"travelSeat"})
	private Customer customerInTheSeat;
	
	public Seat() 
	{
		super();
	}

	public Seat(Integer id, Plane plane, Integer seatNumber) {
		super();
		this.id = id;
		this.plane = plane;
		this.seatNumber = seatNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Customer getCustomerInTheSeat() {
		return customerInTheSeat;
	}

	public void setCustomerInTheSeat(Customer customerInTheSeat) {
		this.customerInTheSeat = customerInTheSeat;
	}
	
	
}
