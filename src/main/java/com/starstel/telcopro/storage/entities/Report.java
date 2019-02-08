package com.starstel.telcopro.storage.entities;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum Report {
	COMMANDE("Reports/Bon_de_commande.jrxml");
	
	private String path;

	private Report(String path) {
		this.path = path;
	}

	public Path getPath() {
		return Paths.get(path);
	}
	public String getName()
	{
		return path;
	}
}
