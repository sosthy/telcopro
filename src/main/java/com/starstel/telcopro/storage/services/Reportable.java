package com.starstel.telcopro.storage.services;

import java.util.Collection;
import java.util.HashMap;

import com.starstel.telcopro.stocks.entities.Commande;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.storage.entities.Report;
import com.starstel.telcopro.storage.entities.Storage;

public interface Reportable {
	String getPathResourceFile(String fileName);
	HashMap<String,Object> toParameters(String key, Object value);
	void reportCommande(Commande commande);
	void reportOutPut(Mouvment mouvment);
	void report(Report report, Collection<?> dataSource, HashMap<String,Object> parameters, Storage directory, String fileName);
}
