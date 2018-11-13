package com.starstel.telcopro.stocks.services;

import java.util.List;
import java.util.Set;

import com.starstel.telcopro.stocks.entities.Emplacement;
import com.starstel.telcopro.stocks.entities.Entrepot;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.Product;

public interface EntrepotService 
{
	// Entrepot
	List<Entrepot> listEntrepot();
	Entrepot getEntrepot(Long id);
	Entrepot saveEntrepot(Entrepot entrepot);
	Boolean deleteEntrepot(Long id);
	List<Product> getProductsOfEntrepot(Long id);
	List<Mouvment> getAllMouvmentOfEntrepot(Long id);
	Long getStockCountOfEntrepot(Long id);
	Long getProductItemCountOfEntrepot(Long id);
	Long getPortableItemCountOfEntrepot(Long id);
	List<Entrepot> search(String keyWords);

	Boolean isSpaced(Entrepot entrepot);
	Boolean isAddPossible(Entrepot entrepot, Product product);
	Boolean isAddPossible(Entrepot entrepot, Set<Product> product);
	
	// Emplacement
	List<Emplacement> listEmplacement();
	Emplacement getEmplacement(Long id);
	Emplacement saveEmplacement(Emplacement emplacement);
	Boolean deleteEmplacement(Long id);
	List<Emplacement> getAllEmplacementOfEntrepot(Long id);
	List<Product> getAllStockOfEmplacement(Long id);
	Long getStockCountOfEmplacement(Long id);
	Long getProductItemCountOfEmplacement(Long id);
	List<Emplacement> searchEmplacement(String keyWords);
}
