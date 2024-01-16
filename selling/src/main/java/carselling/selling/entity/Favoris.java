package carselling.selling.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "favoris")
public class Favoris {

	@Column(name = "id_annonce")
	String idAnnonce;
	@Column(name = "id_users")
	String idUsers;
	@Id
	@Column(name = "id_favoris")
	Integer idFavoris;




	public Favoris(){}

	public String getIdAnnonce(){
		return this.idAnnonce;
	}
	public void setIdAnnonce(String idAnnonce){
		this.idAnnonce = idAnnonce;
	}
	public String getIdUsers(){
		return this.idUsers;
	}
	public void setIdUsers(String idUsers){
		this.idUsers = idUsers;
	}
	public Integer getIdFavoris(){
		return this.idFavoris;
	}
	public void setIdFavoris(Integer idFavoris){
		this.idFavoris = idFavoris;
	}


}
