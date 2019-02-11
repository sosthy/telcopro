/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starstel.telcopro.storage.services;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.starstel.telcopro.stocks.entities.Commande;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.PortableItem;
import com.starstel.telcopro.storage.entities.Report;
import com.starstel.telcopro.storage.entities.Storage;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author USER
 */
@Service
public class ReportManager implements Reportable {
    
    private JasperReport reporter;
    private JasperPrint printer;
    
    @Autowired
    private ResourceLoader  loader;

    /**
	 * Méthode permettant de créer un bon de commande
	 * 
	 * @param commande 
	 * La commande du bon.
	 */
	@Override
    public void reportCommande(Commande commande) {
    	this.report(Report.COMMANDE, commande.getMouvmentLines(), toParameters("commande",commande), Storage.DIRECTORY_COMMANDES_DOCS, commande.getReference());
    }
    /**
	 * Méthode permettant de créer un bon de sortie
	 * 
	 * @param mouvment 
	 * Les transactions du bon.
	 */
	@Override
    public void reportOutPut(Mouvment mouvment) {
		Set<PortableItem> items = new HashSet<>();
		mouvment.getMouvmentLines().forEach(ml -> {
			items.addAll(ml.getProductsItem());
		});
		this.report(Report.OUTPUT, items, toParameters("mouvment", mouvment), Storage.DIRECTORY_MOUVMENTS_DOCS, mouvment.getReference());
    }
    /**
     * Méthode permettant de créer un état à partir du fichier jrxml et une source de données
     * 
     * @param report 
     * Chemin fichier jrxml
     * @param dataSource
     * Source de données
     * @param parameters
     * Paramètres à integrer dans l'état
     * @param directory
     * Le dossier de sauvegarde.
     * @param fileName
     * Le nom du fichier à produire.
     */
	@Override
    public void report(Report report, Collection<?> dataSource, HashMap<String,Object> parameters, Storage directory, String fileName)
    {
    	File file = new File(directory.getName() + fileName + ".pdf");
    	String path = getPathResourceFile(report.getName());
    	try {
            reporter= JasperCompileManager.compileReport(path);
            printer= JasperFillManager.fillReport(reporter, parameters, new JRBeanCollectionDataSource(dataSource));
            JasperExportManager.exportReportToPdfFile(printer, file.toString());
        } catch (Exception ex) {
        	System.err.println("Error ReportManager.report = " + ex.getMessage());
        }
    }
    
	@Override
    public HashMap<String,Object> toParameters(String key, Object value)
    {
        HashMap<String,Object> parameters= new HashMap<String,Object>();

        parameters.put(key, value);
        
        return parameters;
    }
    
	@Override
	public String getPathResourceFile(String fileName) {

    	String path = "";
    	try {
    		path = loader.getResource("classpath:" + fileName).getURI().getPath();
		} catch (IOException e) {
        	System.err.println("Error ReportManager.getPathResourceFile = " + e.getMessage());
		}
		return path;
	}
}
