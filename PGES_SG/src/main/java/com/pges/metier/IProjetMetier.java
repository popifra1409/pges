package com.pges.metier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Activite_Source;
import com.pges.entities.Impact;
import com.pges.entities.Mesure;
import com.pges.entities.Projet;
import com.pges.entities.Resultat_Attendu;

public interface IProjetMetier {
	// ============Gestion des projets======
	public void ajouterProjet(Projet projet);

	public Page<Projet> listProjets(String codeEntr, int page, int size);

	public Projet consulterProjet(Long idProjet);

	// ============Gestion des impacts======
	public Page<Impact> chercherImpacts(String mc, int page, int size);

	public Page<Impact> listImpacts(Long idPhase, int page, int size);

	public void ajouterImpact(Impact impact);

	public Impact consulterImpact(Long idImpact);

	// ============Gestion des activites sources ======
	public Page<Activite_Source> listActSources(Long idImpact, int page, int size);

	public Page<Mesure> listMesuresActSources(Long idImpact, int page, int size);

	public Mesure consulterMesure(Long idMesure);

	public Activite_Source consulterActSource(Long idActSource);

	public Page<Activite_Source> chercherActSourceImpact(Long idImpact, int page, int size);

	public void ajouterActSource(Activite_Source activiteSource);

	// ============= Gestion des mesures ================
	public void ajouterMesure(Mesure mesure);

	public List<Mesure> listMesuresImpact(Long idImpact);

	// ============= Gestion des resultats================
	public Page<Resultat_Attendu> listeResutatsMesure(Long idMesure, int page, int size);

	public Page<Resultat_Attendu> listeResultatsPhase(Long idPhase, int page, int size);

	public void ajouterResultat(Resultat_Attendu resultat);

	public Resultat_Attendu consulterResultat(Long idResultat);

	public List<Resultat_Attendu> listResultatsMesure(Long idMesure);
}
