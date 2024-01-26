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
@Table(name = "commission")
public class Commission {

	@Column(name = "boundary_inferior")
	Double boundaryInferior;
	@Column(name = "percentage")
	Integer percentage;
	@Column(name = "boundary_superior")
	Double boundarySuperior;
	@Id	@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "COM"),
		@Parameter(name = "sequence", value = "seq_commission"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_commission")
	String idCommission;




	public Commission(){}

	public Double getBoundaryInferior(){
		return this.boundaryInferior;
	}
	public void setBoundaryInferior(Double boundaryInferior) throws Exception{
		if (boundaryInferior < 0) {
			throw new Exception("Inferior boundary should not be negative");
		}

		this.boundaryInferior = boundaryInferior;
	}
	public Integer getPercentage(){
		return this.percentage;
	}
	public void setPercentage(Integer percentage) throws Exception{
		if (percentage < 0) {
			throw new Exception("Percentage should not be negative");
		}
		this.percentage = percentage;
	}
	public Double getBoundarySuperior(){
		return this.boundarySuperior;
	}
	public void setBoundarySuperior(Double boundarySuperior) throws Exception{
		if (boundarySuperior < 0) {
			throw new Exception("Superior boundary should not be negative");
		}

		this.boundarySuperior = boundarySuperior;
	}
	public String getIdCommission(){
		return this.idCommission;
	}
	public void setIdCommission(String idCommission){
		this.idCommission = idCommission;
	}


}
