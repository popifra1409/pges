package com.pges.metier;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.pges.entities.Entreprise;
import com.pges.entities.Personne;
import com.pges.entities.Phase;
import com.pges.entities.Site;

public interface IPgesMetier {
	// ======= Agents ================
	public void ajouterPersonne(Personne personne);

	public Personne rechercherPersonne(Long idPersonne);

	public Page<Personne> listPersonnes(int page, int size);

	public List<Personne> listePersonnes();

	public Page<Personne> chercherPersonnes(String motCle, int page, int size);

	public void modifierPersonne(Personne personne, Long idPersonne);

	public void supprimerPersonne(Long idPersonne);

	public List<Map<String, Object>> report();
	// ========== Sociétés ============

	public void ajouterSociete(Entreprise entreprise);

	public Entreprise consulterEntreprise(String codeEntr);

	public Page<Entreprise> listEntreprises(Long idPromoteur, int page, int size);

	public Page<Entreprise> chercheEntreprises(String motCle, int page, int size);

	public void supprimerSociete(String codeEntr);

	// ============== Sites =======================
	public Page<Site> listSites(int page, int size);

	public void ajouterSite(Site site);

	public Page<Site> chercherSites(String motCle, int page, int size);

	public Site rechercherSite(Long idSite);

	public void supprimerSite(Long idSite);

	// ============= phases ==============
	public Page<Phase> listPhases(Long idProjet, int page, int size);

	public Page<Phase> chercherPhases(String motCle, int page, int size);

	public void ajouterPhase(Phase phase);

	public void supprimerPhase(Long idPhase);

	public Phase rechercherPhase(Long idPhase);
}
