package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.criteria.Root;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import carselling.selling.utils.IdGenerator;

@Entity
@Table(name = "announcement")
public class Announcement {

	@Id
	@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "ANN"),
		@Parameter(name = "sequence", value = "seq_announcement"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_announcement")
	String idAnnouncement;
	@Column(name = "date_addition")
	Date dateAddition;
	@Column(name = "date_validation")
	Timestamp dateValidation;
	@Column(name = "price")
	Double price;
	@Column(name = "description")
	String description;
	@ManyToOne
	@JoinColumn(name = "status")
	AnnouncementStatus status;
	@ManyToOne
	@JoinColumn(name = "id_location")
	Location location;
	@ManyToOne
	@JoinColumn(name = "id_car")
	Car car;


	public Announcement(){}

	public String getIdAnnouncement(){
		return this.idAnnouncement;
	}
	public void setIdAnnouncement(String idAnnouncement){
		this.idAnnouncement = idAnnouncement;
	}
	public Date getDateAddition(){
		return this.dateAddition;
	}
	public void setDateAddition(Date dateAddition){
		this.dateAddition = dateAddition;
	}
	public Timestamp getDateValidation(){
		return this.dateValidation;
	}
	public void setDateValidation(Timestamp dateValidation){
		this.dateValidation = dateValidation;
	}
	public Double getPrice(){
		return this.price;
	}
	public void setPrice(Double price) throws Exception{
		if (price < 0) {
			throw new Exception("Price should not be negative");
		}
		this.price = price;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public AnnouncementStatus getStatus(){
		return this.status;
	}
	public void setStatus(AnnouncementStatus status){
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void searchByDescription(String description, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root) {
		if (description != null) {
			description = description.toLowerCase();
			predicates.add(builder.like(builder.lower(root.get("description")), "%" + description + "%"));
		}
	}

	public void searchByPrice(Double price_min, Double price_max, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(price_min != null && price_max != null){
			predicates.add(builder.between(root.get("price"), price_min, price_max));
		}
		if(price_min != null && price_max == null){
			predicates.add(builder.greaterThanOrEqualTo(root.get("price"), price_min));
		}
		if(price_min == null && price_max != null){
			predicates.add(builder.lessThanOrEqualTo(root.get("price"), price_max));
		}
	}

	public void searchByLocation(String idLocation, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(idLocation != null){
			predicates.add(builder.equal(root.get("location").get("idLocation"), idLocation));
		}
	}

	public void searchByTransmission(String idTransmission, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(idTransmission != null){
			predicates.add(builder.equal(root.get("car").get("transmission").get("idTransmission"), idTransmission));
		}
	}

	public void searchByFuelType(String idFuelType, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(idFuelType != null){
			predicates.add(builder.equal(root.get("car").get("modelFuelType").get("fuelType").get("idFuelType"), idFuelType));
		}
	}

	public void searchByColor(String color, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(color != null){
			color = color.toLowerCase();
			predicates.add(builder.like(builder.lower(root.get("car").get("color")), "%" + color + "%"));
		}
	}

	public void searchByMotor(String idMotor, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(idMotor != null){
			predicates.add(builder.equal(root.get("car").get("modelMotor").get("motorisation").get("idMotorisation"), idMotor));
		}
	}

	public void searchByKilometrage(Double min_kilometrage, Double max_kilometrage, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(min_kilometrage != null && max_kilometrage != null){
			predicates.add(builder.between(root.get("car").get("kilometrage"), min_kilometrage, max_kilometrage));
		}
		if(min_kilometrage != null && max_kilometrage == null){
			predicates.add(builder.greaterThanOrEqualTo(root.get("car").get("kilometrage"), min_kilometrage));
		}
		if(min_kilometrage == null && max_kilometrage !=null){
			predicates.add(builder.lessThanOrEqualTo(root.get("car").get("kilometrage"), max_kilometrage));
		}
	}

	public void searchByCarStatus(String idCarStatus, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(idCarStatus != null){
			predicates.add(builder.equal(root.get("car").get("carStatus").get("idCarStatus"), idCarStatus));
		}
	}

	public void searchByGearBox(String idGearBox, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(idGearBox != null){
			predicates.add(builder.equal(root.get("car").get("modelGearBox").get("gearBox").get("idGearBox"), idGearBox));
		}
	}

	public void searchByModel(String idModel, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(idModel != null){
			predicates.add(builder.equal(root.get("car").get("model").get("idModel"), idModel));
		}
	}

	public void searchByBrand(String idBrand, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(idBrand != null){
			predicates.add(builder.equal(root.get("car").get("model").get("brand").get("idBrand"), idBrand));
		}
	}

	public void searchByCategory(String idCategory, List<Predicate> predicates, CriteriaBuilder builder, Root<Announcement> root){
		if(idCategory != null){
			predicates.add(builder.equal(root.get("car").get("model").get("category").get("idCategory"), idCategory));
		}
	}
}
