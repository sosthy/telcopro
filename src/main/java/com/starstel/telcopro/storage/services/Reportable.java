package com.starstel.telcopro.storage.services;

import java.util.Collection;
import java.util.HashMap;

import com.starstel.telcopro.stocks.entities.Commande;
import com.starstel.telcopro.storage.entities.Report;
import com.starstel.telcopro.storage.entities.Storage;

public interface Reportable {
	String getPathResourceFile(String fileName);
	void reportCommande(Commande commande);
	void report(Report report, Collection<?> dataSource, HashMap<String,Object> parameters, Storage directory, String fileName);
}
