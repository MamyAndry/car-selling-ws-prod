package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import carselling.selling.utils.IdGenerator;


@Entity
@Table(name = "fund")
public class Fund {

	@Column(name = "date_addition")
    @Temporal(TemporalType.DATE)
	Date dateAddition;
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
	@JoinColumn(name = "id_sale")
	Sale sale;




	public Fund(){}

	public Date getDateAddition(){
		return this.dateAddition;
	}
	public void setDateAddition(Date dateAddition){
		this.dateAddition = dateAddition;
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

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

}
