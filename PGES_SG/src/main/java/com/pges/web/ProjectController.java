package com.pges.web;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pges.entities.Activite_Source;
import com.pges.entities.Entreprise;
import com.pges.entities.Impact;
import com.pges.entities.Mesure;
import com.pges.entities.Personne;
import com.pges.entities.Phase;
import com.pges.entities.Projet;
import com.pges.entities.Resultat_Attendu;
import com.pges.entities.Site;
import com.pges.metier.IPgesMetier;
import com.pges.metier.IPlanMetier;
import com.pges.metier.IProjetMetier;

@Controller
public class ProjectController {
	@Autowired
	private IProjetMetier projetMetier;
	@Autowired
	private IPgesMetier pgesMetier;
	@Autowired
	private IPlanMetier planMetier;

	// ============= gestion des projets ==================
	@RequestMapping(value = "/formProjet", method = RequestMethod.GET)
	public String formProjet(Model model, String codeEntr, @RequestParam(name = "page", defaultValue = "0") int p) {
		try {
			Entreprise entreprise = pgesMetier.consulterEntreprise(codeEntr);
			Page<Site> pageSites = pgesMetier.listSites(p, 5);
			model.addAttribute("entreprise", entreprise);
			model.addAttribute("listSites", pageSites.getContent());
			model.addAttribute("projet", new Projet(null, null, new Date(), new Date(), null, new Date(), new Date(),
					null, null, entreprise, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formProjet";
	}

	@RequestMapping(value = "/findSociete", method = RequestMethod.GET)
	public String findSociete(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		try {
			Page<Entreprise> pageSocietes = pgesMetier.chercheEntreprises("%" + mc + "%", p, 5);
			model.addAttribute("listEntreprises", pageSocietes.getContent());
			int pagesCount = pageSocietes.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", mc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findSociete";
	}

	@RequestMapping("/projet")
	public String projets(Model model, @RequestParam(name = "page", defaultValue = "0") int p, String codeEntr) {
		try {
			Entreprise entreprise = pgesMetier.consulterEntreprise(codeEntr);
			model.addAttribute("societe", entreprise);

			Page<Projet> pageProjets = projetMetier.listProjets(codeEntr, p, 5);
			model.addAttribute("listProjets", pageProjets.getContent());
			int pagesCount = pageProjets.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("codeEntr", codeEntr);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "projet";
	}

	@RequestMapping(value = "/ajouterProjet", method = RequestMethod.POST)
	public String ajouterSociete(@Valid Projet projet, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/formProjet?codeEntr=" + projet.getEntreprise().getCodeEntr();
		}
		try {
			Date dateDebut = projet.getDateDebut();
			Date dateFin = projet.getDateFin();
			Long dateDiff = (dateFin.getTime() - dateDebut.getTime()) / (1000 * 60 * 60 * 24);
			projet.setDuree(dateDiff);
			projetMetier.ajouterProjet(projet);
		} catch (Exception e) {
			// model.addAttribute("error", e);
		}

		return "redirect:/projet?codeEntr=" + projet.getEntreprise().getCodeEntr();
	}

	@RequestMapping(value = "/rechercherProjet")
	public String rechercherProjet(Model model, Long id, @RequestParam(name = "page", defaultValue = "0") int p) {
		try {
			Projet projet = projetMetier.consulterProjet(id);
			model.addAttribute("projet", projet);
			Page<Site> pageSites = pgesMetier.listSites(p, 5);
			model.addAttribute("listSites", pageSites.getContent());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editProjet";
	}

	@RequestMapping(value = "/editProjet", method = RequestMethod.POST)
	public String editProjet(@Valid Projet projet, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "editProjet";
		}
		try {
			Date dateDebut = projet.getDateDebut();
			Date dateFin = projet.getDateFin();
			Long dateDiff = (dateFin.getTime() - dateDebut.getTime()) / (1000 * 60 * 60 * 24);
			projet.setDuree(dateDiff);
			projetMetier.ajouterProjet(projet);
		} catch (Exception e) {

		}

		return "redirect:/projet?codeEntr=" + projet.getEntreprise().getCodeEntr();
	}

	@RequestMapping(value = "/fermerFormProjet", method = RequestMethod.GET)
	public String fermerFormSociete(@RequestParam String codeEntr) {
		return "redirect:/projet?codeEntr=" + codeEntr;
	}
	// ============= gestion des phases du projet ==================

	@RequestMapping(value = "/findProjet", method = RequestMethod.GET)
	public String findProjet(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
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
		return "findProjet";
	}

	@RequestMapping("/phase")
	public String phases(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idProjet,
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
		return "phase";
	}

	@RequestMapping(value = "/formPhase", method = RequestMethod.GET)
	public String formPhase(Model model, Long idProjet) {

		try {
			Projet projet = projetMetier.consulterProjet(idProjet);
			model.addAttribute("projet", projet);
			model.addAttribute("phase", new Phase(null, null, new Date(), new Date(), null, projet));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formPhase";
	}

	@RequestMapping(value = "/ajouterPhase", method = RequestMethod.POST)
	public String ajouterPhase(@Valid Phase phase, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formPhase";
		}
		try {
			Date dateDebut = phase.getDateDebut();
			Date dateFin = phase.getDateFin();
			Long dateDiff = (dateFin.getTime() - dateDebut.getTime()) / (1000 * 60 * 60 * 24);
			phase.setDuree(dateDiff);
			pgesMetier.ajouterPhase(phase);
		} catch (Exception e) {

		}

		return "redirect:/phase?idProjet=" + phase.getProjet().getIdProjet();
	}

	@RequestMapping(value = "/supprimerPhase")
	public String supprimerPhase(Long id) {
		try {
			pgesMetier.supprimerPhase(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/phase";
	}

	@RequestMapping(value = "/rechercherPhase")
	public String modifierPhase(Model model, Long id) {
		try {
			Phase phase = pgesMetier.rechercherPhase(id);
			model.addAttribute("phase", phase);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editPhase";
	}

	@RequestMapping(value = "/editPhase", method = RequestMethod.POST)
	public String editPhase(@Valid Phase phase, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formPhase";
		}
		try {
			Date dateDebut = phase.getDateDebut();
			Date dateFin = phase.getDateFin();
			Long dateDiff = (dateFin.getTime() - dateDebut.getTime()) / (1000 * 60 * 60 * 24);
			phase.setDuree(dateDiff);
			pgesMetier.ajouterPhase(phase);
		} catch (Exception e) {

		}

		return "redirect:/phase?idProjet=" + phase.getProjet().getIdProjet();
	}

	@RequestMapping(value = "/fermerFormPhase", method = RequestMethod.GET)
	public String fermerFormPhase(@RequestParam Long idProjet) {
		return "redirect:/phase?idProjet=" + idProjet;
	}

	// ================== gestion des impacts ===============
	@RequestMapping(value = "/findPhase", method = RequestMethod.GET)
	public String findPhase(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idPhase) {
		try {
			Phase phase = pgesMetier.rechercherPhase(idPhase);
			model.addAttribute("phase", phase);

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
		// return "redirect:findPhase?idPhase=" + idPhase;
		return "findPhase";
	}

	@RequestMapping(value = "/findImpact", method = RequestMethod.GET)
	public String findImpact(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
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
		return "findImpact";
	}

	@RequestMapping("/impact")
	public String impacts(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
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
		// return "redirect:/impact?idProjet=" + idProjet;
		return "impact";
	}

	@RequestMapping(value = "/formImpact", method = RequestMethod.GET)
	public String formImpact(Model model, Long idPhase) {
		try {
			Phase phase = pgesMetier.rechercherPhase(idPhase);
			model.addAttribute("phase", phase);
			model.addAttribute("impact", new Impact(null, null, 0, phase));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formImpact";
	}

	@RequestMapping(value = "/ajouterImpact", method = RequestMethod.POST)
	public String ajouterImpact(@Valid Impact impact, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formImpact";
		}
		try {

			projetMetier.ajouterImpact(impact);
		} catch (Exception e) {

		}

		return "redirect:/findPhase?idPhase=" + impact.getPhase().getIdPhase();
	}

	@RequestMapping(value = "/rechercherImpact")
	public String modifierImpact(Model model, Long id) {
		try {
			Impact impact = projetMetier.consulterImpact(id);
			model.addAttribute("impact", impact);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editImpact";
	}

	@RequestMapping(value = "/editImpact", method = RequestMethod.POST)
	public String editImpact(@Valid Impact impact, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formImpact";
		}
		try {
			projetMetier.ajouterImpact(impact);
		} catch (Exception e) {

		}

		return "redirect:/findPhase?idPhase=" + impact.getPhase().getIdPhase();
	}

	@RequestMapping(value = "/fermerFormImpact", method = RequestMethod.GET)
	public String fermerFormImpact(@RequestParam Long idPhase) {
		return "redirect:/findPhase?idPhase=" + idPhase;
	}

	// ================== gestion des activites sources et des
	@RequestMapping(value = "/findActSource", method = RequestMethod.GET)
	public String findActSource(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idImpact) {
		try {
			Impact impact = projetMetier.consulterImpact(idImpact);
			model.addAttribute("impact", impact);

			Page<Activite_Source> pageActSources = projetMetier.listActSources(impact.getIdImpact(), p, 5);
			model.addAttribute("listActSources", pageActSources.getContent());
			int pagesCount = pageActSources.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("idImpact", idImpact);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findActSource";
	}

	@RequestMapping(value = "/formActSource", method = RequestMethod.GET)
	public String formActSource(Model model, Long idImpact) {
		try {
			Impact impact = projetMetier.consulterImpact(idImpact);
			model.addAttribute("impact", impact);
			model.addAttribute("activiteSource", new Activite_Source(null, impact));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formActSource";
	}

	@RequestMapping(value = "/ajouterActSource", method = RequestMethod.POST)
	public String ajouterActSource(@Valid Activite_Source activiteSource, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formMesure";
		}
		try {
			projetMetier.ajouterActSource(activiteSource);
		} catch (Exception e) {

		}

		return "redirect:/findActSource?idImpact=" + activiteSource.getImpact().getIdImpact();
	}

	@RequestMapping(value = "/rechercherActSource")
	public String rechercherActSource(Model model, Long id) {
		try {
			Activite_Source activiteSource = projetMetier.consulterActSource(id);
			model.addAttribute("activiteSource", activiteSource);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editActSource";
	}

	@RequestMapping(value = "/editActSource", method = RequestMethod.POST)
	public String editActSource(@Valid Activite_Source activiteSource, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formActSource";
		}
		try {
			projetMetier.ajouterActSource(activiteSource);
		} catch (Exception e) {

		}

		return "redirect:/findActSource?idImpact=" + activiteSource.getImpact().getIdImpact();
	}

	@RequestMapping(value = "/fermerFormActSource", method = RequestMethod.GET)
	public String fermerFormActSource(@RequestParam Long idImpact) {
		return "redirect:/findActSource?idImpact=" + idImpact;
	}

	// mesures===============
	@RequestMapping(value = "/findMesure", method = RequestMethod.GET)
	public String findMesure(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idImpact) {
		try {
			Impact impact = projetMetier.consulterImpact(idImpact);
			model.addAttribute("impact", impact);
			// Resultat_Attendu resultat =
			// projetMetier.consulterResutatMesure(idMesure);
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
		return "findMesure";
	}

	@RequestMapping(value = "/formMesure", method = RequestMethod.GET)
	public String formMesure(Model model, Long idImpact, @RequestParam(name = "page", defaultValue = "0") int p) {
		try {
			Impact impact = projetMetier.consulterImpact(idImpact);
			Page<Activite_Source> pageActSources = projetMetier.chercherActSourceImpact(impact.getIdImpact(), p,
					1000000);
			model.addAttribute("impact", impact);
			model.addAttribute("listActSourcesImpact", pageActSources.getContent());
			model.addAttribute("mesure", new Mesure(null, null, 0, 0, 0, 0, 0, null, null, impact));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formMesure";
	}

	@RequestMapping(value = "/ajouterMesure", method = RequestMethod.POST)
	public String ajouterMesure(@Valid Mesure mesure, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formMesure";
		}
		try {
			projetMetier.ajouterMesure(mesure);
		} catch (Exception e) {

		}

		return "redirect:/findMesure?idImpact=" + mesure.getImpact().getIdImpact();
	}

	@RequestMapping(value = "/rechercherMesure")
	public String rechercherMesure(Model model, Long id, @RequestParam(name = "page", defaultValue = "0") int p) {
		try {
			Mesure mesure = projetMetier.consulterMesure(id);
			model.addAttribute("mesure", mesure);
			Impact impact = projetMetier.consulterImpact(mesure.getImpact().getIdImpact());
			Page<Activite_Source> pageActSources = projetMetier.chercherActSourceImpact(impact.getIdImpact(), p,
					1000000);
			model.addAttribute("listActSourcesImpact", pageActSources.getContent());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editMesure";
	}

	@RequestMapping(value = "/editMesure", method = RequestMethod.POST)
	public String editMesure(@Valid Mesure mesure, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formMesure";
		}
		try {
			projetMetier.ajouterMesure(mesure);
		} catch (Exception e) {

		}

		return "redirect:/findMesure?idImpact=" + mesure.getImpact().getIdImpact();
	}

	@RequestMapping(value = "/fermerFormMesure", method = RequestMethod.GET)
	public String fermerFormMesure(@RequestParam Long idImpact) {
		return "redirect:/findMesure?idImpact=" + idImpact;
	}

	// =============Résultats attendus ===========================
	@RequestMapping(value = "/findResultat", method = RequestMethod.GET)
	public String findResultat(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idMesure) {
		try {
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			model.addAttribute("mesure", mesure);
			Page<Resultat_Attendu> pageResultats = projetMetier.listeResutatsMesure(mesure.getIdMesure(), p, 5);
			model.addAttribute("listResultats", pageResultats.getContent());
			int pagesCount = pageResultats.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("idMesure", idMesure);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findResultat";
	}

	@RequestMapping(value = "/formResultat", method = RequestMethod.GET)
	public String formResultat(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idMesure) {
		try {
			Mesure mesure = projetMetier.consulterMesure(idMesure);
			model.addAttribute("mesure", mesure);
			model.addAttribute("resultat", new Resultat_Attendu(null, 0, 0, mesure));
			model.addAttribute("idMesure", idMesure);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formResultat";
	}

	@RequestMapping(value = "/ajouterResultat", method = RequestMethod.POST)
	public String ajouterResultat(@Valid Resultat_Attendu resultat, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formResultat";
		}
		try {
			projetMetier.ajouterResultat(resultat);
		} catch (Exception e) {

		}

		return "redirect:/findResultat?idMesure=" + resultat.getMesure().getIdMesure();
	}

	@RequestMapping(value = "/rechercherResultat")
	public String rechercherResultat(Model model, Long id, @RequestParam(name = "page", defaultValue = "0") int p) {
		try {
			Resultat_Attendu resultat = projetMetier.consulterResultat(id);
			model.addAttribute("resultat", resultat);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editResultat";
	}

	@RequestMapping(value = "/fermerFormResultat", method = RequestMethod.GET)
	public String fermerFormResultat(@RequestParam Long idMesure) {
		return "redirect:/findResultat?idMesure=" + idMesure;
	}

}
