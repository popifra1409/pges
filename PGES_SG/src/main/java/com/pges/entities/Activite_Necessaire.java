package com.pges.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_activiteNecessaire")
public class Activite_Necessaire implements Serializable {
	@Id
	@GeneratedValue
	private Long idActNec;
	private String libActNec;
	private double coutActNec;
	private double tauxRea;
	private int priorite;
	private String appreciation;
	// une activité necessaire peut etre subdivisée en plusieurs tâches
	@OneToMany(mappedBy = "activite", fetch = FetchType.LAZY)
	private Collection<Tache> taches;
	// un activité necessaire concerne une mesure proposée
	@ManyToOne
	@JoinColumn(name = "idMesure")
	private Mesure mesure;

	public Activite_Necessaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Activite_Necessaire(String libActNec, double coutActNec, double tauxRea, int priorite, String appreciation,
			Mesure mesure) {
		super();
		this.libActNec = libActNec;
		this.coutActNec = coutActNec;
		this.tauxRea = tauxRea;
		this.priorite = priorite;
		this.appreciation = appreciation;
		this.mesure = mesure;
	}

	public Long getIdActNec() {
		return idActNec;
	}

	public void setIdActNec(Long idActNec) {
		this.idActNec = idActNec;
	}

	public String getLibActNec() {
		return libActNec;
	}

	public void setLibActNec(String libActNec) {
		this.libActNec = libActNec;
	}

	public double getCoutActNec() {
		return coutActNec;
	}

	public void setCoutActNec(double coutActNec) {
		this.coutActNec = coutActNec;
	}

	public double getTauxRea() {
		return tauxRea;
	}

	public void setTauxRea(double tauxRea) {
		this.tauxRea = tauxRea;
	}

	public Collection<Tache> getTaches() {
		return taches;
	}

	public void setTaches(Collection<Tache> taches) {
		this.taches = taches;
	}

	public Mesure getMesure() {
		return mesure;
	}

	public void setMesure(Mesure mesure) {
		this.mesure = mesure;
	}

	public int getPriorite() {
		return priorite;
	}

	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}

	public String getAppreciation() {
		return appreciation;
	}

	public void setAppreciation(String appreciation) {
		this.appreciation = appreciation;
	}

}
