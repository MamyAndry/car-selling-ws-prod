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
@Table(name = "category")
public class Category {

	@Id
	@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "CAT"),
		@Parameter(name = "sequence", value = "seq_category"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_category")
	String idCategory;
	@Column(name = "name")
	String name;




	public Category(){}

	public String getIdCategory(){
		return this.idCategory;
	}
	public void setIdCategory(String idCategory){
		this.idCategory = idCategory;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}


}
