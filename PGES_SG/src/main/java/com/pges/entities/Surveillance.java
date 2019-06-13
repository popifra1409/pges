package com.pges.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbl_surveillance")
public class Surveillance implements Serializable {
	@Id
	@GeneratedValue
	private Long idSurveillance;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateSurveillance;
	private String periodeSurveillance;
	private String indicateurSurveillance;
	private String moyenVerif;
	private double coutSurveillance;
	private double tauxEff;
	private String comSurveillance;
	// une surveillance s'effectue à une phase d'un projet
	@ManyToOne
	@JoinColumn(name = "idPhase")
	private Phase phase;
	// une surveillance est effectuée par une personne
	@ManyToOne
	@JoinColumn(name = "idPersonne")
	private Personne personne;
	// une surveillance concerne une activité planifiée
	@ManyToOne
	@JoinColumn(name = "idTache")
	private Tache tache;

	public Surveillance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Surveillance(Date dateSurveillance, String periodeSurveillance, String indicateurSurveillance,
			String moyenVerif, double coutSurveillance, double tauxEff, String comSurveillance, Phase phase,
			Personne personne, Tache tache) {
		super();
		this.dateSurveillance = dateSurveillance;
		this.periodeSurveillance = periodeSurveillance;
		this.indicateurSurveillance = indicateurSurveillance;
		this.moyenVerif = moyenVerif;
		this.coutSurveillance = coutSurveillance;
		this.tauxEff = tauxEff;
		this.comSurveillance = comSurveillance;
		this.phase = phase;
		this.personne = personne;
		this.tache = tache;
	}

	public Long getIdSurveillance() {
		return idSurveillance;
	}

	public void setIdSurveillance(Long idSurveillance) {
		this.idSurveillance = idSurveillance;
	}

	public Date getDateSurveillance() {
		return dateSurveillance;
	}

	public void setDateSurveillance(Date dateSurveillance) {
		this.dateSurveillance = dateSurveillance;
	}

	public String getPeriodeSurveillance() {
		return periodeSurveillance;
	}

	public void setPeriodeSurveillance(String periodeSurveillance) {
		this.periodeSurveillance = periodeSurveillance;
	}

	public String getIndicateurSurveillance() {
		return indicateurSurveillance;
	}

	public void setIndicateurSurveillance(String indicateurSurveillance) {
		this.indicateurSurveillance = indicateurSurveillance;
	}

	public double getCoutSurveillance() {
		return coutSurveillance;
	}

	public void setCoutSurveillance(double coutSurveillance) {
		this.coutSurveillance = coutSurveillance;
	}

	public String getComSurveillance() {
		return comSurveillance;
	}

	public void setComSurveillance(String comSurveillance) {
		this.comSurveillance = comSurveillance;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public String getMoyenVerif() {
		return moyenVerif;
	}

	public void setMoyenVerif(String moyenVerif) {
		this.moyenVerif = moyenVerif;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public double getTauxEff() {
		return tauxEff;
	}

	public void setTauxEff(double tauxEff) {
		this.tauxEff = tauxEff;
	}
}
