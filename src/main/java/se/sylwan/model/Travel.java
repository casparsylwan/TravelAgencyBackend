package se.sylwan.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Travel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	
	@OneToOne
	@JoinColumn(name="plane")
	@JsonIgnoreProperties({"travelDestination"})
	private Plane plane;
	
	
	@ManyToOne
	@JoinColumn(name = "departure")
	private Airport fromAirport;
	
	@ManyToOne
	@JoinColumn(name = "arrival")
	private Airport toAirport;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Timestamp depatureDate;
	
	private Integer price;
	
	private boolean paid  = false;

	public Travel() {
		super();
	}

	public Travel(long id, Airport fromAirport, Airport toAirport, Timestamp depatureDate, Integer price) {
		super();
		this.id = id;
		this.fromAirport = fromAirport;
		this.toAirport = toAirport;
		this.depatureDate = depatureDate;
		this.price = price;
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

	public Timestamp getDepatureDate() {
		return depatureDate;
	}

	public void setDepatureDate(Timestamp depatureDate) {
		this.depatureDate = depatureDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}
			
}
