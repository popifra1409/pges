package com.pges.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SUI")
public class Avis_Suivi extends Avis {

	public Avis_Suivi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Avis_Suivi(String refAvis, String libAvis, String critereAvis, String valeurAvis, Surveillance surveillance,
			Suivi suivi) {
		super(refAvis, libAvis, critereAvis, valeurAvis, surveillance, suivi);
		// TODO Auto-generated constructor stub
	}

}
