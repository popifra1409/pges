package com.pges.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_entreprise")
public class Entreprise implements Serializable {
	@Id
	@NotEmpty(message = "Code societe obligatoire")
	private String codeEntr;
	@NotEmpty(message = "Raison sociale obligatoire")
	private String nomEntr;
	private String droit;
	private int nbreEmp;
	private String adresse;
	private String email;
	@NotEmpty(message = "Numero de telephone obligatoire")
	private String telephone;
	// une entreprise appartient à une personne
	@ManyToOne
	@JoinColumn(name = "idPersonne")
	private Personne personne;
	// une entreprise peut avoir plusieurs projets
	@OneToMany(mappedBy = "entreprise")
	private Collection<Projet> projets;

	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String codeEntr, String nomEntr, String droit, int nbreEmp, String adresse, String email,
			String telephone, Personne personne) {
		super();
		this.codeEntr = codeEntr;
		this.nomEntr = nomEntr;
		this.droit = droit;
		this.nbreEmp = nbreEmp;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.personne = personne;
	}

	public String getCodeEntr() {
		return codeEntr;
	}

	public void setCodeEntr(String codeEntr) {
		this.codeEntr = codeEntr;
	}

	public String getNomEntr() {
		return nomEntr;
	}

	public void setNomEntr(String nomEntr) {
		this.nomEntr = nomEntr;
	}

	public String getDroit() {
		return droit;
	}

	public void setDroit(String droit) {
		this.droit = droit;
	}

	public int getNbreEmp() {
		return nbreEmp;
	}

	public void setNbreEmp(int nbreEmp) {
		this.nbreEmp = nbreEmp;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
}
