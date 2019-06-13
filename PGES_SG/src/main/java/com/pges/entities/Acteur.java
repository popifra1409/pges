package com.pges.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ACT")
public class Acteur extends Personne {

	public Acteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Acteur(String nomPersonne, String pnomPersonne, String sexe, String adresse, String telPersonne,
			String fonction) {
		super(nomPersonne, pnomPersonne, sexe, adresse, telPersonne, fonction);
		// TODO Auto-generated constructor stub
	}

}
