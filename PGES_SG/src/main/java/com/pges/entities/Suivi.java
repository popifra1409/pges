package com.pges.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_suivi")
public class Suivi implements Serializable {
	@Id
	@GeneratedValue
	private Long idSuivi;
	private Date dateSuivi;
	private String situationRef;
	private String horizonRef;
	private String indicateurSuivi;
	private String periodeSuivi;
	private String frequenceSuivi;
	private String moyenVerification;
	private double coutSuivi;
	private String comSuivi;
	private double tauxEff;
	// un suivi concerne une phase d'un projet
	@ManyToOne
	@JoinColumn(name = "idPhase")
	private Phase phase;
	// un suivi est effectué par une personne
	@ManyToOne
	@JoinColumn(name = "idPersonne")
	private Personne personne;
	@ManyToOne
	@JoinColumn(name = "idResultat")
	private Resultat_Attendu resultat;

	public Suivi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Suivi(Date dateSuivi, String situationRef, String horizonRef, String indicateurSuivi, String periodeSuivi,
			String frequenceSuivi, String moyenVerification, double coutSuivi, String comSuivi, Phase phase,
			double tauxEff, Personne personne, Resultat_Attendu resultat) {
		super();
		this.dateSuivi = dateSuivi;
		this.situationRef = situationRef;
		this.horizonRef = horizonRef;
		this.indicateurSuivi = indicateurSuivi;
		this.periodeSuivi = periodeSuivi;
		this.frequenceSuivi = frequenceSuivi;
		this.moyenVerification = moyenVerification;
		this.coutSuivi = coutSuivi;
		this.comSuivi = comSuivi;
		this.tauxEff = tauxEff;
		this.phase = phase;
		this.personne = personne;
		this.resultat = resultat;
	}

	public Long getIdSuivi() {
		return idSuivi;
	}

	public void setIdSuivi(Long idSuivi) {
		this.idSuivi = idSuivi;
	}

	public Date getDateSuivi() {
		return dateSuivi;
	}

	public void setDateSuivi(Date dateSuivi) {
		this.dateSuivi = dateSuivi;
	}

	public String getSituationRef() {
		return situationRef;
	}

	public void setSituationRef(String situationRef) {
		this.situationRef = situationRef;
	}

	public String getHorizonRef() {
		return horizonRef;
	}

	public void setHorizonRef(String horizonRef) {
		this.horizonRef = horizonRef;
	}

	public String getIndicateurSuivi() {
		return indicateurSuivi;
	}

	public void setIndicateurSuivi(String indicateurSuivi) {
		this.indicateurSuivi = indicateurSuivi;
	}

	public String getPeriodeSuivi() {
		return periodeSuivi;
	}

	public void setPeriodeSuivi(String periodeSuivi) {
		this.periodeSuivi = periodeSuivi;
	}

	public String getFrequenceSuivi() {
		return frequenceSuivi;
	}

	public void setFrequenceSuivi(String frequenceSuivi) {
		this.frequenceSuivi = frequenceSuivi;
	}

	public String getMoyenVerification() {
		return moyenVerification;
	}

	public void setMoyenVerification(String moyenVerification) {
		this.moyenVerification = moyenVerification;
	}

	public double getCoutSuivi() {
		return coutSuivi;
	}

	public void setCoutSuivi(double coutSuivi) {
		this.coutSuivi = coutSuivi;
	}

	public String getComSuivi() {
		return comSuivi;
	}

	public void setComSuivi(String comSuivi) {
		this.comSuivi = comSuivi;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Resultat_Attendu getResultat() {
		return resultat;
	}

	public void setResultat(Resultat_Attendu resultat) {
		this.resultat = resultat;
	}

	public double getTauxEff() {
		return tauxEff;
	}

	public void setTauxEff(double tauxEff) {
		this.tauxEff = tauxEff;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
}
