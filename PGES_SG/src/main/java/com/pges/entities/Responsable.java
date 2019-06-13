package com.pges.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RES")
public class Responsable extends Personne {

	public Responsable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Responsable(String nomPersonne, String pnomPersonne, String sexe, String adresse, String telPersonne,
			String fonction) {
		super(nomPersonne, pnomPersonne, sexe, adresse, telPersonne, fonction);
		// TODO Auto-generated constructor stub
	}

}
