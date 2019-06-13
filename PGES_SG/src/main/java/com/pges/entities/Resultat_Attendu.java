package com.pges.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_resultatAttendu")
public class Resultat_Attendu {
	@Id
	@GeneratedValue
	private Long idResultat;
	private String libResultat;
	private int priorite;
	private double tauxeff;
	// un resultat appartient à une mesure
	@ManyToOne
	@JoinColumn(name = "idMesure")
	private Mesure mesure;
	// un résultat est obtenu à une phase
	@ManyToOne
	@JoinColumn(name = "IdPhase")
	private Phase phase;

	public Resultat_Attendu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resultat_Attendu(String libResultat, int priorite, double tauxeff, Mesure mesure) {
		super();
		this.libResultat = libResultat;
		this.priorite = priorite;
		this.tauxeff = tauxeff;
		this.mesure = mesure;
	}

	public Long getIdResultat() {
		return idResultat;
	}

	public void setIdResultat(Long idResultat) {
		this.idResultat = idResultat;
	}

	public String getLibResultat() {
		return libResultat;
	}

	public void setLibResultat(String libResultat) {
		this.libResultat = libResultat;
	}

	public Mesure getMesure() {
		return mesure;
	}

	public void setMesure(Mesure mesure) {
		this.mesure = mesure;
	}

	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	public double getTauxeff() {
		return tauxeff;
	}

	public void setTauxeff(double tauxeff) {
		this.tauxeff = tauxeff;
	}
}
