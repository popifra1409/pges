package com.pges.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_AVIS", discriminatorType = DiscriminatorType.STRING, length = 3)
@Table(name = "tbl_avis")
public abstract class Avis implements Serializable {
	@Id
	private String refAvis;
	private String libAvis;
	private String critereAvis;
	private String valeurAvis;
	// un avis est formulé pour une surveillanc
	@OneToOne
	@JoinColumn(name = "idSurveillance")
	private Surveillance surveillance;
	// un avis est formulé pour un suivi
	@OneToOne
	@JoinColumn(name = "idSuivi")
	private Suivi suivi;

	public Avis() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Avis(String refAvis, String libAvis, String critereAvis, String valeurAvis, Surveillance surveillance,
			Suivi suivi) {
		super();
		this.refAvis = refAvis;
		this.libAvis = libAvis;
		this.critereAvis = critereAvis;
		this.valeurAvis = valeurAvis;
		this.surveillance = surveillance;
		this.suivi = suivi;
	}

	public String getRefAvis() {
		return refAvis;
	}

	public void setRefAvis(String refAvis) {
		this.refAvis = refAvis;
	}

	public String getLibAvis() {
		return libAvis;
	}

	public void setLibAvis(String libAvis) {
		this.libAvis = libAvis;
	}

	public String getCritereAvis() {
		return critereAvis;
	}

	public void setCritereAvis(String critereAvis) {
		this.critereAvis = critereAvis;
	}

	public String getValeurAvis() {
		return valeurAvis;
	}

	public void setValeurAvis(String valeurAvis) {
		this.valeurAvis = valeurAvis;
	}

	public Surveillance getSurveillance() {
		return surveillance;
	}

	public void setSurveillance(Surveillance surveillance) {
		this.surveillance = surveillance;
	}

	public Suivi getSuivi() {
		return suivi;
	}

	public void setSuivi(Suivi suivi) {
		this.suivi = suivi;
	}
}
