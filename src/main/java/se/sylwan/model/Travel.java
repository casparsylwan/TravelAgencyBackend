package se.sylwan.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Travel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@ManyToMany
	@JoinTable(name="customer_travel",
    joinColumns=@JoinColumn(name="email"),
    inverseJoinColumns=@JoinColumn(name="travel_id"))
	private List<Customer> customer;
	
	@ManyToOne
	@JoinColumn(name = "departure")
	private Airport fromAirport;
	
	@ManyToOne
	@JoinColumn(name = "arrival")
	private Airport toAirport;
	
	@Column(columnDefinition = "TIMESTAMP")
	private Date depatureDate;

	public Travel() {
		super();
	}

	public Travel(long id, Airport fromAirport, Airport toAirport, Date depatureDate) {
		super();
		this.id = id;
		this.fromAirport = fromAirport;
		this.toAirport = toAirport;
		this.depatureDate = depatureDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Airport getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(Airport fromAirport) {
		this.fromAirport = fromAirport;
	}

	public Airport getToAirport() {
		return toAirport;
	}

	public void setToAirport(Airport toAirport) {
		this.toAirport = toAirport;
	}

	public Date getDepatureDate() {
		return depatureDate;
	}

	public void setDepatureDate(Date depatureDate) {
		this.depatureDate = depatureDate;
	}

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
	
	
	
}
