package com.pges.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_appreciationSuivi")
public class Appreciation_Suivi implements Serializable {
	private String valeurSuivi;
	// concatenation de deux clés issues de l'association
	// Appreciation-Suivi
	@EmbeddedId
	private Appreciation_SuviPK apprecSuiviPK;
	// une appreciation de suivi concerne un suivi
	@ManyToOne
	@JoinColumn(name = "idSuivi", referencedColumnName = "idSuivi", insertable = false, updatable = false)
	private Suivi suivi;
	// une appreciation de surveillance concerne une appreciation
	@ManyToOne
	@JoinColumn(name = "idApprec")
	private Appreciation appreciation;

	public Appreciation_Suivi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appreciation_Suivi(String valeurSuivi, Suivi suivi, Appreciation appreciation) {
		super();
		this.valeurSuivi = valeurSuivi;
		this.suivi = suivi;
		this.appreciation = appreciation;
		this.apprecSuiviPK = new Appreciation_SuviPK(suivi.getIdSuivi(), appreciation.getCodeApprec());
	}

	public String getValeurSuivi() {
		return valeurSuivi;
	}

	public void setValeurSuivi(String valeurSuivi) {
		this.valeurSuivi = valeurSuivi;
	}

	public Suivi getSuivi() {
		return suivi;
	}

	public void setSuivi(Suivi suivi) {
		this.suivi = suivi;
	}

	public Appreciation getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(Appreciation appreciation) {
		this.appreciation = appreciation;
	}

}
