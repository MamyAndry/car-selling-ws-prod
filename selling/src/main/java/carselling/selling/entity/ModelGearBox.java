package carselling.selling.entity;


import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;




@Entity
@Table(name = "model_gear_box")

public class ModelGearBox {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_model_gear_box")
	Integer idModelGearBox;

	@ManyToOne
	@JoinColumn(name = "id_gear_box")
	GearBox gearBox;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "id_model")
	Model model;




	public ModelGearBox(){}

	

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public GearBox getGearBox() {
		return gearBox;
	}



	public void setGearBox(GearBox gearBox) {
		this.gearBox = gearBox;
	}



	public Integer getIdModelGearBox() {
		return idModelGearBox;
	}



	public void setIdModelGearBox(Integer idModelGearBox) {
		this.idModelGearBox = idModelGearBox;
	}


}
