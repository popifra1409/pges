package com.pges.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbl_phase")
public class Phase implements Serializable {
	@Id
	@GeneratedValue
	private Long idPhase;
	@NotEmpty(message = "Libelle de phase obligatoire")
	private String libellePhase;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDebut;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFin;
	// une phase appartient un projet
	private Long duree;
	// à une phase on peut relever plusieurs impacts
	@OneToMany(mappedBy = "phase", fetch = FetchType.LAZY)
	private Collection<Impact> impacts;
	@ManyToOne
	@JoinColumn(name = "idProjet")
	private Projet projet;
	// une phase comporte plusieurs mise en oeuvres
	@ManyToMany
	@JoinTable(name = "tbl_phase_meo", joinColumns = { @JoinColumn(name = "idPhase") }, inverseJoinColumns = {
			@JoinColumn(name = "idMeo") })
	private Collection<Mise_En_Oeuvre> meos;
	@ManyToMany
	@JoinTable(name = "tbl_phase_surveillance", joinColumns = { @JoinColumn(name = "idPhase") }, inverseJoinColumns = {
			@JoinColumn(name = "idSurveillance") })
	private Collection<Mise_En_Oeuvre> surveillances;
	@ManyToMany
	@JoinTable(name = "tbl_phase_suivi", joinColumns = { @JoinColumn(name = "idPhase") }, inverseJoinColumns = {
			@JoinColumn(name = "idSuivi") })
	private Collection<Mise_En_Oeuvre> suivis;

	public Phase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Phase(String libellePhase, String description, Date dateDebut, Date dateFin, Long duree, Projet projet) {
		super();
		this.libellePhase = libellePhase;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.duree = duree;
		this.projet = projet;
	}

	public Long getIdPhase() {
		return idPhase;
	}

	public void setIdPhase(Long idPhase) {
		this.idPhase = idPhase;
	}

	public String getLibellePhase() {
		return libellePhase;
	}

	public void setLibellePhase(String libellePhase) {
		this.libellePhase = libellePhase;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Mise_En_Oeuvre> getMeos() {
		return meos;
	}

	public void setMeos(Collection<Mise_En_Oeuvre> meos) {
		this.meos = meos;
	}

	public Collection<Mise_En_Oeuvre> getSurveillances() {
		return surveillances;
	}

	public void setSurveillances(Collection<Mise_En_Oeuvre> surveillances) {
		this.surveillances = surveillances;
	}

	public Collection<Mise_En_Oeuvre> getSuivis() {
		return suivis;
	}

	public void setSuivis(Collection<Mise_En_Oeuvre> suivis) {
		this.suivis = suivis;
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

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Long getDuree() {
		return duree;
	}

	public void setDuree(Long duree) {
		this.duree = duree;
	}

	public Collection<Impact> getImpacts() {
		return impacts;
	}

	public void setImpacts(Collection<Impact> impacts) {
		this.impacts = impacts;
	}

	public Long dateDiff() {
		Long nbre = dateFin.getTime() - dateDebut.getTime();
		return nbre;
	}
}
