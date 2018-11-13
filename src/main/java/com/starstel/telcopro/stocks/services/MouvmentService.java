package com.starstel.telcopro.stocks.services;

import java.util.List;
import java.util.Set;

import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.MouvmentLine;
import com.starstel.telcopro.stocks.entities.MouvmentType;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.entities.Recipient;

public interface MouvmentService 
{
	// MouvmentType
	List<MouvmentType> listMouvmentType();
	MouvmentType saveMouvmentType(MouvmentType mouvmentType);
	Boolean deleteMouvmentType(Long id);
	Set<Mouvment> getAllMouvmentOfMouvmentType(Long id);
	Set<Mouvment> getAllMouvmentOfEmployee(Long id);
	List<MouvmentType> searchType(String keyWords);
	
	// Mouvment
	List<Mouvment> listMouvment();
	Mouvment saveMouvment(Mouvment mouvment);
	Boolean deleteMouvment(Long id);
	Set<Product> getProducts(Mouvment mouvment);
	List<Mouvment> search(String keyWords);

	// MouvmentLine
	List<MouvmentLine> listMouvmentLine();
	MouvmentLine saveMouvmentLine(MouvmentLine mouvmentLine);
	Boolean deleteMouvmentLine(Long id);
	Set<MouvmentLine> getAllMouvmentLineOfMouvmentType(Long id);
	Mouvment getMouvment(Long id);
	List<MouvmentLine> searchLine(String keyWords);
}
