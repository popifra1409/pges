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
@Table(name = "tbl_tache")
public class Tache implements Serializable {
	@Id
	@GeneratedValue
	private Long idTache;
	private String libTache;
	private int priorite;
	private double tauxRea;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDebut;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFin;
	// une tache appartient à une activite necessaire
	@ManyToOne
	@JoinColumn(name = "IdActNec")
	private Activite_Necessaire activite;
	// une tache est effectuée à une phase
	@ManyToOne
	@JoinColumn(name = "IdPhase")
	private Phase phase;

	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tache(String libTache, int priorite, double tauxRea, Activite_Necessaire activite, Phase phase) {
		super();
		this.libTache = libTache;
		this.priorite = priorite;
		this.tauxRea = tauxRea;
		this.activite = activite;
		this.phase = phase;
	}

	public Long getIdTache() {
		return idTache;
	}

	public void setIdTache(Long idTache) {
		this.idTache = idTache;
	}

	public String getLibTache() {
		return libTache;
	}

	public void setLibTache(String libTache) {
		this.libTache = libTache;
	}

	public Activite_Necessaire getActivite() {
		return activite;
	}

	public void setActivite(Activite_Necessaire activite) {
		this.activite = activite;
	}

	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public double getTauxRea() {
		return tauxRea;
	}

	public void setTauxRea(double tauxRea) {
		this.tauxRea = tauxRea;
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
