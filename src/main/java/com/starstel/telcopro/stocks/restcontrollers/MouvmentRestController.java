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
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.MouvmentLine;
import com.starstel.telcopro.stocks.entities.MouvmentType;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.services.MouvmentService;

@CrossOrigin("*")
@RestController
@RequestMapping("stocks/mouvments")
public class MouvmentRestController {
	
	@Autowired
	private MouvmentService mouvmentService;

	@RequestMapping(value="/types", method = RequestMethod.GET)
	public List<MouvmentType> listMouvmentType() {
		return mouvmentService.listMouvmentType();
	}

	@RequestMapping(value="/types", method = RequestMethod.POST)
	public MouvmentType saveMouvmentType(@RequestBody MouvmentType mouvmentType) {
		return mouvmentService.saveMouvmentType(mouvmentType);
	}

	@RequestMapping(value="/types/{id}", method = RequestMethod.DELETE)
	public Boolean deleteMouvmentType(@PathVariable Long id) {
		return mouvmentService.deleteMouvmentType(id);
	}

	@RequestMapping(value="/types/search",method=RequestMethod.GET)
	public List<MouvmentType> searchType(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return mouvmentService.searchType(keyWords);
	}

	@RequestMapping(value="/mouvments-of-type/{id}", method = RequestMethod.GET)
	public Set<Mouvment> getAllMouvmentOfMouvmentType(@PathVariable Long id) {
		return mouvmentService.getAllMouvmentOfMouvmentType(id);
	}

	@RequestMapping(value="/mouvments-of-employee/{id}", method = RequestMethod.GET)
	public Set<Mouvment> getAllMouvmentOfEmployee(@PathVariable Long id) {
		return mouvmentService.getAllMouvmentOfEmployee(id);
	}

	@RequestMapping(value="", method = RequestMethod.GET)
	public List<Mouvment> listMouvment() {
		return mouvmentService.listMouvment();
	}

	@RequestMapping(value="", method = RequestMethod.POST)
	public Mouvment saveMouvment(@RequestBody Mouvment mouvment) {
		return mouvmentService.saveMouvment(mouvment);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public Boolean deleteMouvment(@PathVariable Long id) {
		return mouvmentService.deleteMouvment(id);
	}

	@RequestMapping(value="/search",method=RequestMethod.GET)
	public List<Mouvment> search(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return mouvmentService.search(keyWords);
	}

	@RequestMapping(value="/products-of-mouvment/{id}", method = RequestMethod.GET)
	public Set<Product> getProducts(@PathVariable Long id) {
		return mouvmentService.getProducts(mouvmentService.getMouvment(id));
	}

	@RequestMapping(value="/lines", method = RequestMethod.GET)
	public List<MouvmentLine> listMouvmentLine() {
		return mouvmentService.listMouvmentLine();
	}

	@RequestMapping(value="/lines/{id}", method = RequestMethod.POST)
	public MouvmentLine saveMouvmentLine(@RequestBody MouvmentLine mouvmentLine) {
		return mouvmentService.saveMouvmentLine(mouvmentLine);
	}

	@RequestMapping(value="/lines/{id}", method = RequestMethod.DELETE)
	public Boolean deleteMouvmentLine(@PathVariable Long id) {
		return mouvmentService.deleteMouvmentLine(id);
	}

	@RequestMapping(value="/lines/search",method=RequestMethod.GET)
	public List<MouvmentLine> searchLine(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return mouvmentService.searchLine(keyWords);
	}

	@RequestMapping(value="/lines-of-types/{id}", method = RequestMethod.GET)
	public Set<MouvmentLine> getAllMouvmentLineOfMouvmentType(@PathVariable Long id) {
		return mouvmentService.getAllMouvmentLineOfMouvmentType(id);
	}
	
}
