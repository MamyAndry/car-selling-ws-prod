package carselling.selling.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import carselling.selling.utils.IdGenerator;


@Entity
@Table(name = "gear_box")
public class GearBox {

	@Id
	@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "GRB"),
		@Parameter(name = "sequence", value = "seq_gear"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_gear_box")
	String idGearBox;
	@Column(name = "name")
	String name;




	public GearBox(){}

	public String getIdGearBox(){
		return this.idGearBox;
	}
	public void setIdGearBox(String idGearBox){
		this.idGearBox = idGearBox;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}


}
