package com.pges.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.pges.entities.Acteur;
import com.pges.entities.Entreprise;
import com.pges.entities.Personne;
import com.pges.entities.Promoteur;
import com.pges.entities.Responsable;
import com.pges.entities.Site;
import com.pges.metier.IPgesMetier;

@Controller
public class PgesController {
	@Autowired
	private IPgesMetier pgesMetier;
	@Autowired
	private ApplicationContext applicationContext;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	// ===========gestion des peronnes============
	@RequestMapping("/agent")
	public String agents(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		try {
			Page<Personne> pagePersonnes = pgesMetier.chercherPersonnes("%" + mc + "%", p, 5);
			model.addAttribute("listPersonnes", pagePersonnes.getContent());
			int pagesCount = pagePersonnes.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", mc);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "agent";
	}

	@RequestMapping(value = "/formAgent", method = RequestMethod.GET)
	public String formAgent(Model model) {
		try {
			model.addAttribute("agent", new Acteur(null, null, null, null, null, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formAgent";
	}

	@RequestMapping(value = "/ajouterPersonne", method = RequestMethod.POST)
	public String ajouterPersonne(Model model, String typePers, String nomPersonne, String pnomPersonne, String sexe,
			String adresse, String telPersonne, String fonction) {
		try {
			if (typePers.equals("ACT")) {
				pgesMetier.ajouterPersonne(new Acteur(nomPersonne, pnomPersonne, sexe, adresse, telPersonne, fonction));
			} else if (typePers.equals("PRO")) {
				pgesMetier.ajouterPersonne(
						new Promoteur(nomPersonne, pnomPersonne, sexe, adresse, telPersonne, fonction));
			}
			if (typePers.equals("RES")) {
				pgesMetier.ajouterPersonne(
						new Responsable(nomPersonne, pnomPersonne, sexe, adresse, telPersonne, fonction));
			}

		} catch (Exception e) { // TODO: handle exception
		}

		return "redirect:/agent";
	}

	@RequestMapping(value = "/rechercherPersonne")
	public String modifierPersonne(Model model, Long id) {
		try {
			Personne pers = pgesMetier.rechercherPersonne(id);
			model.addAttribute("pers", pers);
			String tyPers = pers.getClass().getSimpleName();
			if (tyPers.equals("Acteur")) {
				model.addAttribute("tyPers", "ACT");
			}
			if (tyPers.equals("Promoteur")) {
				model.addAttribute("tyPers", "PRO");
			}
			if (tyPers.equals("Responsable")) {
				model.addAttribute("tyPers", "RES");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editAgent";
	}

	@RequestMapping(value = "/editAgent", method = RequestMethod.POST)
	public String editAgent(Model model, Long idPersonne, String typePers, String nomPersonne, String pnomPersonne,
			String sexe, String adresse, String telPersonne, String fonction) {
		try {
			if (typePers.equals("ACT")) {
				pgesMetier.ajouterPersonne(new Acteur(nomPersonne, pnomPersonne, sexe, adresse, telPersonne, fonction));
			} else if (typePers.equals("PRO")) {
				pgesMetier.ajouterPersonne(
						new Promoteur(nomPersonne, pnomPersonne, sexe, adresse, telPersonne, fonction));
			}
			if (typePers.equals("RES")) {
				pgesMetier.ajouterPersonne(
						new Responsable(nomPersonne, pnomPersonne, sexe, adresse, telPersonne, fonction));
			}
		} catch (Exception e) {

		}

		return "redirect:/agent";
	}

	@RequestMapping(value = "/supprimerPersonne")
	public String supprimerPersonne(Long id) {
		try {
			pgesMetier.supprimerPersonne(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/agent";
	}

	@RequestMapping(value = "/fermer")
	public String fermer() {
		return "redirect:/agent";
	}

	@RequestMapping(value = "/etatAgent", method = RequestMethod.GET)
	public ModelAndView agent() {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/reports/agent.jrxml");
		view.setApplicationContext(applicationContext);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataSource", pgesMetier.report());
		return new ModelAndView(view, params);
	}

	// ===========gestion des societes============
	@RequestMapping(value = "/findPersonne", method = RequestMethod.GET)
	public String findPersonne(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		// model.addAttribute("promoteur", new Promoteur());
		try {
			Page<Personne> pagePersonnes = pgesMetier.chercherPersonnes("%" + mc + "%", p, 5);
			model.addAttribute("listPersonnes", pagePersonnes.getContent());
			int pagesCount = pagePersonnes.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", mc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findPersonne";
	}

	@RequestMapping("/societe")
	public String societes(Model model, @RequestParam(name = "page", defaultValue = "0") int p, Long idPersonne) {
		try {
			Personne personne = pgesMetier.rechercherPersonne(idPersonne);
			model.addAttribute("personne", personne);

			Page<Entreprise> pageEntreprises = pgesMetier.listEntreprises(idPersonne, p, 5);
			model.addAttribute("listEntreprises", pageEntreprises.getContent());
			int pagesCount = pageEntreprises.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("idPersonne", idPersonne);

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "societe";
	}

	@RequestMapping(value = "/formSociete", method = RequestMethod.GET)
	public String formSociete(Model model, Long idPersonne) {
		try {
			Personne personne = pgesMetier.rechercherPersonne(idPersonne);
			model.addAttribute("personne", personne);
			model.addAttribute("societe", new Entreprise(null, null, null, 0, null, null, null, personne));
			model.addAttribute("idPersonne", idPersonne);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "formSociete";
	}

	@RequestMapping(value = "/ajouterSociete", method = RequestMethod.POST)
	public String ajouterSociete(@Valid Entreprise entr, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/formSociete?idPersonne=" + entr.getPersonne().getIdPersonne();
			// return "formSociete";
		}
		try {
			pgesMetier.ajouterSociete(entr);
		} catch (Exception e) {
			// model.addAttribute("error", e);
		}

		return "redirect:/societe?idPersonne=" + entr.getPersonne().getIdPersonne();
		// return "redirect:/societe";
	}

	@RequestMapping(value = "/supprimerSociete/{idPersonne}", method = RequestMethod.GET)
	public String supprimerSociete(String codeEntr) {
		try {
			pgesMetier.supprimerSociete(codeEntr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/societesociete?idPersonne";
	}

	@RequestMapping(value = "/rechercherSociete")
	public String modifierSociete(Model model, String codeEntr) {
		try {
			Entreprise entr = pgesMetier.consulterEntreprise(codeEntr);
			model.addAttribute("societe", entr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editSociete";
	}

	@RequestMapping(value = "/editSociete", method = RequestMethod.POST)
	public String editSociete(@Valid Entreprise entr, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "editSociete";
		}
		try {
			pgesMetier.ajouterSociete(entr);
		} catch (Exception e) {

		}

		return "redirect:/societe?idPersonne=" + entr.getPersonne().getIdPersonne();
	}

	@RequestMapping(value = "/fermerFormSociete", method = RequestMethod.GET)
	public String fermerFormSociete(@RequestParam Long idPersonne) {
		return "redirect:/societe?idPersonne=" + idPersonne;
	}

	// ================== gestion des sites ===============
	@RequestMapping("/site")
	public String sites(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		try {
			Page<Site> pageSites = pgesMetier.chercherSites("%" + mc + "%", p, 5);
			// Page<Site> pageSites = pgesMetier.listSites(p, 5);
			model.addAttribute("listSites", pageSites.getContent());
			int pagesCount = pageSites.getTotalPages();
			int[] pages = new int[pagesCount];
			for (int i = 0; i < pagesCount; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("currentPage", p);
			model.addAttribute("motCle", mc);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "site";
	}

	@RequestMapping(value = "/formSite", method = RequestMethod.GET)
	public String formSite(Model model) {
		model.addAttribute("site", new Site());
		return "formSite";
	}

	@RequestMapping(value = "/ajouterSite", method = RequestMethod.POST)
	public String ajouterSite(@Valid Site site, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formSite";
		}
		try {
			pgesMetier.ajouterSite(site);
		} catch (Exception e) {

		}

		return "redirect:/site";
	}

	@RequestMapping(value = "/supprimerSite")
	public String supprimerSite(Long id) {
		try {
			pgesMetier.supprimerSite(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/site";
	}

	@RequestMapping(value = "/rechercherSite")
	public String modifierSite(Model model, Long id) {
		try {
			Site site = pgesMetier.rechercherSite(id);
			model.addAttribute("site", site);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "editSite";
	}

	@RequestMapping(value = "/editSite", method = RequestMethod.POST)
	public String editSite(@Valid Site site, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "editSite";
		}
		try {
			pgesMetier.ajouterSite(site);
		} catch (Exception e) {

		}

		return "redirect:/site";
	}

	@RequestMapping(value = "/fermerFormSite")
	public String fermerFormSite() {
		return "redirect:/site";
	}
}
