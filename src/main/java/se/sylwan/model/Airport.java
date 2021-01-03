package se.sylwan.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity()
public class Airport {
	
	@Id
	private String name;
	
	private String country;
	
	private String city;
	
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

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
