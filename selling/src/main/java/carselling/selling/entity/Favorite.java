package carselling.selling.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import carselling.selling.utils.IdGenerator;


@Entity
@Table(name = "favorite")
public class Favorite {

	@ManyToOne
	@JoinColumn(name = "id_announcement")
	Announcement announcement;
	@ManyToOne
	@JoinColumn(name = "id_users")
	User user;
	@Id
	@GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "FAV"),
		@Parameter(name = "sequence", value = "seq_favorite"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
	@Column(name = "id_favorite")
	String idFavorite;




	public Favorite(){}

	public String getIdFavorite(){
		return this.idFavorite;
	}
	public void setIdFavorite(String idFavorite){
		this.idFavorite = idFavorite;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
