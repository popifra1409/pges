package com.pges.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PRO")
public class Promoteur extends Personne {

	public Promoteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Promoteur(String nomPersonne, String pnomPersonne, String sexe, String adresse, String telPersonne,
			String fonction) {
		super(nomPersonne, pnomPersonne, sexe, adresse, telPersonne, fonction);
		// TODO Auto-generated constructor stub
	}

}
