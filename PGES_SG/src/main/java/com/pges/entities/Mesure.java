package com.pges.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_mesure")
public class Mesure implements Serializable {
	@Id
	@GeneratedValue
	private Long idMesure;
	private String libMesure;
	private String objectif;
	private double coutMesure;
	private double coutSurveillance;
	private double coutSuivi;
	private double tauxRea;
	private double tauxEff;
	private String appreciation;
	// une mesure correspond à une activité source
	@OneToOne
	@JoinColumn(name = "idActSource")
	private Activite_Source activiteSource;
	// une mesure concerne un impact
	@ManyToOne
	@JoinColumn(name = "idImpact")
	private Impact impact;
	// une mesure peut avoir plusieurs activité necessaires
	@OneToMany(mappedBy = "mesure", fetch = FetchType.LAZY)
	private Collection<Activite_Necessaire> activiteNecessaires;

	public Mesure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mesure(String libMesure, String objectif, double coutMesure, double coutSurveillance, double coutSuivi,
			double tauxRea, double tauxEff, String appreciation, Activite_Source activiteSource, Impact impact) {
		super();
		this.libMesure = libMesure;
		this.objectif = objectif;
		this.coutMesure = coutMesure;
		this.coutSurveillance = coutSurveillance;
		this.coutSuivi = coutSuivi;
		this.tauxRea = tauxRea;
		this.tauxEff = tauxEff;
		this.appreciation = appreciation;
		this.activiteSource = activiteSource;
		this.impact = impact;
	}

	public Long getIdMesure() {
		return idMesure;
	}

	public void setIdMesure(Long idMesure) {
		this.idMesure = idMesure;
	}

	public String getLibMesure() {
		return libMesure;
	}

	public void setLibMesure(String libMesure) {
		this.libMesure = libMesure;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public double getCoutMesure() {
		return coutMesure;
	}

	public void setCoutMesure(double coutMesure) {
		this.coutMesure = coutMesure;
	}

	public Impact getImpact() {
		return impact;
	}

	public void setImpact(Impact impact) {
		this.impact = impact;
	}

	public Collection<Activite_Necessaire> getActiviteNecessaires() {
		return activiteNecessaires;
	}

	public void setActiviteNecessaires(Collection<Activite_Necessaire> activiteNecessaires) {
		this.activiteNecessaires = activiteNecessaires;
	}

	public Activite_Source getActiviteSource() {
		return activiteSource;
	}

	public void setActiviteSource(Activite_Source activiteSource) {
		this.activiteSource = activiteSource;
	}

	public double getTauxRea() {
		return tauxRea;
	}

	public void setTauxRea(double tauxRea) {
		this.tauxRea = tauxRea;
	}

	public double getCoutSurveillance() {
		return coutSurveillance;
	}

	public void setCoutSurveillance(double coutSurveillance) {
		this.coutSurveillance = coutSurveillance;
	}

	public double getCoutSuivi() {
		return coutSuivi;
	}

	public void setCoutSuivi(double coutSuivi) {
		this.coutSuivi = coutSuivi;
	}

	public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

	public double getTauxEff() {
		return tauxEff;
	}

	public void setTauxEff(double tauxEff) {
		this.tauxEff = tauxEff;
	}

}
