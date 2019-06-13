package com.pges.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_activiteSource")
public class Activite_Source implements Serializable {
	@Id
	@GeneratedValue
	private Long idActSource;
	private String libActSource;
	// une activité source concerne un impact
	@ManyToOne
	@JoinColumn(name = "idImpact")
	private Impact impact;

	public Activite_Source() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Activite_Source(String libActSource, Impact impact) {
		super();
		this.libActSource = libActSource;
		this.impact = impact;
	}

	public Long getIdActSource() {
		return idActSource;
	}

	public void setIdActSource(Long idActSource) {
		this.idActSource = idActSource;
	}

	public String getLibActSource() {
		return libActSource;
	}

	public void setLibActSource(String libActSource) {
		this.libActSource = libActSource;
	}

	public Impact getImpact() {
		return impact;
	}

	public void setImpact(Impact impact) {
		this.impact = impact;
	}

}
