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

import com.starstel.telcopro.stocks.entities.PointOfSale;
import com.starstel.telcopro.stocks.entities.Recipient;
import com.starstel.telcopro.stocks.entities.RecipientGroupe;
import com.starstel.telcopro.stocks.services.PointOfSaleService;
import com.starstel.telcopro.stocks.services.RecipientService;

@CrossOrigin("*")
@RestController
@RequestMapping("stocks/points-of-sale")
public class PointOfSaleRestController {

	@Autowired
	private PointOfSaleService pointOfSaleService;

	@RequestMapping(value="",method=RequestMethod.GET)
	public List<PointOfSale> listPointOfSale() {
		return pointOfSaleService.getPointOfSales();
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public PointOfSale getPointOfSale(@PathVariable Long id) {
		return pointOfSaleService.getPointOfSale(id);
	}

	@RequestMapping(value="",method=RequestMethod.POST)
	public PointOfSale savePointOfSale(@RequestBody PointOfSale pointOfSale) {
		return pointOfSaleService.save(pointOfSale);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public Boolean deletePointOfSale(@PathVariable Long id) {
		return pointOfSaleService.delete(id);
	}

	@RequestMapping(value="/search",method=RequestMethod.GET)
	public List<PointOfSale> searchLine(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return pointOfSaleService.search(keyWords);
	}
}
