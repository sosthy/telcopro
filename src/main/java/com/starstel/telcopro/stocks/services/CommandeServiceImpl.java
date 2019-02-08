package com.starstel.telcopro.stocks.services;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starstel.telcopro.stocks.entities.Commande;
import com.starstel.telcopro.stocks.entities.MouvmentLine;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.repositories.CommandeRepository;
import com.starstel.telcopro.storage.services.Reportable;

@Service
@Transactional
public class CommandeServiceImpl implements CommandeService
{
	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private Reportable reporter;
	
	@Override
	public List<Commande> listCommande() 
	{
		return commandeRepository.findAll();
	}
	
	@Override
	public Commande saveCommande(Commande commande) 
	{
		if(commande.getMouvmentLines() != null)
			commande.getMouvmentLines().forEach(mLine -> {
				mLine.setMouvment(commande);
		});
		Commande commandeTest = commandeRepository.save(commande);
		reporter.reportCommande(commandeTest);
		return commandeTest;
	}
	
	@Override
	public Boolean deleteCommande(String reference) 
	{
		Commande m = (Commande) commandeRepository.getByReference(reference);
		commandeRepository.delete(m);
		return true;
	}

	@Override
	public Set<Product> getProducts(Commande commande) {
		
		Set<Product> products = new HashSet<>();
		for(MouvmentLine cl: commande.getMouvmentLines())
			products.add(cl.getProduct());
		
		return products;
	}

	@Override
	public Commande getCommande(Long id) {
		return commandeRepository.findById(id).get();
	}

	@Override
	public List<Commande> search(String keyWords) {
		return commandeRepository.search("%"+keyWords+"%");
	}

	public List<Commande> getAllCommandeDealByEmployee(Long id) {
		return commandeRepository.getAllCommandeDealByEmployee(id);
	}

	public List<Commande> getAllCommandeEmitByEmployee(Long id) {
		return commandeRepository.getAllCommandeEmitByEmployee(id);
	}

	public List<Commande> getAllCommandeReceiveByWorkSpace(Long id) {
		return commandeRepository.getAllCommandeReceiveByWorkSpace(id);
	}
	
	@Override
	public List<Commande> getAllCommandeOfMouvmentType(Long id)
	{
		return commandeRepository.getAllCommandeOfMouvmentType(id);
	}
}
