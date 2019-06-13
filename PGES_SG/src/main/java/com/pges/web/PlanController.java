package com.pges.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pges.entities.Activite_Necessaire;
import com.pges.entities.Impact;
import com.pges.entities.Mesure;
import com.pges.entities.Mise_En_Oeuvre;
import com.pges.entities.Personne;
import com.pges.entities.Phase;
import com.pges.entities.Projet;
import com.pges.entities.Resultat_Attendu;
import com.pges.entities.Suivi;
import com.pges.entities.Surveillance;
import com.pges.entities.Tache;
import com.pges.metier.IPgesMetier;
import com.pges.metier.IPlanMetier;
import com.pges.metier.IProjetMetier;

@Controller
public class PlanController {
	@Autowired
	private IProjetMetier projetMetier;
	@Autowired
	private IPgesMetier pgesMetier;
	@Autowired
	private IPlanMetier planMetier;

	// ============= gestion des activités nécessaires ==================
	@RequestMapping(value = "/findPlanification", method = RequestMethod.GET)
	public String findPlanification(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		try {
			Page<Projet> pageProjets = projetMetier.listProjets("%" + motCle + "%", p, 5);
			model.addAttribute("listProjets", pageProjets.getContent());
			int pagesCount = pageProjets.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", motCle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findPlanification";
	}

	@RequestMapping("/phasePlanification")
	public String phasePlanification(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc, Long idProjet) {
		try {
			Projet projet = projetMetier.consulterProjet(idProjet);
			model.addAttribute("projet", projet);

			Page<Phase> pagePhases = pgesMetier.listPhases(idProjet, p, 5);
			model.addAttribute("listPhases", pagePhases.getContent());
			int pagesCount = pagePhases.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", mc);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "phasePlanification";
	}

	@RequestMapping(value = "/impactPlanification", method = RequestMethod.GET)
	public String impactPlanification(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			Long idPhase) {
		try {
			Phase phase = pgesMetier.rechercherPhase(idPhase);
			model.addAttribute("phase", phase);
			model.addAttribute("idProjet", phase.getProjet().getIdProjet());

			Page<Impact> pageImpacts = projetMetier.listImpacts(phase.getIdPhase(), p, 5);
			model.addAttribute("listImpacts", pageImpacts.getContent());
			int pagesCount = pageImpacts.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("idPhase", idPhase);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "impactPlanification";
	}

	@RequestMapping(value = "/mesurePlanification", method = RequestMethod.GET)
	public String mesurePlanification(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			Long idImpact) {
		try {
			Impact impact = projetMetier.consulterImpact(idImpact);
			model.addAttribute("impact", impact);

			Page<Mesure> pageMesures = projetMetier.listMesuresActSources(impact.getIdImpact(), p, 5);
			model.addAttribute("listMesuresActSources", pageMesures.getContent());
			int pagesCount = pageMesures.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("idImpact", idImpact);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "mesurePlanification";
	}

	@RequestMapping(value = "/findActNec", method = RequestMethod.GET)
	public String findActNec(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idMesure) {
		try {
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			model.addAttribute("mesure", mesure);

			Page<Activite_Necessaire> pageActNec = planMetier.listActNec(mesure.getIdMesure(), p, 5);
			model.addAttribute("listMesuresActSources", pageActNec.getContent());
			int pagesCount = pageActNec.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("idMesure", idMesure);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findActNec";
	}

	@RequestMapping(value = "/formActNec", method = RequestMethod.GET)
	public String formActNec(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idMesure) {
		try {
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			model.addAttribute("mesure", mesure);
			model.addAttribute("activiteNec", new Activite_Necessaire(null, 0, 0, 0, null, mesure));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formActNec";
	}

	@RequestMapping(value = "/ajouterActNec", method = RequestMethod.POST)
	public String ajouterActNec(@Valid Activite_Necessaire activiteNecessaire, BindingResult bindingResult) {
		List<Activite_Necessaire> listeActNecMesure = new ArrayList<Activite_Necessaire>();
		double coutMesure = 0;
		Long idMesure = activiteNecessaire.getMesure().getIdMesure();
		if (bindingResult.hasErrors()) {
			return "formActNec";
		}
		try {
			planMetier.ajouterActNec(activiteNecessaire);
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			listeActNecMesure = planMetier.listActNecMesure(idMesure);
			for (int i = 0; i < listeActNecMesure.size(); i++) {
				coutMesure = coutMesure + listeActNecMesure.get(i).getCoutActNec();
			}
			mesure.setCoutMesure(coutMesure);
			projetMetier.ajouterMesure(mesure);
		} catch (Exception e) {

		}

		return "redirect:/findActNec?idMesure=" + idMesure;
	}

	@RequestMapping(value = "/rechercherActNec")
	public String modifierActNec(Model model, Long id, @RequestParam(name = "page", defaultValue = "0") int p) {
		try {
			Activite_Necessaire activite = planMetier.consulterActNec(id);
			model.addAttribute("activite", activite);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editActNec";
	}

	@RequestMapping(value = "/editActNec", method = RequestMethod.POST)
	public String editActNec(@Valid Activite_Necessaire activite, BindingResult bindingResult) {
		List<Activite_Necessaire> listeActNecMesure = new ArrayList<Activite_Necessaire>();
		double coutMesure = 0;
		Long idMesure = activite.getMesure().getIdMesure();
		if (bindingResult.hasErrors()) {
			return "formActNec";
		}
		try {
			planMetier.ajouterActNec(activite);
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			listeActNecMesure = planMetier.listActNecMesure(idMesure);
			for (int i = 0; i < listeActNecMesure.size(); i++) {
				coutMesure = coutMesure + listeActNecMesure.get(i).getCoutActNec();
			}
			mesure.setCoutMesure(coutMesure);
			projetMetier.ajouterMesure(mesure);
		} catch (Exception e) {

		}

		return "redirect:/findActNec?idMesure=" + activite.getMesure().getIdMesure();
	}

	@RequestMapping(value = "/fermerFormActNec", method = RequestMethod.GET)
	public String fermerFormActNec(Long idMesure) {
		return "redirect:/findActNec?idMesure=" + idMesure;
	}

	// ============= gestion des tâches ==================
	@RequestMapping(value = "/findTache", method = RequestMethod.GET)
	public String findTache(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idActNec) {
		try {
			Activite_Necessaire activiteNecessaire = planMetier.consulterActNec(idActNec);
			model.addAttribute("activiteNecessaire", activiteNecessaire);

			Page<Tache> pageTaches = planMetier.listTaches(activiteNecessaire.getIdActNec(), p, 5);
			model.addAttribute("listTaches", pageTaches.getContent());
			int pagesCount = pageTaches.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("idActNec", idActNec);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findTache";
	}

	@RequestMapping(value = "/formTache", method = RequestMethod.GET)
	public String formTache(Model model, Long idActNec) {
		try {
			Activite_Necessaire activite = planMetier.consulterActNec(idActNec);
			Phase phase = pgesMetier.rechercherPhase(activite.getMesure().getImpact().getPhase().getIdPhase());
			model.addAttribute("phase", phase);
			model.addAttribute("activite", activite);
			model.addAttribute("tache", new Tache(null, 0, 0, activite, phase));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formTache";
	}

	@RequestMapping(value = "/ajouterTache", method = RequestMethod.POST)
	public String ajouterTache(@Valid Tache tache, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formTache";
		}
		try {
			planMetier.ajouterTache(tache);
		} catch (Exception e) {

		}

		return "redirect:/findTache?idActNec=" + tache.getActivite().getIdActNec();
	}

	@RequestMapping(value = "/rechercherTache")
	public String modifierTache(Model model, Long id) {
		try {
			Tache tache = planMetier.consulterTache(id);
			model.addAttribute("tache", tache);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editTache";
	}

	@RequestMapping(value = "/editTache", method = RequestMethod.POST)
	public String editTache(@Valid Tache tache, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formTache";
		}
		try {
			planMetier.ajouterTache(tache);
		} catch (Exception e) {

		}

		return "redirect:/findTache?idActNec=" + tache.getActivite().getIdActNec();
	}

	@RequestMapping(value = "/fermerFormTache", method = RequestMethod.GET)
	public String fermerFormTache(Long idActNec) {
		return "redirect:/findTache?idActNec=" + idActNec;
	}

	// ============= gestion des mise en oeuvre ==================
	@RequestMapping(value = "/findMeo", method = RequestMethod.GET)
	public String findMeo(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		try {
			Page<Projet> pageProjets = projetMetier.listProjets("%" + motCle + "%", p, 5);
			model.addAttribute("listProjets", pageProjets.getContent());
			int pagesCount = pageProjets.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", motCle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findMeo";
	}

	@RequestMapping("/meo")
	public String meos(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idProjet,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		try {
			Projet projet = projetMetier.consulterProjet(idProjet);
			model.addAttribute("projet", projet);

			Page<Phase> pagePhases = pgesMetier.listPhases(idProjet, p, 5);
			model.addAttribute("listPhases", pagePhases.getContent());
			int pagesCount = pagePhases.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", mc);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "meo";
	}

	@RequestMapping(value = "/phaseMeo", method = RequestMethod.GET)
	public String phaseMeo(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idPhase) {
		try {
			Phase phase = pgesMetier.rechercherPhase(idPhase);
			model.addAttribute("phase", phase);

			// Page<Mise_En_Oeuvre> pageMeos =
			// planMetier.listMeos(phase.getIdPhase(), p, 5);
			Page<Mise_En_Oeuvre> pageTacheMeos = planMetier.listTacheMeos(phase.getIdPhase(), p, 5);
			model.addAttribute("listMeos", pageTacheMeos.getContent());
			int pagesCount = pageTacheMeos.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("idPhase", idPhase);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "phaseMeo";
	}

	@RequestMapping(value = "/formMeo", method = RequestMethod.GET)
	public String formMeo(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idPhase) {
		try {
			Phase phase = pgesMetier.rechercherPhase(idPhase);
			Page<Tache> pageTaches = planMetier.listTachePhase(phase.getIdPhase(), p, 1000000);
			Page<Personne> pageAgents = pgesMetier.listPersonnes(p, 10000000);
			model.addAttribute("listAgents", pageAgents.getContent());
			model.addAttribute("phase", phase);
			model.addAttribute("listTachePhase", pageTaches.getContent());
			model.addAttribute("meo", new Mise_En_Oeuvre(null, new Date(), new Date(), null, 0, phase, null, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formMeo";
	}

	@RequestMapping(value = "/ajouterMeo", method = RequestMethod.POST)
	public String ajouterMeo(@Valid Mise_En_Oeuvre meo, BindingResult bindingResult) {
		Long idMesure = meo.getTache().getActivite().getMesure().getIdMesure();
		if (bindingResult.hasErrors()) {
			return "formMeo";
		}
		try {
			planMetier.ajouterMeo(meo);
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			mesure.setCoutMesure(meo.getCoutMeo());
			Tache tache = planMetier.consulterTache(meo.getTache().getIdTache());
			tache.setDateDebut(meo.getDateDebut());
			tache.setDateFin(meo.getDateFin());
			planMetier.ajouterTache(tache);
			projetMetier.ajouterMesure(mesure);
		} catch (Exception e) {

		}

		return "redirect:/phaseMeo?idPhase=" + meo.getPhase().getIdPhase();
	}

	@RequestMapping(value = "/agentMeo", method = RequestMethod.GET)
	public String agentMeo(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idMeo) {
		try {
			Mise_En_Oeuvre meo = planMetier.consulterMeo(idMeo);
			Page<Personne> pageAgents = pgesMetier.listPersonnes(p, 1000000);
			model.addAttribute("meo", meo);
			model.addAttribute("listAgents", pageAgents.getContent());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "agentMeo";
	}

	@RequestMapping(value = "/rechercherMeo")
	public String modifierMeo(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long id) {
		try {
			Mise_En_Oeuvre meo = planMetier.consulterMeo(id);
			Page<Personne> pageAgents = pgesMetier.listPersonnes(p, 10000000);
			model.addAttribute("listAgents", pageAgents.getContent());
			model.addAttribute("meo", meo);
			Page<Tache> pageTaches = planMetier.listTachePhase(meo.getPhase().getIdPhase(), p, 1000000);
			model.addAttribute("listTachePhase", pageTaches.getContent());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editMeo";
	}

	@RequestMapping(value = "/editMeo", method = RequestMethod.POST)
	public String editMeo(@Valid Mise_En_Oeuvre meo, BindingResult bindingResult) {
		Long idMesure = meo.getTache().getActivite().getMesure().getIdMesure();
		if (bindingResult.hasErrors()) {
			return "formMeo";
		}
		try {
			planMetier.ajouterMeo(meo);
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			mesure.setCoutMesure(meo.getCoutMeo());
			Tache tache = planMetier.consulterTache(meo.getTache().getIdTache());
			tache.setDateDebut(meo.getDateDebut());
			tache.setDateFin(meo.getDateFin());
			planMetier.ajouterTache(tache);
			projetMetier.ajouterMesure(mesure);
		} catch (Exception e) {

		}

		return "redirect:/phaseMeo?idPhase=" + meo.getPhase().getIdPhase();
	}

	@RequestMapping(value = "/fermerFormMeo", method = RequestMethod.GET)
	public String fermerFormMeo(Long idPhase) {
		return "redirect:/phaseMeo?idPhase=" + idPhase;
	}

	// ============= gestion du suivi de l'effectivité des mesures
	// ================
	@RequestMapping(value = "/findSurveillance", method = RequestMethod.GET)
	public String findSurveillance(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		try {
			Page<Projet> pageProjets = projetMetier.listProjets("%" + motCle + "%", p, 5);
			model.addAttribute("listProjets", pageProjets.getContent());
			int pagesCount = pageProjets.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", motCle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findSurveillance";
	}

	@RequestMapping("/surveillance")
	public String surveillances(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idProjet,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		try {
			Projet projet = projetMetier.consulterProjet(idProjet);
			model.addAttribute("projet", projet);

			Page<Phase> pagePhases = pgesMetier.listPhases(idProjet, p, 5);
			model.addAttribute("listPhases", pagePhases.getContent());
			int pagesCount = pagePhases.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", mc);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "surveillance";
	}

	@RequestMapping(value = "/phaseSurveillance", method = RequestMethod.GET)
	public String phaseSurveillance(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idPhase) {
		try {
			Phase phase = pgesMetier.rechercherPhase(idPhase);
			model.addAttribute("phase", phase);

			Page<Surveillance> pageSurveillances = planMetier.listTacheSurveillances(phase.getIdPhase(), p, 5);
			model.addAttribute("listSurveillances", pageSurveillances.getContent());
			int pagesCount = pageSurveillances.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("idPhase", idPhase);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "phaseSurveillance";
	}

	@RequestMapping(value = "/formSurveillance", method = RequestMethod.GET)
	public String formSurveillance(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idPhase) {
		try {
			Phase phase = pgesMetier.rechercherPhase(idPhase);
			Page<Tache> pageTaches = planMetier.listTachePhase(phase.getIdPhase(), p, 1000000);
			model.addAttribute("listTachePhase", pageTaches.getContent());
			Page<Personne> pagePersonnes = pgesMetier.listPersonnes(p, 1000000);
			model.addAttribute("listPersonnes", pagePersonnes.getContent());
			model.addAttribute("phase", phase);
			model.addAttribute("listTachePhase", pageTaches.getContent());
			model.addAttribute("surveillance",
					new Surveillance(new Date(), null, null, null, 0, 0, null, phase, null, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formSurveillance";
	}

	@RequestMapping(value = "/ajouterSurveillance", method = RequestMethod.POST)
	public String ajouterSurveillance(@Valid Surveillance surveillance, BindingResult bindingResult) {
		List<Surveillance> listeSurveillanceTache = new ArrayList<Surveillance>();
		List<Tache> listeTachesActNec = new ArrayList<Tache>();
		List<Activite_Necessaire> listeActNecMesure = new ArrayList<Activite_Necessaire>();
		List<Mesure> listeMesuresImpact = new ArrayList<Mesure>();

		double tauxCumul = 0, tauxCumulAct = 0, TRP, TRM, sumTRM = 0, TCI, sumCoutMeos = 0, sumCoutActivites = 0;
		int sumpoids = 0, sumpoidsAct = 0, nbreMesure = 0;

		Long idTache = surveillance.getTache().getIdTache();
		Long idActNec = surveillance.getTache().getActivite().getIdActNec();
		Long idMesure = surveillance.getTache().getActivite().getMesure().getIdMesure();
		Long idImpact = surveillance.getTache().getActivite().getMesure().getImpact().getIdImpact();

		if (bindingResult.hasErrors()) {
			return "formSurveillance";
		}
		try {
			planMetier.ajouterSurveillance(surveillance);
			// ======== mise à jour du taux de la tache ===========
			Tache tache = planMetier.consulterTache(idTache);
			tache.setTauxRea(surveillance.getTauxEff());
			planMetier.ajouterTache(tache);

			// ======== mise a jour du taux TRP et du cout de surveillance de
			// l'activite necessaire =======
			Activite_Necessaire activite = planMetier.consulterActNec(idActNec);
			listeTachesActNec = planMetier.listTacheActNec(idActNec);
			// listeSurveillanceTache =
			// planMetier.listSurveillanceTache(idTache);
			// evaluation de la somme des poids des taches
			for (int i = 0; i < listeTachesActNec.size(); i++) {
				sumpoids = sumpoids + listeTachesActNec.get(i).getPriorite();
			}
			// calcul du taux de réalisation des activités planifiées (taches)
			// d'une activite necessaire
			for (int i = 0; i < listeTachesActNec.size(); i++) {
				tauxCumul = tauxCumul + listeTachesActNec.get(i).getTauxRea() * listeTachesActNec.get(i).getPriorite();
			}
			TRP = tauxCumul / sumpoids;
			activite.setTauxRea(TRP);
			planMetier.ajouterActNec(activite);

			// Date dateJour = new Date("yyyy-MM-dd");
			Long intFin = surveillance.getTache().getDateFin().getTime();
			Long intJour = System.currentTimeMillis();
			if (TRP == 100) {
				activite.setAppreciation("R");
			}
			if ((intJour <= intFin) && (TRP < 100)) {
				activite.setAppreciation("EC");
			}
			if ((intJour > intFin) && (TRP < 100)) {
				activite.setAppreciation("NR");
			}
			// evaluation de la somme des couts
			for (int i = 0; i < listeSurveillanceTache.size(); i++) {
				sumCoutMeos = sumCoutMeos + listeSurveillanceTache.get(i).getCoutSurveillance();
				// sumCoutMeos = activite.getCoutActNec() +
				// listeSurveillanceTache.get(i).getCoutSurveillance();
			}
			activite.setCoutActNec(sumCoutMeos);
			planMetier.ajouterActNec(activite);

			// ======== calcul du taux de réalisation TRM et du cout de la
			// mesure ===============
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			listeActNecMesure = planMetier.listActNecMesure(idMesure);
			// evaluation de la somme des poids des activités nécessaires
			for (int i = 0; i < listeActNecMesure.size(); i++) {
				sumpoidsAct = sumpoidsAct + listeActNecMesure.get(i).getPriorite();
			}
			// mise à jour du taux de réalisation des activités nécessaires
			// d'une
			// mesure
			for (int i = 0; i < listeActNecMesure.size(); i++) {
				tauxCumulAct = tauxCumulAct
						+ listeActNecMesure.get(i).getTauxRea() * listeActNecMesure.get(i).getPriorite();
			}
			TRM = tauxCumulAct / sumpoidsAct;
			mesure.setTauxRea(TRM);
			for (int i = 0; i < listeActNecMesure.size(); i++) {
				sumCoutActivites = sumCoutActivites + listeActNecMesure.get(i).getCoutActNec();
				// sumCoutActivites = mesure.getCoutMesure() +
				// listeActNecMesure.get(i).getCoutActNec();
			}
			mesure.setCoutMesure(sumCoutActivites);
			projetMetier.ajouterMesure(mesure);

			// ========== mise à jour du taux de couverture de l'impact
			Impact impact = projetMetier.consulterImpact(idImpact);
			listeMesuresImpact = projetMetier.listMesuresImpact(idImpact);
			for (int i = 0; i < listeMesuresImpact.size(); i++) {
				sumTRM = sumTRM + listeMesuresImpact.get(i).getTauxRea();
				nbreMesure = nbreMesure + i + 1;
			}
			TCI = sumTRM / nbreMesure;
			impact.setTauxCouverture(TCI);
			projetMetier.ajouterImpact(impact);
		} catch (Exception e) {

		}

		return "redirect:/phaseSurveillance?idPhase=" + surveillance.getPhase().getIdPhase();
	}

	@RequestMapping(value = "/rechercherSurveillance")
	public String modifierSurveillance(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long id) {
		try {
			Surveillance surveillance = planMetier.consulterSurveillance(id);
			model.addAttribute("surveillance", surveillance);
			Page<Tache> pageTaches = planMetier.listTachePhase(surveillance.getPhase().getIdPhase(), p, 1000000);
			model.addAttribute("listTachePhase", pageTaches.getContent());
			Page<Personne> pagePersonnes = pgesMetier.listPersonnes(p, 1000000);
			model.addAttribute("listPersonnes", pagePersonnes.getContent());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editSurveillance";
	}

	@RequestMapping(value = "/editSurveillance", method = RequestMethod.POST)
	public String editSurveillance(@Valid Surveillance surveillance, BindingResult bindingResult) {
		List<Surveillance> listeSurveillanceTache = new ArrayList<Surveillance>();
		List<Tache> listeTachesActNec = new ArrayList<Tache>();
		List<Activite_Necessaire> listeActNecMesure = new ArrayList<Activite_Necessaire>();
		List<Mesure> listeMesuresImpact = new ArrayList<Mesure>();

		double tauxCumul = 0, tauxCumulAct = 0, TRP, TRM, sumTRM = 0, TCI, sumCoutMeos = 0, sumCoutActivites = 0;
		int sumpoids = 0, sumpoidsAct = 0, nbreMesure = 0;

		Long idTache = surveillance.getTache().getIdTache();
		Long idActNec = surveillance.getTache().getActivite().getIdActNec();
		Long idMesure = surveillance.getTache().getActivite().getMesure().getIdMesure();
		Long idImpact = surveillance.getTache().getActivite().getMesure().getImpact().getIdImpact();

		if (bindingResult.hasErrors()) {
			return "formSurveillance";
		}
		try {
			planMetier.ajouterSurveillance(surveillance);
			// ======== mise à jour du taux de la tache ===========
			Tache tache = planMetier.consulterTache(idTache);
			tache.setTauxRea(surveillance.getTauxEff());
			planMetier.ajouterTache(tache);

			// ======== mise a jour du taux TRP et du cout de surveillance de
			// l'activite necessaire =======
			Activite_Necessaire activite = planMetier.consulterActNec(idActNec);
			listeTachesActNec = planMetier.listTacheActNec(idActNec);
			// listeSurveillanceTache =
			// planMetier.listSurveillanceTache(idTache);
			// evaluation de la somme des poids des taches
			for (int i = 0; i < listeTachesActNec.size(); i++) {
				sumpoids = sumpoids + listeTachesActNec.get(i).getPriorite();
			}
			// calcul du taux de réalisation des activités planifiées (taches)
			// d'une activite necessaire
			for (int i = 0; i < listeTachesActNec.size(); i++) {
				tauxCumul = tauxCumul + listeTachesActNec.get(i).getTauxRea() * listeTachesActNec.get(i).getPriorite();
			}
			TRP = tauxCumul / sumpoids;
			activite.setTauxRea(TRP);
			planMetier.ajouterActNec(activite);

			// Date dateJour = new Date("yyyy-MM-dd");
			Long intFin = surveillance.getTache().getDateFin().getTime();
			Long intJour = System.currentTimeMillis();
			if (TRP == 100) {
				activite.setAppreciation("R");
			}
			if ((intJour <= intFin) && (TRP < 100)) {
				activite.setAppreciation("EC");
			}
			if ((intJour > intFin) && (TRP < 100)) {
				activite.setAppreciation("NR");
			}
			// evaluation de la somme des couts
			for (int i = 0; i < listeSurveillanceTache.size(); i++) {
				sumCoutMeos = sumCoutMeos + listeSurveillanceTache.get(i).getCoutSurveillance();
				// sumCoutMeos = activite.getCoutActNec() +
				// listeSurveillanceTache.get(i).getCoutSurveillance();
			}
			activite.setCoutActNec(sumCoutMeos);
			planMetier.ajouterActNec(activite);

			// ======== calcul du taux de réalisation TRM et du cout de la
			// mesure ===============
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			listeActNecMesure = planMetier.listActNecMesure(idMesure);
			// evaluation de la somme des poids des activités nécessaires
			for (int i = 0; i < listeActNecMesure.size(); i++) {
				sumpoidsAct = sumpoidsAct + listeActNecMesure.get(i).getPriorite();
			}
			// mise à jour du taux de réalisation des activités nécessaires
			// d'une
			// mesure
			for (int i = 0; i < listeActNecMesure.size(); i++) {
				tauxCumulAct = tauxCumulAct
						+ listeActNecMesure.get(i).getTauxRea() * listeActNecMesure.get(i).getPriorite();
			}
			TRM = tauxCumulAct / sumpoidsAct;
			mesure.setTauxRea(TRM);
			for (int i = 0; i < listeActNecMesure.size(); i++) {
				sumCoutActivites = sumCoutActivites + listeActNecMesure.get(i).getCoutActNec();
			}
			mesure.setCoutMesure(sumCoutActivites);
			projetMetier.ajouterMesure(mesure);

			// ========== mise à jour du taux de couverture de l'impact
			Impact impact = projetMetier.consulterImpact(idImpact);
			listeMesuresImpact = projetMetier.listMesuresImpact(idImpact);
			for (int i = 0; i < listeMesuresImpact.size(); i++) {
				sumTRM = sumTRM + listeMesuresImpact.get(i).getTauxRea();
				nbreMesure = nbreMesure + i + 1;
			}

			TCI = sumTRM / nbreMesure;
			impact.setTauxCouverture(TCI);
			projetMetier.ajouterImpact(impact);
		} catch (Exception e) {

		}

		return "redirect:/phaseSurveillance?idPhase=" + surveillance.getPhase().getIdPhase();
	}

	@RequestMapping(value = "/fermerFormSurveillance", method = RequestMethod.GET)
	public String fermerFormSurveillance(Long idPhase) {
		return "redirect:/phaseSurveillance?idPhase=" + idPhase;
	}

	// ============= gestion du suivi de l'efficacité des mesures
	// ================
	@RequestMapping(value = "/findSuivi", method = RequestMethod.GET)
	public String findSuivi(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String motCle) {
		try {
			Page<Projet> pageProjets = projetMetier.listProjets("%" + motCle + "%", p, 5);
			model.addAttribute("listProjets", pageProjets.getContent());
			int pagesCount = pageProjets.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", motCle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findSuivi";
	}

	@RequestMapping("/suivi")
	public String suivis(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idProjet,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		try {
			Projet projet = projetMetier.consulterProjet(idProjet);
			model.addAttribute("projet", projet);

			Page<Phase> pagePhases = pgesMetier.listPhases(idProjet, p, 5);
			model.addAttribute("listPhases", pagePhases.getContent());
			int pagesCount = pagePhases.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", mc);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "suivi";
	}

	@RequestMapping(value = "/phaseSuivi", method = RequestMethod.GET)
	public String phaseSuivi(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idPhase) {
		try {
			Phase phase = pgesMetier.rechercherPhase(idPhase);
			model.addAttribute("phase", phase);

			Page<Suivi> pageSuivis = planMetier.listResultatSuivis(phase.getIdPhase(), p, 5);
			model.addAttribute("listSuivis", pageSuivis.getContent());
			int pagesCount = pageSuivis.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("idPhase", idPhase);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "phaseSuivi";
	}

	@RequestMapping(value = "/formSuivi", method = RequestMethod.GET)
	public String formSuivi(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idPhase) {
		try {
			Phase phase = pgesMetier.rechercherPhase(idPhase);
			Page<Resultat_Attendu> pageResultats = projetMetier.listeResultatsPhase(phase.getIdPhase(), p, 1000000);
			model.addAttribute("listeResultatsPhase", pageResultats.getContent());
			Page<Personne> pagePersonnes = pgesMetier.listPersonnes(p, 1000000);
			model.addAttribute("listPersonnes", pagePersonnes.getContent());
			model.addAttribute("phase", phase);
			model.addAttribute("suivi",
					new Suivi(new Date(), null, null, null, null, null, null, 0, null, phase, 0, null, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formSuivi";
	}

	@RequestMapping(value = "/rechercherSuivi")
	public String modifierSuivi(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long id) {
		try {
			Suivi suivi = planMetier.consulterSuivi(id);
			model.addAttribute("suivi", suivi);
			Page<Resultat_Attendu> pageResultats = projetMetier.listeResultatsPhase(suivi.getPhase().getIdPhase(), p,
					1000000);
			model.addAttribute("listeResultatsPhase", pageResultats.getContent());
			Page<Personne> pagePersonnes = pgesMetier.listPersonnes(p, 1000000);
			model.addAttribute("listPersonnes", pagePersonnes.getContent());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editSuivi";
	}

	@RequestMapping(value = "/ajouterSuivi", method = RequestMethod.POST)
	public String ajouterSuivi(@Valid Suivi suivi, BindingResult bindingResult) {
		List<Resultat_Attendu> listeResultatsMesure = new ArrayList<Resultat_Attendu>();

		double tauxCumul = 0, tauxEff = 0;
		int sumpoids = 0;

		Long idResultat = suivi.getResultat().getIdResultat();
		Long idMesure = suivi.getResultat().getMesure().getIdMesure();

		if (bindingResult.hasErrors()) {
			return "formSuivi";
		}
		try {
			planMetier.ajouterSuivi(suivi);
			// ======== mise à jour du taux du résultat attendu ===========
			Resultat_Attendu resultat = projetMetier.consulterResultat(idResultat);
			resultat.setTauxeff(suivi.getTauxEff());
			projetMetier.ajouterResultat(resultat);

			// ======== mise a jour du taux d'efficacité de la mesure
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			listeResultatsMesure = projetMetier.listResultatsMesure(idMesure);

			for (int i = 0; i < listeResultatsMesure.size(); i++) {
				sumpoids = sumpoids + listeResultatsMesure.get(i).getPriorite();
			}
			// calcul du taux d'efficacité des resultats attendus
			for (int i = 0; i < listeResultatsMesure.size(); i++) {
				tauxCumul = tauxCumul
						+ listeResultatsMesure.get(i).getTauxeff() * listeResultatsMesure.get(i).getPriorite();
			}
			mesure.setTauxEff(tauxCumul / sumpoids);
			if (tauxEff == 100) {
				mesure.setAppreciation("E");
			}
			if ((tauxEff >= 5) && (tauxEff <= 90)) {
				mesure.setAppreciation("PE");
			}
			if (tauxEff < 5) {
				mesure.setAppreciation("NE");
			}
			projetMetier.ajouterMesure(mesure);
		} catch (Exception e) {

		}

		return "redirect:/phaseSuivi?idPhase=" + suivi.getPhase().getIdPhase();
	}

	@RequestMapping(value = "/editSuivi", method = RequestMethod.POST)
	public String editSuivi(@Valid Suivi suivi, BindingResult bindingResult) {
		List<Resultat_Attendu> listeResultatsMesure = new ArrayList<Resultat_Attendu>();

		double tauxCumul = 0, tauxEff = 0;
		int sumpoids = 0;

		Long idResultat = suivi.getResultat().getIdResultat();
		Long idMesure = suivi.getResultat().getMesure().getIdMesure();

		if (bindingResult.hasErrors()) {
			return "formSuivi";
		}
		try {
			planMetier.ajouterSuivi(suivi);
			// ======== mise à jour du taux du résultat attendu ===========
			Resultat_Attendu resultat = projetMetier.consulterResultat(idResultat);
			resultat.setTauxeff(suivi.getTauxEff());
			projetMetier.ajouterResultat(resultat);

			// ======== mise a jour du taux d'efficacité de la mesure
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			listeResultatsMesure = projetMetier.listResultatsMesure(idMesure);

			for (int i = 0; i < listeResultatsMesure.size(); i++) {
				sumpoids = sumpoids + listeResultatsMesure.get(i).getPriorite();
			}
			// calcul du taux d'efficacité des resultats attendus
			for (int i = 0; i < listeResultatsMesure.size(); i++) {
				tauxCumul = tauxCumul
						+ listeResultatsMesure.get(i).getTauxeff() * listeResultatsMesure.get(i).getPriorite();
			}
			mesure.setTauxEff(tauxCumul / sumpoids);
			if (tauxEff == 100) {
				mesure.setAppreciation("E");
			}
			if ((tauxEff >= 5) && (tauxEff <= 90)) {
				mesure.setAppreciation("PE");
			}
			if (tauxEff < 5) {
				mesure.setAppreciation("NE");
			}
			projetMetier.ajouterMesure(mesure);
		} catch (Exception e) {

		}

		return "redirect:/phaseSuivi?idPhase=" + suivi.getPhase().getIdPhase();
	}

	@RequestMapping(value = "/fermerFormSuivi", method = RequestMethod.GET)
	public String fermerFormSuivi(Long idPhase) {
		return "redirect:/phaseSuivi?idPhase=" + idPhase;
	}
}
