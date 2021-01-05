package se.sylwan.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity()
public class Airport {
	
	@Id
	private String name;
	
	private String country;
	
	private String city;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "fromAirport")
	@JsonIgnore
	private  List<Travel> travelDepartures;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "toAirport")
	@JsonIgnore
	private List<Travel> travelArrival;
	
	
	
	public Airport() {
		super();
	}

	public Airport(String name, String country, String city) {
		super();
		this.name = name;
		this.country = country;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Travel> getTravelDepartures() {
		return travelDepartures;
	}

	public void setTravelDepartures(List<Travel> travelDepartures) {
		this.travelDepartures = travelDepartures;
	}

	public List<Travel> getTravelArrival() {
		return travelArrival;
	}

	public void setTravelArrival(List<Travel> travelArrival) {
		this.travelArrival = travelArrival;
	}

	
}
