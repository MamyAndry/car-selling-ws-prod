package carselling.selling.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import carselling.selling.utils.IdGenerator;


@Entity
@Table(name = "car")
public class Car {

	@ManyToOne
	@JoinColumn(name = "id_transmission")
	Transmission transmission;
	@ManyToOne
	@JoinColumn(name = "id_model_fuel_type")
	ModelFuelType modelFuelType;
	@Column(name = "door_number")
	Integer doorNumber;
	@Column(name = "color")
	String color;
	@ManyToOne
	@JoinColumn(name = "id_model_motor")
	ModelMotor modelMotor;
	@Column(name = "kilometrage")
	Double kilometrage;
	@ManyToOne
	@JoinColumn(name = "id_car_status")
	CarStatus carStatus;
	@ManyToOne
	@JoinColumn(name = "id_users")
	User user;
	@Id
	@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "CAR"),
		@Parameter(name = "sequence", value = "seq_car"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_car")
	String idCar;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_model_gear_box")
	ModelGearBox modelGearBox;
	@ManyToOne
	@JoinColumn(name = "id_model")
	Model model;
	




	public Car(){}

	
	public Integer getDoorNumber(){
		return this.doorNumber;
	}
	public void setDoorNumber(Integer doorNumber){
		this.doorNumber = doorNumber;
	}
	public String getColor(){
		return this.color;
	}
	public void setColor(String color){
		this.color = color;
	}
	public Double getKilometrage(){
		return this.kilometrage;
	}
	public void setKilometrage(Double kilometrage){
		this.kilometrage = kilometrage;
	}


	public Transmission getTransmission() {
		return transmission;
	}


	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}


	public ModelFuelType getModelFuelType() {
		return modelFuelType;
	}


	public void setModelFuelType(ModelFuelType modelFuelType) {
		this.modelFuelType = modelFuelType;
	}


	public ModelMotor getModelMotor() {
		return modelMotor;
	}


	public void setModelMotor(ModelMotor modelMotor) {
		this.modelMotor = modelMotor;
	}


	public CarStatus getCarStatus() {
		return carStatus;
	}


	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	

	public ModelGearBox getModelGearBox() {
		return modelGearBox;
	}


	public void setModelGearBox(ModelGearBox modelGearBox) {
		this.modelGearBox = modelGearBox;
	}


	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}


    public String getIdCar() {
        return idCar;
    }


    public void setIdCar(String idCar) {
        this.idCar = idCar;
    }


}
