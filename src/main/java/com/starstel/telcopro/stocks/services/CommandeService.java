package com.starstel.telcopro.stocks.services;

import java.util.List;
import java.util.Set;

import com.starstel.telcopro.stocks.entities.Commande;
import com.starstel.telcopro.stocks.entities.Product;

public interface CommandeService 
{
	// Commande
	List<Commande> listCommande();
	Commande saveCommande(Commande commande);
	Boolean deleteCommande(String reference);
	Commande getCommande(Long id);
	Set<Product> getProducts(Commande commande);
	List<Commande> getAllCommandeDealByEmployee(Long id);
	List<Commande> getAllCommandeEmitByEmployee(Long id);
	List<Commande> getAllCommandeReceiveByWorkSpace(Long id);
	List<Commande> getAllCommandeOfMouvmentType(Long id);
	List<Commande> search(String keyWords);
}
