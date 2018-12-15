package com.starstel.telcopro.storage.entities;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum Storage {
	ROOT("Telcopro"),
	DIRECTORY_EMPLOYEES_IMAGES("Telcopro/Employees/Images"),
	DIRECTORY_RECIPIENTS_IMAGES("Telcopro/Recipients/Images"),
	DIRECTORY_PRODUCTS_IMAGES("Telcopro/Products/Images"),
	DIRECTORY_PORTABLES_IMAGES("Telcopro/Portables/Images"),
	DIRECTORY_MOUVMENTS_DOCS("Telcopro/Mouvments/Documents");
	
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
