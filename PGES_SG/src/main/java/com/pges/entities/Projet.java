package com.pges.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbl_projet")
public class Projet implements Serializable {
	@Id
	@GeneratedValue
	private Long idProjet;
	@NotEmpty(message = "Libelle projet obligatoire")
	private String libProjet;
	@NotEmpty(message = "Produit d'exploitation obligatoire")
	private String produitExp;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDebut;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFin;
	private Long duree;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCCE;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateACE;
	@NotEmpty(message = "Ref. CCE obligatoire")
	private String refCCE;
	@NotEmpty(message = "ref. ACE obligatoire")
	private String refACE;
	// un projet appartient à une entreprise
	@ManyToOne
	@JoinColumn(name = "codeEntr")
	private Entreprise entreprise;
	// un projet est implanté sur un site
	@ManyToOne
	@JoinColumn(name = "idSite")
	private Site site;
	// un projet peut etre découpé en plusieurs phases
	@OneToMany(mappedBy = "projet", fetch = FetchType.LAZY)
	private Collection<Phase> phases;

	public Projet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Projet(String libProjet, String produitExp, Date dateDebut, Date dateFin, Long duree, Date dateCCE,
			Date dateACE, String refCCE, String refACE, Entreprise entreprise, Site site) {
		super();
		this.libProjet = libProjet;
		this.produitExp = produitExp;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.duree = duree;
		this.dateCCE = dateCCE;
		this.dateACE = dateACE;
		this.refCCE = refCCE;
		this.refACE = refACE;
		this.entreprise = entreprise;
		this.site = site;
	}

	public Long getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}

	public String getLibProjet() {
		return libProjet;
	}

	public void setLibProjet(String libProjet) {
		this.libProjet = libProjet;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
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

	public Long getDuree() {
		return duree;
	}

	public void setDuree(Long duree) {
		this.duree = duree;
	}

	public String getProduitExp() {
		return produitExp;
	}

	public void setProduitExp(String produitExp) {
		this.produitExp = produitExp;
	}

	public Date getDateCCE() {
		return dateCCE;
	}

	public void setDateCCE(Date dateCCE) {
		this.dateCCE = dateCCE;
	}

	public Date getDateACE() {
		return dateACE;
	}

	public void setDateACE(Date dateACE) {
		this.dateACE = dateACE;
	}

	public String getRefCCE() {
		return refCCE;
	}

	public void setRefCCE(String refCCE) {
		this.refCCE = refCCE;
	}

	public String getRefACE() {
		return refACE;
	}

	public void setRefACE(String refACE) {
		this.refACE = refACE;
	}

	public Collection<Phase> getPhases() {
		return phases;
	}

	public void setPhases(Collection<Phase> phases) {
		this.phases = phases;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}
