package se.sylwan.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity()
public class Customer {
	
	@Id
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String roles;
	
	@Column(name="active_member")
	private boolean active = true;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "passanger")
	private List<Seat> travelOrders;
		
	public Customer() {}

	public Customer(String email, String password, String firstName, String lastName, String roles, boolean active) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
		this.active = active;
	}
	
	public Customer(String email, String password, String firstName, String lastName, boolean active) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = "CUSTOMER";
		this.active = active;
	}
	
	public Customer(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = "CUSTOMER";
		this.active = true;
	}
	
	public Customer(String email, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = "null";
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = "CUSTOMER";
		this.active = true;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getRoles() {
		return roles;
	}



	public void setRoles(String roles) {
		this.roles = roles;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Seat> getTravelOrders() {
		return travelOrders;
	}

	public void setTravelOrders(List<Seat> travelOrders) {
		this.travelOrders = travelOrders;
	}

}
