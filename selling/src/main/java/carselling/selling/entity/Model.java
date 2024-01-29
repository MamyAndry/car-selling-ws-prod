package carselling.selling.entity;


import java.util.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import carselling.selling.utils.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "model")
public class Model {
	@Id
	@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "MDL"),
		@Parameter(name = "sequence", value = "seq_model"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_model")
	String idModel;

	@ManyToOne
	@JoinColumn(name = "id_brand")
	Brand brand;
	@ManyToOne
	@JoinColumn(name = "id_category")
	Category category;
	@Column(name = "name")
	String name;

	@ManyToMany
	@JoinTable(
		name = "model_gear_box",
		joinColumns = @JoinColumn(name = "id_model"),
		inverseJoinColumns = @JoinColumn(name = "id_gear_box")
	)
	List<GearBox> gearBoxes;

	@ManyToMany @Getter @Setter
	@JoinTable(
		name = "model_motor",
		joinColumns = @JoinColumn(name = "id_model"),
		inverseJoinColumns = @JoinColumn(name = "id_motorisation")
	)
	List<Motorisation> motorisations = new ArrayList<>();

	@ManyToMany @Getter @Setter
	@JoinTable(
		name = "model_fuel_type",
		joinColumns = @JoinColumn(name = "id_model"),
		inverseJoinColumns = @JoinColumn(name = "id_fuel_type")
	)
	List<FuelType> fuelTypes = new ArrayList<>();;


	public Model(){}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getIdModel(){
		return this.idModel;
	}
	public void setIdModel(String idModel){
		this.idModel = idModel;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<GearBox> getGearBoxes() {
		return gearBoxes;
	}
	public void setGearBoxes(List<GearBox> gearBoxes) {
		this.gearBoxes = gearBoxes;
	}
	

}
