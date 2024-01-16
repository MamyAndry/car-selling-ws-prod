package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "transmission")
public class Transmission {

	@Id
	@Column(name = "id_transmission")
	String idTransmission;
	@Column(name = "name")
	String name;




	public Transmission(){}

	public String getIdTransmission(){
		return this.idTransmission;
	}
	public void setIdTransmission(String idTransmission){
		this.idTransmission = idTransmission;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}


}
