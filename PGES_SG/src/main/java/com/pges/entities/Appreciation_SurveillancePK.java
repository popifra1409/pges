package com.pges.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Appreciation_SurveillancePK implements Serializable {
	private Long idSurveillance;
	private String codeApprec;

	public Appreciation_SurveillancePK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appreciation_SurveillancePK(Long idSurveillance, String codeApprec) {
		super();
		this.idSurveillance = idSurveillance;
		this.codeApprec = codeApprec;
	}

	public Long getIdSurveillance() {
		return idSurveillance;
	}

	public void setIdSurveillance(Long idSurveillance) {
		this.idSurveillance = idSurveillance;
	}

	public String getCodeApprec() {
		return codeApprec;
	}

	public void setCodeApprec(String codeApprec) {
		this.codeApprec = codeApprec;
	}

}
