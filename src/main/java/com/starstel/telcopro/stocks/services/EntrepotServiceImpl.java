package com.starstel.telcopro.stocks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starstel.telcopro.stocks.entities.Emplacement;
import com.starstel.telcopro.stocks.entities.Entrepot;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.repositories.EmplacementRepository;
import com.starstel.telcopro.stocks.repositories.EntrepotRepository;

@Service
public class EntrepotServiceImpl implements EntrepotService
{
	@Autowired
	private EntrepotRepository entrepotRepository;
	@Autowired
	private EmplacementRepository emplacementRepository;

	@Override
	public List<Entrepot> listEntrepot() 
	{
		return  entrepotRepository.findAll();
	}

	@Override
	public Entrepot getEntrepot(Long id) 
	{
		return entrepotRepository.findById(id).get();
	}

	@Override
	public Entrepot saveEntrepot(Entrepot entrepot)
	{
		return entrepotRepository.save(entrepot);
	}

	@Override
	public Boolean deleteEntrepot(Long id) 
	{
		entrepotRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Emplacement> listEmplacement() 
	{
		List<Emplacement> list=emplacementRepository.findAll();
		return list;
	}

	@Override
	public Emplacement getEmplacement(Long id) 
	{
		return emplacementRepository.findById(id).get();
	}

	@Override
	public Emplacement saveEmplacement(Emplacement emplacement) 
	{
		return emplacementRepository.save(emplacement);
	}

	@Override
	public Boolean deleteEmplacement(Long id) 
	{
		emplacementRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Mouvment> getAllMouvmentOfEntrepot(Long id) 
	{
		Entrepot entrepot = entrepotRepository.findById(id).get();
		
		if(entrepot != null)
		{
			List<Mouvment> list=new ArrayList<Mouvment>(entrepot.getMouvments());
			return list;
		}
		else
			return null;
	}

	@Override
	public List<Product> getProductsOfEntrepot(Long id) 
	{
		List<Product> list=entrepotRepository.getAllStockOfEntrepot(id);
		return list;
	}

	@Override
	public Long getStockCountOfEntrepot(Long id) 
	{
		return Long.valueOf(entrepotRepository.getAllStockOfEntrepot(id).size());
	}

	@Override
	public Long getProductItemCountOfEntrepot(Long id) 
	{
		return getPortableItemCountOfEntrepot(id);
	}

	@Override
	public Long getPortableItemCountOfEntrepot(Long id) 
	{
		return entrepotRepository.getPortableItemCountOfEntrepot(id);
	}

	@Override
	public List<Emplacement> getAllEmplacementOfEntrepot(Long id)
	{
		System.out.println(id + "-----------------==========");
		Entrepot entrepot = entrepotRepository.findById(id).get();
		
		if(entrepot != null)
		{
			List<Emplacement> list=new ArrayList<Emplacement>(entrepot.getEmplacements());
			return list;
		}
		else
			return null;
	}

	@Override
	public List<Product> getAllStockOfEmplacement(Long id) 
	{
		Emplacement emplacement = emplacementRepository.findById(id).get();
		
		if(emplacement != null)
		{
			List<Product> list=new ArrayList<Product>(emplacement.getProducts());
			return list;
		}
		else
			return null;
	}

	@Override
	public Long getStockCountOfEmplacement(Long id) 
	{
		Emplacement emplacement = emplacementRepository.findById(id).get();
		
		if(emplacement != null)
			return Long.valueOf(emplacement.getProducts().size());
		else
			return null;
	}

	@Override
	public Long getProductItemCountOfEmplacement(Long id)
	{
		return entrepotRepository.getPortableItemCountOfEmplacement(id);
	}

	@Override
	public Boolean isSpaced(Entrepot entrepot) {
		return entrepot.getVolume()<entrepot.getVolumeSecurity();
	}

	@Override
	public Boolean isAddPossible(Entrepot entrepot, Product product) {
		return (entrepot.getVolume() + product.getVolume()) <= entrepot.getVolumeSecurity();
	}

	@Override
	public Boolean isAddPossible(Entrepot entrepot, Set<Product> products) {
		double volumeProducts=0;
		
		for(Product product: products)
			volumeProducts += product.getVolume();
		
		return (entrepot.getVolume() + volumeProducts) <= entrepot.getVolumeSecurity();
	}

	@Override
	public List<Entrepot> search(String keyWords) {
		return entrepotRepository.search("%"+keyWords+"%");
	}

	@Override
	public List<Emplacement> searchEmplacement(String keyWords) {
		return emplacementRepository.search("%"+keyWords+"%");
	}
}
