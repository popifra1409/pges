package com.pges.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SUR")
public class Avis_Surveillance extends Avis {

	public Avis_Surveillance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Avis_Surveillance(String refAvis, String libAvis, String critereAvis, String valeurAvis,
			Surveillance surveillance, Suivi suivi) {
		super(refAvis, libAvis, critereAvis, valeurAvis, surveillance, suivi);
		// TODO Auto-generated constructor stub
	}

}
