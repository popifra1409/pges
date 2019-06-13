package com.pges.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_appreciation")
public class Appreciation implements Serializable {
	@Id
	private String codeApprec;
	private String libApprec;
	// une appreciation peut avoir plusieurs valeurs selon les surveillances
	@OneToMany(mappedBy = "appreciation", fetch = FetchType.LAZY)
	private Collection<Appreciation_Surveillance> appreciation_surveillances;
	// une appreciation peut avoir plusieurs valeurs selon les surveillances
	@OneToMany(mappedBy = "appreciation", fetch = FetchType.LAZY)
	private Collection<Appreciation_Suivi> appreciation_suivis;

	public Appreciation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appreciation(String codeApprec, String libApprec) {
		super();
		this.codeApprec = codeApprec;
		this.libApprec = libApprec;
	}

	public String getCodeApprec() {
		return codeApprec;
	}

	public void setCodeApprec(String codeApprec) {
		this.codeApprec = codeApprec;
	}

	public String getLibApprec() {
		return libApprec;
	}

	public void setLibApprec(String libApprec) {
		this.libApprec = libApprec;
	}

	public Collection<Appreciation_Surveillance> getAppreciation_surveillances() {
		return appreciation_surveillances;
	}

	public void setAppreciation_surveillances(Collection<Appreciation_Surveillance> appreciation_surveillances) {
		this.appreciation_surveillances = appreciation_surveillances;
	}

	public Collection<Appreciation_Suivi> getAppreciation_suivis() {
		return appreciation_suivis;
	}

	public void setAppreciation_suivis(Collection<Appreciation_Suivi> appreciation_suivis) {
		this.appreciation_suivis = appreciation_suivis;
	}

}
