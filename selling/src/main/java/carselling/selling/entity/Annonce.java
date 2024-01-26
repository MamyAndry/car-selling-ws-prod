package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Timestamp;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import carselling.selling.utils.IdGenerator;

@Entity
@Table(name = "annonce")
public class Annonce {

	@Id
	@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "ANN"),
		@Parameter(name = "sequence", value = "seq_annonce"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_annonce")
	String idAnnonce;
	@Column(name = "date_add", columnDefinition = "TIMESTAMP")
	Timestamp dateAdd;
	@Column(name = "date_validation")
	Timestamp dateValidation;
	@Column(name = "price")
	Double price;
	@Column(name = "description")
	String description;
	@Column(name = "status")
	Integer status;
	@ManyToOne
	@JoinColumn(name = "id_location")
	Location location;
	@ManyToOne
	@JoinColumn(name = "id_car")
	Car car;


	public Annonce(){}

	public String getIdAnnonce(){
		return this.idAnnonce;
	}
	public void setIdAnnonce(String idAnnonce){
		this.idAnnonce = idAnnonce;
	}
	public Timestamp getDateAdd(){
		return this.dateAdd;
	}
	public void setDateAdd(Timestamp dateAdd){
		this.dateAdd = dateAdd;
	}
	public Timestamp getDateValidation(){
		return this.dateValidation;
	}
	public void setDateValidation(Timestamp dateValidation){
		this.dateValidation = dateValidation;
	}
	public Double getPrice(){
		return this.price;
	}
	public void setPrice(Double price) throws Exception{
		if (price < 0) {
			throw new Exception("Price should not be negative");
		}
		this.price = price;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public Integer getStatus(){
		return this.status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	

}
