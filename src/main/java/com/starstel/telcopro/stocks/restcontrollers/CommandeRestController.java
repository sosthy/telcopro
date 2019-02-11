package com.starstel.telcopro.stocks.restcontrollers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starstel.telcopro.stocks.entities.Entrepot;
import com.starstel.telcopro.stocks.entities.Commande;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.services.CommandeService;
import com.starstel.telcopro.storage.services.Reportable;

@CrossOrigin("*")
@RestController
@RequestMapping("stocks/commandes")
public class CommandeRestController {
	
	@Autowired
	private CommandeService commandeService;
	@Autowired
	private Reportable reporter;

	@RequestMapping(value="/commandes-of-type/{id}", method = RequestMethod.GET)
	public List<Commande> getAllCommandeOfMouvmentType(@PathVariable Long id) {
		return commandeService.getAllCommandeOfMouvmentType(id);
	}

	@RequestMapping(value="/commandes-deal-by-employee/{id}", method = RequestMethod.GET)
	public List<Commande> getAllCommandeDealByEmployee(@PathVariable Long id) {
		return commandeService.getAllCommandeDealByEmployee(id);
	}

	@RequestMapping(value="/commandes-receive-by-workspace/{id}", method = RequestMethod.GET)
	public List<Commande> getAllCommandeReceiveByWorkSpace(@PathVariable Long id) {
		return commandeService.getAllCommandeReceiveByWorkSpace(id);
	}

	@RequestMapping(value="/commandes-emit-by-employee/{id}", method = RequestMethod.GET)
	public List<Commande> getAllCommandeOfEmployee(@PathVariable Long id) {
		return commandeService.getAllCommandeEmitByEmployee(id);
	}

	@RequestMapping(value="", method = RequestMethod.GET)
	public List<Commande> listCommande() {
		return commandeService.listCommande();
	}

	@RequestMapping(value="", method = RequestMethod.POST)
	public Commande saveCommande(@RequestBody Commande commande) {
		Commande commandeTest = commandeService.saveCommande(commande);
		reporter.reportCommande(commandeTest);
		return commandeTest;
	}

	@RequestMapping(value="/{reference}", method = RequestMethod.DELETE)
	public Boolean deleteCommande(@PathVariable String reference) {
		return commandeService.deleteCommande(reference);
	}

	@RequestMapping(value="/search",method=RequestMethod.GET)
	public List<Commande> search(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return commandeService.search(keyWords);
	}

	@RequestMapping(value="/products-of-commande/{id}", method = RequestMethod.GET)
	public Set<Product> getProducts(@PathVariable Long id) {
		return commandeService.getProducts(commandeService.getCommande(id));
	}
	
}
