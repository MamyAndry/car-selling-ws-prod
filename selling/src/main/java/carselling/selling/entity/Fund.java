package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import carselling.selling.utils.IdGenerator;


@Entity
@Table(name = "fund")
public class Fund {

	@Column(name = "date_add")
	Date dateAdd;
	@Column(name = "rising")
	Double rising;
	@Id	
	@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "FND"),
		@Parameter(name = "sequence", value = "seq_fund"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_fund")
	String idFund;
	@ManyToOne
	@JoinColumn(name = "id_vente")
	Vente vente;




	public Fund(){}

	public Date getDateAdd(){
		return this.dateAdd;
	}
	public void setDateAdd(Date dateAdd){
		this.dateAdd = dateAdd;
	}
	public Double getRising(){
		return this.rising;
	}
	public void setRising(Double rising) throws Exception{
		if (rising < 0) {
			throw new Exception("Price should not be negative");
		}
		this.rising = rising;
	}
	public String getIdFund(){
		return this.idFund;
	}
	public void setIdFund(String idFund){
		this.idFund = idFund;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

}
