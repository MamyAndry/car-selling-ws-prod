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
