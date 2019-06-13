package com.pges.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_site")
public class Site implements Serializable {
	@Id
	@GeneratedValue
	private Long idSite;
	@NotEmpty(message = "Nom du site obligatoire")
	private String nomSite;
	private String pays;
	private String region;
	private String department;
	private String ville;
	private String longitude;
	private String latitude;

	// un site peut regrouper plusieurs projets
	@OneToMany(mappedBy = "site", fetch = FetchType.LAZY)
	private Collection<Projet> projets;

	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Site(String nomSite, String pays, String region, String department, String ville, String longitude,
			String latitude) {
		super();
		this.nomSite = nomSite;
		this.pays = pays;
		this.region = region;
		this.department = department;
		this.ville = ville;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Long getIdSite() {
		return idSite;
	}

	public void setIdSite(Long idSite) {
		this.idSite = idSite;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Collection<Projet> getProjets() {
		return projets;
	}

	public void setProjets(Collection<Projet> projets) {
		this.projets = projets;
	}

}
