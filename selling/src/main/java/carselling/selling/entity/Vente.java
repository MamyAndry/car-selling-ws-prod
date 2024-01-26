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
@Table(name = "vente")
public class Vente {

	@Column(name = "id_annonce")
	String idAnnonce;
	@Column(name = "price_payed")
	Double pricePayed;
	@Id
		@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "SAL"),
		@Parameter(name = "sequence", value = "seq_vente"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_vente")
	String idVente;
	@Column(name = "date_sell")
	Date dateSell;
	@ManyToOne
	@JoinColumn(name = "id_Users")
	User seller;
	@Column
	Integer status;
	@Column(name = "date_validation")
	Date dateValidation;


	public Vente(){}

	public String getIdAnnonce(){
		return this.idAnnonce;
	}
	public void setIdAnnonce(String idAnnonce){
		this.idAnnonce = idAnnonce;
	}
	public Double getPricePayed(){
		return this.pricePayed;
	}
	public void setPricePayed(Double pricePayed) throws Exception{
		if (pricePayed < 0) {
			throw new Exception("Price should not be negative");
		}

		this.pricePayed = pricePayed;
	}
	public String getIdVente(){
		return this.idVente;
	}
	public void setIdVente(String idVente){
		this.idVente = idVente;
	}
	public Date getDateSell(){
		return this.dateSell;
	}
	public void setDateSell(Date dateSell){
		this.dateSell = dateSell;
	}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Date getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}
	

}
