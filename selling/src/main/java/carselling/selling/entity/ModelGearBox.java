package carselling.selling.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "model_gear_box")
public class ModelGearBox {

	@Id
	@Column(name = "id_model_gear_box")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer idModelGearBox;
	@ManyToOne
	@JoinColumn(name = "id_gear_box")
	GearBox gearBox;
	@ManyToOne
	@JoinColumn(name = "id_model")
	Model model;




	public ModelGearBox(){}

	public GearBox getGearBox(){
		return this.gearBox;
	}
	public void setGearBox(GearBox gearBox){
		this.gearBox = gearBox;
	}
	public Integer getIdModelGearBox(){
		return this.idModelGearBox;
	}
	public void setIdModelGearBox(Integer idModelGearBox){
		this.idModelGearBox = idModelGearBox;
	}
	public Model getModel(){
		return this.model;
	}
	public void setModel(Model model){
		this.model = model;
	}


}
