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
@Table(name = "profit")
public class Profit {

	@Column(name = "date_add")
	Date dateAdd;
	@Column(name = "rising")
	Double rising;
	@Id 
	@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "PRF"),
		@Parameter(name = "sequence", value = "seq_profit"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_profit")
	String idProfit;
	@ManyToOne
	@JoinColumn(name = "id_users")
	User user;




	public Profit(){}

	public Date getDateAdd(){
		return this.dateAdd;
	}
	public void setDateAdd(Date dateAdd){
		this.dateAdd = dateAdd;
	}
	public Double getRising(){
		return this.rising;
	}
	public void setRising(Double rising){
		this.rising = rising;
	}
	public String getIdProfit(){
		return this.idProfit;
	}
	public void setIdProfit(String idProfit){
		this.idProfit = idProfit;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
