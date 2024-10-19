package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	@Enumerated(EnumType.STRING)
	private Role role;  // Ajout de l'accès privé à la variable role

	// Constructeur par défaut
	public User() { }

	// Constructeur avec paramètres
	public User(String firstName, String lastName, Date dateNaissance, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateNaissance = dateNaissance;
		this.role = role;
	}

	// Constructeur avec ID
	public User(Long id, String firstName, String lastName, Date dateNaissance, Role role) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateNaissance = dateNaissance;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +
				", dateNaissance=" + dateNaissance + ", role=" + role + "]";
	}

	// Getters et Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
