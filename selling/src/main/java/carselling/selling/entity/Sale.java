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
@Table(name = "sale")
public class Sale {

	@Column(name = "id_announcement")
	String idAnnouncement;
	@Column(name = "price_payed")
	Double pricePayed;
	@Id
		@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "SAL"),
		@Parameter(name = "sequence", value = "seq_sale"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_sale")
	String idSale;
	@Column(name = "date_sale")
	Date dateSale;
	@ManyToOne
	@JoinColumn(name = "id_seller")
	User seller;
	@Column
	Integer status;
	@Column(name = "date_validation")
	Date dateValidation;


	public Sale(){}

	public String getIdAnnouncement(){
		return this.idAnnouncement;
	}
	public void setIdAnnouncement(String idAnnouncement){
		this.idAnnouncement = idAnnouncement;
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
	public String getIdSale(){
		return this.idSale;
	}
	public void setIdSale(String idsale){
		this.idSale = idsale;
	}
	public Date getDateSale(){
		return this.dateSale;
	}
	public void setDateSale(Date dateSale){
		this.dateSale = dateSale;
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
