/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.starstel.telcopro.storage.services;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mockito.internal.util.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.starstel.telcopro.stocks.entities.Commande;
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
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

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
     
    public void reportCommande(Commande commande) {
    	this.report(Report.COMMANDE, commande.getMouvmentLines(), getBonCommandeParameters(commande), Storage.DIRECTORY_COMMANDES_DOCS, commande.getReference());
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
    public HashMap<String,Object> getBonCommandeParameters(Commande commande)
    {
        HashMap<String,Object> parameters= new HashMap<String,Object>();

        parameters.put("commande", commande);
        
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
