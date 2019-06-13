package com.pges.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_appreciationSurveillance")
public class Appreciation_Surveillance implements Serializable {
	private String valeur;
	// concatenation de deux clés issues de l'association
	// Appreciation-Surveillance
	@EmbeddedId
	private Appreciation_SurveillancePK apprecSurveillancePK;
	// une appreciation de surveillance concerne une surveillance
	@ManyToOne
	@JoinColumn(name = "iSurveillance")
	private Surveillance surveillance;
	// une appreciation de surveillance concerne une appreciation
	@ManyToOne
	@JoinColumn(name = "idApprec")
	private Appreciation appreciation;

	public Appreciation_Surveillance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appreciation_Surveillance(String valeur, Surveillance surveillance, Appreciation appreciation) {
		super();
		this.valeur = valeur;
		this.surveillance = surveillance;
		this.appreciation = appreciation;
		this.apprecSurveillancePK = new Appreciation_SurveillancePK(surveillance.getIdSurveillance(),
				appreciation.getCodeApprec());
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public Surveillance getSurveillance() {
		return surveillance;
	}

	public void setSurveillance(Surveillance surveillance) {
		this.surveillance = surveillance;
	}

	public Appreciation getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(Appreciation appreciation) {
		this.appreciation = appreciation;
	}

}
