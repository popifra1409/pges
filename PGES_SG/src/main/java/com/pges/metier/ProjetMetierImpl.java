package com.pges.metier;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pges.dao.Activite_SourceRepository;
import com.pges.dao.ImpactRepository;
import com.pges.dao.MesureRepository;
import com.pges.dao.ProjetRepository;
import com.pges.dao.Resultat_AttenduRepository;
import com.pges.entities.Activite_Source;
import com.pges.entities.Impact;
import com.pges.entities.Mesure;
import com.pges.entities.Projet;
import com.pges.entities.Resultat_Attendu;

@Transactional
@Service
public class ProjetMetierImpl implements IProjetMetier {
	@Autowired
	private ProjetRepository projetRepository;
	@Autowired
	private ImpactRepository impactRepository;
	@Autowired
	private MesureRepository mesureRepository;
	@Autowired
	private Activite_SourceRepository activiteSourceRepository;
	@Autowired
	private Resultat_AttenduRepository resultatAttenduRepository;

	@Override
	public void ajouterProjet(Projet projet) {
		projetRepository.save(projet);

	}

	@Override
	public Page<Projet> listProjets(String codeEntr, int page, int size) {
		return projetRepository.listProjets(codeEntr, new PageRequest(page, size));
	}

	@Override
	public Projet consulterProjet(Long idProjet) {
		return projetRepository.findOne(idProjet);
	}

	@Override
	public Page<Impact> chercherImpacts(String mc, int page, int size) {
		return impactRepository.chercherImpacts(mc, new PageRequest(page, size));
	}

	@Override
	public Page<Impact> listImpacts(Long idPhase, int page, int size) {
		return impactRepository.listImpacts(idPhase, new PageRequest(page, size));
	}

	@Override
	public void ajouterImpact(Impact impact) {
		impactRepository.save(impact);

	}

	@Override
	public Impact consulterImpact(Long idImpact) {
		return impactRepository.findOne(idImpact);
	}

	@Override
	public Page<Mesure> listMesuresActSources(Long idImpact, int page, int size) {
		return mesureRepository.listMesuresActSources(idImpact, new PageRequest(page, size));
	}

	@Override
	public Mesure consulterMesure(Long idMesure) {
		return mesureRepository.findOne(idMesure);
	}

	@Override
	public Activite_Source consulterActSource(Long idActSource) {
		return activiteSourceRepository.findOne(idActSource);
	}

	@Override
	public void ajouterActSource(Activite_Source activiteSource) {
		activiteSourceRepository.save(activiteSource);
	}

	@Override
	public void ajouterMesure(Mesure mesure) {
		mesureRepository.save(mesure);
	}

	@Override
	public List<Mesure> listMesuresImpact(Long idImpact) {
		return mesureRepository.listMesuresImpact(idImpact);
	}

	@Override
	public Page<Activite_Source> listActSources(Long idImpact, int page, int size) {
		return activiteSourceRepository.listActSources(idImpact, new PageRequest(page, size));
	}

	@Override
	public Page<Activite_Source> chercherActSourceImpact(Long idImpact, int page, int size) {
		return activiteSourceRepository.chercherActSourceImpact(idImpact, new PageRequest(page, size));
	}

	@Override
	public Page<Resultat_Attendu> listeResutatsMesure(Long idMesure, int page, int size) {
		return resultatAttenduRepository.listeResultatsMesure(idMesure, new PageRequest(page, size));
	}

	@Override
	public void ajouterResultat(Resultat_Attendu resultat) {
		resultatAttenduRepository.save(resultat);

	}

	@Override
	public Resultat_Attendu consulterResultat(Long idResultat) {
		return resultatAttenduRepository.findOne(idResultat);
	}

	@Override
	public Page<Resultat_Attendu> listeResultatsPhase(Long idPhase, int page, int size) {
		return resultatAttenduRepository.listeResultatsPhase(idPhase, new PageRequest(page, size));
	}

	@Override
	public List<Resultat_Attendu> listResultatsMesure(Long idMesure) {
		return resultatAttenduRepository.listResultatsMesure(idMesure);
	}

}
