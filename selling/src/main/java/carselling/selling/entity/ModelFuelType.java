package carselling.selling.entity;


import jakarta.persistence.*;




@Entity
@Table(name = "model_fuel_type")
public class ModelFuelType {

	@Id
	@Column(name = "id_model_fuel_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public FuelType getFuelType(){
		return this.fuelType;
	}
	public void setIdFuelType(FuelType fuelType){
		this.fuelType = fuelType;
	}
	public Model getModel(){
		return this.model;
	}
	public void setModel(Model model){
		this.model = model;
	}


}
