package carselling.selling.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "model_motor")
public class ModelMotor {

	@Id
	@Column(name = "id_model_motor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer idModelMotor;
	@ManyToOne
	@JoinColumn(name = "id_motorisation")
	Motorisation motorisation;
	@ManyToOne
	@JoinColumn(name = "id_model")
	Model model;

	public ModelMotor(){}

	public Integer getIdModelMotor(){
		return this.idModelMotor;
	}
	public void setIdModelMotor(Integer idModelMotor){
		this.idModelMotor = idModelMotor;
	}
	public Motorisation getMotorisation(){
		return this.motorisation;
	}
	public void setMotorisation(Motorisation motorisation){
		this.motorisation = motorisation;
	}
	public Model getModel(){
		return this.model;
	}
	public void setModel(Model model){
		this.model = model;
	}


}
