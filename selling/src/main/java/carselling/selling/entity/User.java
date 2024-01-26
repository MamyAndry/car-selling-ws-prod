package carselling.selling.entity;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import carselling.selling.exception.UserException;
import carselling.selling.utils.IdGenerator;
import carselling.selling.utils.Service;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GenericGenerator(name = "custom-id", type = IdGenerator.class,
	parameters = {
		@Parameter(name = "prefix", value = "CAT"),
		@Parameter(name = "sequence", value = "seq_users"),
		@Parameter(name = "max_length", value = "7")
	})
	@GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
    @Column(name = "id_users")
    String id;
    @Column
    String name;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    Date birthdate;
    @Column
    String email;
    @Column
    String password;
    @Column
    Integer gender;
    @Column(name = "is_admin")
    boolean isAdmin;
    @Column(name = "date_registration")
    Date dateRegistration;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) throws UserException {
        if (name.equals(null) || name.equals("")) {
            throw new UserException("Please, fill the first name field");
        }
        this.name = name;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) throws UserException {
        if (firstName.equals(null) || firstName.equals("")) {
            throw new UserException("Please, fill the first name field");
        }
        this.firstName = firstName;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) throws UserException {
        if (email.equals(null) || !Service.isEmail(email)) {
            throw new UserException("Your email has wrong syntax");
        }
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) throws UserException {
        if (password.equals(null) || password.equals("")) {
            throw new UserException("Please, fill the first name field");
        }
        this.password = password;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void checkPassWord(String password2) throws UserException {
        if (!this.getPassword().equals(password2)) {
            throw new UserException("Please check your password");
        }
    }
    public Date getDateRegistration() {
        return dateRegistration;
    }
    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }
    

}
