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
@Table(name = "tbl_meo")
public class Mise_En_Oeuvre implements Serializable {
	@Id
	@GeneratedValue
	private Long idMeo;
	private String periodeMeo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDebut;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFin;
	private String comMeo;
	private double coutMeo;
	// une mise en oeuvre concerne une phase d'un projet
	@ManyToOne
	@JoinColumn(name = "idPhase")
	private Phase phase;
	// une mise en oeuvre concerne une tache
	@ManyToOne
	@JoinColumn(name = "idTache")
	private Tache tache;
	// une mise en oeuvre est effectuée par une personne
	@ManyToOne
	@JoinColumn(name = "idPersonne")
	private Personne personne;

	public Mise_En_Oeuvre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mise_En_Oeuvre(String periodeMeo, Date dateDebut, Date dateFin, String comMeo, double coutMeo, Phase phase,
			Tache tache, Personne personne) {
		super();
		this.periodeMeo = periodeMeo;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.comMeo = comMeo;
		this.coutMeo = coutMeo;
		this.phase = phase;
		this.tache = tache;
		this.personne = personne;
	}

	public Long getIdMeo() {
		return idMeo;
	}

	public void setIdMeo(Long idMeo) {
		this.idMeo = idMeo;
	}

	public String getPeriodeMeo() {
		return periodeMeo;
	}

	public void setPeriodeMeo(String periodeMeo) {
		this.periodeMeo = periodeMeo;
	}

	public String getComMeo() {
		return comMeo;
	}

	public void setComMeo(String comMeo) {
		this.comMeo = comMeo;
	}

	public Tache getTache() {
		return tache;
	}

	public void setTache(Tache tache) {
		this.tache = tache;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public double getCoutMeo() {
		return coutMeo;
	}

	public void setCoutMeo(double coutMeo) {
		this.coutMeo = coutMeo;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

}
