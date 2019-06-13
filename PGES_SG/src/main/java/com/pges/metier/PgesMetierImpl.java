package com.pges.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pges.dao.EntrepriseRepository;
import com.pges.dao.PersonneRepository;
import com.pges.dao.PhaseRepository;
import com.pges.dao.SiteRepository;
import com.pges.entities.Entreprise;
import com.pges.entities.Personne;
import com.pges.entities.Phase;
import com.pges.entities.Site;

@Service
@Transactional
public class PgesMetierImpl implements IPgesMetier {
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private PhaseRepository phaseRepository;

	@Override
	public void ajouterPersonne(Personne personne) {
		personneRepository.save(personne);
	}

	@Override
	public Page<Personne> listPersonnes(int page, int size) {
		return personneRepository.listPersonnes(new PageRequest(page, size));
	}

	@Override
	public List<Personne> listePersonnes() {
		return personneRepository.findAll();
	}

	@Override
	public Page<Personne> chercherPersonnes(String motCle, int page, int size) {
		return personneRepository.chercherPersonnes(motCle, new PageRequest(page, size));
	}

	@Override
	public Personne rechercherPersonne(Long idPersonne) {
		Personne pers = personneRepository.findOne(idPersonne);
		if (pers == null)
			throw new RuntimeException("Agent introuvable");
		return pers;
	}

	@Override
	public void modifierPersonne(Personne personne, Long idPersonne) {
		personneRepository.modifierPersonne(idPersonne, personne.getNomPersonne(), personne.getPnomPersonne(),
				personne.getSexe(), personne.getAdresse(), personne.getTelPersonne(), personne.getFonction());
	}

	@Override
	public void supprimerPersonne(Long idPersonne) {
		personneRepository.delete(idPersonne);
	}

	@Override
	public List<Map<String, Object>> report() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Personne pers : personneRepository.findAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", pers.getIdPersonne());
			item.put("nom", pers.getNomPersonne());
			item.put("prenom", pers.getPnomPersonne());
			item.put("sexe", pers.getSexe());
			item.put("adresse", pers.getAdresse());
			item.put("telephone", pers.getTelPersonne());
			item.put("fonction", pers.getFonction());
			item.put("type_pers", pers.getClass().getSimpleName());
			result.add(item);
		}
		return result;
	}

	@Override
	public void ajouterSociete(Entreprise entreprise) {
		entrepriseRepository.save(entreprise);
	}

	@Override
	public Page<Entreprise> listEntreprises(Long idPromoteur, int page, int size) {
		return entrepriseRepository.listEntreprises(idPromoteur, new PageRequest(page, size));
	}

	@Override
	public Entreprise consulterEntreprise(String codeEntr) {
		Entreprise entr = entrepriseRepository.findOne(codeEntr);
		if (entr == null)
			throw new RuntimeException("Société introuvable");
		return entr;
	}

	@Override
	public Page<Entreprise> chercheEntreprises(String motCle, int page, int size) {
		return entrepriseRepository.chercheEntreprises(motCle, new PageRequest(page, size));
	}

	@Override
	public void supprimerSociete(String codeEntr) {
		entrepriseRepository.delete(codeEntr);
	}

	@Override
	public Page<Site> listSites(int page, int size) {
		return siteRepository.listSites(new PageRequest(page, size));
	}

	@Override
	public void ajouterSite(Site site) {
		siteRepository.save(site);
	}

	@Override
	public Page<Site> chercherSites(String motCle, int page, int size) {
		return siteRepository.chercherSites(motCle, new PageRequest(page, size));
	}

	@Override
	public Site rechercherSite(Long idSite) {
		return siteRepository.findOne(idSite);
	}

	@Override
	public void supprimerSite(Long idSite) {
		siteRepository.delete(idSite);
	}

	@Override
	public Page<Phase> listPhases(Long idProjet, int page, int size) {
		return phaseRepository.listPhases(idProjet, new PageRequest(page, size));
	}

	@Override
	public Page<Phase> chercherPhases(String motCle, int page, int size) {
		return phaseRepository.chercherPhases(motCle, new PageRequest(page, size));
	}

	@Override
	public void ajouterPhase(Phase phase) {
		phaseRepository.save(phase);
	}

	@Override
	public void supprimerPhase(Long idPhase) {
		phaseRepository.delete(idPhase);
	}

	@Override
	public Phase rechercherPhase(Long idPhase) {
		return phaseRepository.findOne(idPhase);
	}
}
