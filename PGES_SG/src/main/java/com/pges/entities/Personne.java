package com.pges.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_PERS", discriminatorType = DiscriminatorType.STRING, length = 3)
@Table(name = "tbl_personne")
public abstract class Personne implements Serializable {
	@Id
	@GeneratedValue
	private Long idPersonne;
	private String nomPersonne;
	private String pnomPersonne;
	private String sexe;
	private String adresse;
	private String telPersonne;
	private String fonction;
	// une personne peut effectuer plusieurs activités nécessaires
	@OneToMany(mappedBy = "personne", fetch = FetchType.LAZY)
	private Collection<Mise_En_Oeuvre> meos;
	// une personne peut effectuer plusieurs surveillance
	@OneToMany(mappedBy = "personne", fetch = FetchType.LAZY)
	private Collection<Surveillance> surveillances;
	// une personne peut effectuer plusieurs suivis
	@OneToMany(mappedBy = "personne", fetch = FetchType.LAZY)
	private Collection<Suivi> suivis;
	// une personne peut avoir une ou plusieurs entreprise
	@OneToMany(mappedBy = "personne")
	private Collection<Entreprise> entreprises;

	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Personne(String nomPersonne, String pnomPersonne, String sexe, String adresse, String telPersonne,
			String fonction) {
		super();
		this.nomPersonne = nomPersonne;
		this.pnomPersonne = pnomPersonne;
		this.sexe = sexe;
		this.adresse = adresse;
		this.telPersonne = telPersonne;
		this.fonction = fonction;
	}

	public Long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Long idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getNomPersonne() {
		return nomPersonne;
	}

	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}

	public String getPnomPersonne() {
		return pnomPersonne;
	}

	public void setPnomPersonne(String pnomPersonne) {
		this.pnomPersonne = pnomPersonne;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelPersonne() {
		return telPersonne;
	}

	public void setTelPersonne(String telPersonne) {
		this.telPersonne = telPersonne;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public Collection<Surveillance> getSurveillances() {
		return surveillances;
	}

	public void setSurveillances(Collection<Surveillance> surveillances) {
		this.surveillances = surveillances;
	}

	public Collection<Entreprise> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(Collection<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}

	public Collection<Mise_En_Oeuvre> getMeos() {
		return meos;
	}

	public void setMeos(Collection<Mise_En_Oeuvre> meos) {
		this.meos = meos;
	}

	public Collection<Suivi> getSuivis() {
		return suivis;
	}

	public void setSuivis(Collection<Suivi> suivis) {
		this.suivis = suivis;
	}

	/*
	 * @Override public String toString() { return "Personne [idPersonne=" +
	 * idPersonne + ", nomPersonne=" + nomPersonne + ", pnomPersonne=" +
	 * pnomPersonne + ", sexe=" + sexe + ", adresse=" + adresse +
	 * ", telPersonne=" + telPersonne + ", fonction=" + fonction + ", meos=" +
	 * meos + ", surveillances=" + surveillances + ", suivis=" + suivis +
	 * ", entreprises=" + entreprises + "]"; }
	 */

}
