package com.pges.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Appreciation_SuviPK implements Serializable {
	private Long idSuivi;
	private String codeApprec;

	public Appreciation_SuviPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appreciation_SuviPK(Long idSuivi, String codeApprec) {
		super();
		this.idSuivi = idSuivi;
		this.codeApprec = codeApprec;
	}

	public Long getIdSuivi() {
		return idSuivi;
	}

	public void setIdSuivi(Long idSuivi) {
		this.idSuivi = idSuivi;
	}

	public String getCodeApprec() {
		return codeApprec;
	}

	public void setCodeApprec(String codeApprec) {
		this.codeApprec = codeApprec;
	}

}
