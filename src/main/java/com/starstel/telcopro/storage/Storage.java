package com.starstel.telcopro.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum Storage {
	ROOT("Medias"),
	DIRECTORY_IMAGES("Medias/Telcopro Images"),
	DIRECTORY_DOCUMENTS("Medias/Telcopro Documents"),
	EMPLOYEES_EXCEL_FILE("Medias/Telcopro Documents/fiche_employes_starstel.xls");
	private String path;

	private Storage(String path) {
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
