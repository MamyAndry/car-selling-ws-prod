package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "photo")
public class Photo {

	@Column(name = "id_car")
	String idCar;
	@Id
	@Column(name = "id")
	String id;
	@Column(name = "picture")
	String picture;




	public Photo(){}

	public String getIdCar(){
		return this.idCar;
	}
	public void setIdCar(String idCar){
		this.idCar = idCar;
	}
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getPicture(){
		return this.picture;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}


}
