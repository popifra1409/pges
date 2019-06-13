package com.pges.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_impact")
public class Impact implements Serializable {
	@Id
	@GeneratedValue
	private Long idImpact;
	private String libImpact;
	private String importance;
	private double tauxCouverture;
	// un impact appartient à une phase
	@ManyToOne
	@JoinColumn(name = "idPhase")
	private Phase phase;
	// un impact peut avoir plusieurs activités source
	@OneToMany(mappedBy = "impact")
	private Collection<Activite_Source> activiteSources;
	// un impact peut avoir plusieurs mesures
	@OneToMany(mappedBy = "impact")
	private Collection<Mesure> mesures;

	public Impact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Impact(String libImpact, String importance, double tauxCouverture, Phase phase) {
		super();
		this.libImpact = libImpact;
		this.importance = importance;
		this.tauxCouverture = tauxCouverture;
		this.phase = phase;
	}

	public Long getIdImpact() {
		return idImpact;
	}

	public void setIdImpact(Long idImpact) {
		this.idImpact = idImpact;
	}

	public String getLibImpact() {
		return libImpact;
	}

	public void setLibImpact(String libImpact) {
		this.libImpact = libImpact;
	}

	public Collection<Mesure> getMesures() {
		return mesures;
	}

	public void setMesures(Collection<Mesure> mesures) {
		this.mesures = mesures;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public Collection<Activite_Source> getActiviteSources() {
		return activiteSources;
	}

	public void setActiviteSources(Collection<Activite_Source> activiteSources) {
		this.activiteSources = activiteSources;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public double getTauxCouverture() {
		return tauxCouverture;
	}

	public void setTauxCouverture(double tauxCouverture) {
		this.tauxCouverture = tauxCouverture;
	}

}
