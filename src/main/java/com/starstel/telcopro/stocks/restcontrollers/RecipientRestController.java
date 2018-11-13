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

import com.starstel.telcopro.stocks.entities.Recipient;
import com.starstel.telcopro.stocks.entities.RecipientGroupe;
import com.starstel.telcopro.stocks.services.RecipientService;

@CrossOrigin("*")
@RestController
@RequestMapping("stocks/recipients")
public class RecipientRestController {


	@Autowired
	private RecipientService recipientService;
	


	@RequestMapping(value="", method = RequestMethod.GET)
	public List<Recipient> listRecipient() {
		return recipientService.listRecipient();
	}

	@RequestMapping(value="", method = RequestMethod.POST)
	public Recipient createRecipient(@RequestBody Recipient recipient) {
		return recipientService.createRecipient(recipient);
	}

	@RequestMapping(value="", method = RequestMethod.PUT)
	public Recipient editRecipient(@RequestBody Recipient recipient) {
		return recipientService.editRecipient(recipient);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public Boolean deleteRecipient(@PathVariable Long id) {
		return recipientService.deleteRecipient(id);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Recipient getRecipient(@PathVariable Long id) {
		return recipientService.getRecipient(id);
	}
	
	@RequestMapping(value="/groupes", method = RequestMethod.GET)
	public List<RecipientGroupe> listRecipientGroupe() {
		return recipientService.listRecipientGroupe();
	}

	@RequestMapping(value="/groupes", method = RequestMethod.POST)
	public RecipientGroupe createRecipientGroupe(@RequestBody RecipientGroupe recipientGroupe) {
		return recipientService.createRecipientGroupe(recipientGroupe);
	}

	@RequestMapping(value="/groupes", method = RequestMethod.PUT)
	public RecipientGroupe editRecipientGroupe(@RequestBody RecipientGroupe recipientGroupe) {
		return recipientService.editRecipientGroupe(recipientGroupe);
	}

	@RequestMapping(value="/groupes/{id}", method = RequestMethod.DELETE)
	public Boolean deleteRecipientGroupe(@PathVariable Long id) {
		return recipientService.deleteRecipientGroupe(id);
	}

	@RequestMapping(value="/groupes/{id}", method = RequestMethod.GET)
	public RecipientGroupe getRecipientGroupe(@PathVariable Long id) {
		return recipientService.getRecipientGroupe(id);
	}

	@RequestMapping(value="/recipients-of-groupe/{id}", method = RequestMethod.GET)
	public Set<Recipient> listRecipientOfGroupe(@PathVariable Long id) {
		return recipientService.listRecipientOfGroupe(id);
	}

	@RequestMapping(value="/add-recipient-to-groupe", method = RequestMethod.GET)
	public Boolean addRecipientToGroupe(
			@RequestParam(name="recipient") Long idRecipient, 
			@RequestParam(name="groupe") Long idGroupe) {
		return recipientService.addRecipientToGroupe(idRecipient, idGroupe);
	}

	@RequestMapping(value="/groupes/search",method=RequestMethod.GET)
	public List<RecipientGroupe> searchGroupe(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return recipientService.searchGroupe(keyWords);
	}

	@RequestMapping(value="/search",method=RequestMethod.GET)
	public List<Recipient> search(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return recipientService.search(keyWords);
	}
}
