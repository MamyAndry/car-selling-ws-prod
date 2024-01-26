package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "model_fuel_type")
public class ModelFuelType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_model_fuel_type")
	Integer idModelFuelType;
	@ManyToOne
	@JoinColumn(name = "id_fuel_type")
	FuelType fuelType;
	@ManyToOne
	@JoinColumn(name = "id_model")
	Model model;




	public ModelFuelType(){}

	public Integer getIdModelFuelType(){
		return this.idModelFuelType;
	}
	public void setIdModelFuelType(Integer idModelFuelType){
		this.idModelFuelType = idModelFuelType;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}


}
