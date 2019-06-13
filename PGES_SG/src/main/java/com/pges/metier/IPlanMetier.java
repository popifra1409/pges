package com.pges.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Activite_Necessaire;
import com.pges.entities.Mise_En_Oeuvre;
import com.pges.entities.Resultat_Attendu;
import com.pges.entities.Suivi;
import com.pges.entities.Surveillance;
import com.pges.entities.Tache;

public interface IPlanMetier {
	// ======= Activités nécessaires ================
	public Page<Activite_Necessaire> listActNec(Long idMesure, int page, int size);

	public List<Activite_Necessaire> listActNecMesure(Long idMesure);

	public void ajouterActNec(Activite_Necessaire activiteNecessaire);

	public Activite_Necessaire consulterActNec(Long idActNec);

	// public double calculCoutMesure(Long idMesure);

	// ======= Taches ================
	public Page<Tache> listTaches(Long idActNec, int page, int size);

	public List<Tache> listTacheActNec(Long idActNec);

	public void ajouterTache(Tache tache);

	public Tache consulterTache(Long idTache);

	public Page<Tache> listTachePhase(Long idPhase, int page, int size);

	// ======= mise en oeuvre ================
	public Page<Mise_En_Oeuvre> listMeos(Long idPhase, int page, int size);

	public Page<Mise_En_Oeuvre> listTacheMeos(Long idPhase, int page, int size);

	public void ajouterMeo(Mise_En_Oeuvre meo);

	public Mise_En_Oeuvre consulterMeo(Long idMeo);

	public List<Mise_En_Oeuvre> listMeoTaches(Long idTache);

	// ======= surveillance ================
	public Page<Surveillance> listSurveillances(Long idPhase, int page, int size);

	public Page<Surveillance> listTacheSurveillances(Long idPhase, int page, int size);

	public List<Surveillance> listSurveillanceTache(Long idTache);

	public void ajouterSurveillance(Surveillance surveillance);

	public Surveillance consulterSurveillance(Long idSurveillance);

	// ======= suivi ================
	
	public Page<Suivi> listSuivis(Long idPhase, int page, int size);

	public Page<Suivi> listResultatSuivis(Long idPhase, int page, int size);

	public List<Suivi> listSuiviResultat(Long idResultat);

	public void ajouterSuivi(Suivi suivi);

	public Suivi consulterSuivi(Long idSuivi);
}
