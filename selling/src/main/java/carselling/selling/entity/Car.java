package carselling.selling.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "car")
public class Car {


	@Id
	@Column(name = "id_car")
	String idCar;
	@ManyToOne
	@JoinColumn(name = "id_transmission")
	Transmission transmission;
	@ManyToOne
	@JoinColumn(name = "id_model_fuel_type")
	ModelFuelType modelFuelType;
	@Column(name = "door_number")
	Integer doorNumber;
	@ManyToOne
	@JoinColumn(name = "id_model_motor")
	ModelMotor modelMotor;
	@Column(name = "kilometrage")
	BigDecimal kilometrage;
	@Column(name = "id_car_status")
	Integer idCarStatus;
	@ManyToOne
	@JoinColumn(name = "id_users")
	User user;
	@ManyToOne
	@JoinColumn(name = "id_model_gear_box")
	ModelGearBox modelGearBox;
	@Column(name = "status")
	Integer status;
	@ManyToOne
	@JoinColumn(name = "id_model")
	Model model;
	@Column
	String color;

	public Car(){}

	public Transmission getTransmission(){
		return this.transmission;
	}
	public void setTransmission(Transmission transmission){
		this.transmission = transmission;
	}
	public ModelFuelType getModelFuelType(){
		return this.modelFuelType;
	}
	public void setModelFuelType(ModelFuelType modelFuelType){
		this.modelFuelType = modelFuelType;
	}
	public Integer getDoorNumber(){
		return this.doorNumber;
	}
	public void setDoorNumber(Integer doorNumber){
		this.doorNumber = doorNumber;
	}
	public ModelMotor getModelMotor(){
		return this.modelMotor;
	}
	public void setModelMotor(ModelMotor modelMotor){
		this.modelMotor = modelMotor;
	}
	public BigDecimal getKilometrage(){
		return this.kilometrage;
	}
	public void setKilometrage(BigDecimal kilometrage){
		this.kilometrage = kilometrage;
	}
	public Integer getIdCarStatus(){
		return this.idCarStatus;
	}
	public void setIdCarStatus(Integer idCarStatus){
		this.idCarStatus = idCarStatus;
	}
	public User getUser(){
		return this.user;
	}
	public void setIdUsers(User users){
		this.user = users;
	}
	public String getIdCar(){
		return this.idCar;
	}
	public void setIdCar(String idCar){
		this.idCar = idCar;
	}
	public ModelGearBox getModelGearBox(){
		return this.modelGearBox;
	}
	public void setIdModelGearBox(ModelGearBox modelGearBox){
		this.modelGearBox = modelGearBox;
	}
	public Integer getStatus(){
		return this.status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Model getModel(){
		return this.model;
	}
	public void setModel(Model model){
		this.model = model;
	}
	public String getColor(){
		return this.color;
	}
	public void setColor(String color){
		this.color = color;
	}

}
