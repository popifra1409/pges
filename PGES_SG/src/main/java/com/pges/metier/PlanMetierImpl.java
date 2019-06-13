package com.pges.metier;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pges.dao.Activite_NecessaireRepository;
import com.pges.dao.Mise_En_OeuvreRepository;
import com.pges.dao.SuiviRepository;
import com.pges.dao.SurveillanceRepository;
import com.pges.dao.TacheRepository;
import com.pges.entities.Activite_Necessaire;
import com.pges.entities.Mise_En_Oeuvre;
import com.pges.entities.Suivi;
import com.pges.entities.Surveillance;
import com.pges.entities.Tache;

@Transactional
@Service
public class PlanMetierImpl implements IPlanMetier {
	@Autowired
	private Activite_NecessaireRepository activiteNecRepository;
	@Autowired
	private TacheRepository tacheRepository;
	@Autowired
	private Mise_En_OeuvreRepository meoRepository;
	@Autowired
	private SurveillanceRepository surveillanceRepository;
	@Autowired
	private SuiviRepository suiviRepository;

	@Override
	public Page<Activite_Necessaire> listActNec(Long idMesure, int page, int size) {
		return activiteNecRepository.listActNec(idMesure, new PageRequest(page, size));
	}

	@Override
	public List<Activite_Necessaire> listActNecMesure(Long idMesure) {
		return activiteNecRepository.listActNecMesure(idMesure);
	}

	@Override
	public void ajouterActNec(Activite_Necessaire activiteNecessaire) {
		activiteNecRepository.save(activiteNecessaire);
	}

	@Override
	public Activite_Necessaire consulterActNec(Long idActNec) {
		return activiteNecRepository.findOne(idActNec);
	}

	@Override
	public Page<Tache> listTaches(Long idActNec, int page, int size) {
		return tacheRepository.listTaches(idActNec, new PageRequest(page, size));
	}

	@Override
	public List<Tache> listTacheActNec(Long idActNec) {
		return tacheRepository.listTacheActNec(idActNec);
	}

	@Override
	public void ajouterTache(Tache tache) {
		tacheRepository.save(tache);
	}

	@Override
	public Tache consulterTache(Long idTache) {
		return tacheRepository.findOne(idTache);
	}

	@Override
	public Page<Tache> listTachePhase(Long idPhase, int page, int size) {
		return tacheRepository.listTachePhase(idPhase, new PageRequest(page, size));
	}

	@Override
	public Page<Mise_En_Oeuvre> listMeos(Long idPhase, int page, int size) {
		return meoRepository.listMeos(idPhase, new PageRequest(page, size));
	}

	@Override
	public Page<Mise_En_Oeuvre> listTacheMeos(Long idPhase, int page, int size) {
		return meoRepository.listTacheMeos(idPhase, new PageRequest(page, size));
	}

	@Override
	public List<Mise_En_Oeuvre> listMeoTaches(Long idTache) {
		return meoRepository.listMeoTaches(idTache);
	}

	@Override
	public void ajouterMeo(Mise_En_Oeuvre meo) {
		meoRepository.save(meo);
	}

	@Override
	public Mise_En_Oeuvre consulterMeo(Long idMeo) {
		return meoRepository.findOne(idMeo);
	}

	@Override
	public Page<Surveillance> listSurveillances(Long idPhase, int page, int size) {
		return surveillanceRepository.listSurveillances(idPhase, new PageRequest(page, size));
	}

	@Override
	public Page<Surveillance> listTacheSurveillances(Long idPhase, int page, int size) {
		return surveillanceRepository.listTacheSurveillances(idPhase, new PageRequest(page, size));
	}

	@Override
	public List<Surveillance> listSurveillanceTache(Long idTache) {
		return surveillanceRepository.listSurveillanceTache(idTache);
	}

	@Override
	public void ajouterSurveillance(Surveillance surveillance) {
		surveillanceRepository.save(surveillance);
	}

	@Override
	public Surveillance consulterSurveillance(Long idSurveillance) {
		return surveillanceRepository.findOne(idSurveillance);
	}

	@Override
	public Page<Suivi> listSuivis(Long idPhase, int page, int size) {
		return suiviRepository.listSuivis(idPhase, new PageRequest(page, size));
	}

	@Override
	public Page<Suivi> listResultatSuivis(Long idPhase, int page, int size) {
		return suiviRepository.listResultatSuivis(idPhase, new PageRequest(page, size));
	}

	@Override
	public List<Suivi> listSuiviResultat(Long idResultat) {
		return suiviRepository.listSuiviResultat(idResultat);
	}

	@Override
	public void ajouterSuivi(Suivi suivi) {
		suiviRepository.save(suivi);

	}

	@Override
	public Suivi consulterSuivi(Long idSuivi) {
		return suiviRepository.findOne(idSuivi);
	}

}
