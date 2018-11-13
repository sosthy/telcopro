package com.starstel.telcopro.stocks.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.starstel.telcopro.stocks.entities.Camera;
import com.starstel.telcopro.stocks.entities.Cpu;
import com.starstel.telcopro.stocks.entities.Emplacement;
import com.starstel.telcopro.stocks.entities.Memory;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.MouvmentLine;
import com.starstel.telcopro.stocks.entities.Portable;
import com.starstel.telcopro.stocks.entities.PortableCategory;
import com.starstel.telcopro.stocks.entities.PortableUnit;
import com.starstel.telcopro.stocks.entities.SystemOS;



public interface PortableService 
{
	// Portable
	Portable save(Portable portable);
	Boolean delete(Long id);
	List<Portable> getPortables();
	Portable getPortable(Long id);
	List<Emplacement> getEmplacement(Long id);
	List<Mouvment> getAllMouvment(Long id);
	Portable getPortable(String numeroSerie);
	List<Portable> searchPortable(String motCle);
	List<Portable> searchPortable(Portable portable);
	
	// Camera
	Camera saveCamera(Camera camera);
	Boolean deleteCamera(Long id);
	List<Camera> getCameras();
	Camera getCamera(Long id);
	List<Camera> searchCameras(String keyWords);

	// SystemOS
	SystemOS saveSystemOS(SystemOS systemOS);
	Boolean deleteSystemOS(Long id);
	List<SystemOS> getSystemOSs();
	SystemOS getSystemOS(Long id);
	List<SystemOS> searchSystemOS(String keyWords);

	// PortableCategory
	PortableCategory savePortableCategory(PortableCategory portableCategory);
	Boolean deletePortableCategory(Long id);
	List<PortableCategory> getPortableCategories();
	PortableCategory getPortableCategory(Long id);
	List<PortableCategory> searchPortableCategories(String keyWords);

	// PortableUnit
	PortableUnit savePortableUnit(PortableUnit portableCategory);
	Boolean deletePortableUnit(Long id);
	List<PortableUnit> getPortableUnits();
	PortableUnit getPortableUnit(Long id);
	List<PortableUnit> searchPortableUnits(String keyWords);

	// Memory
	Memory saveMemory(Memory memory);
	Boolean deleteMemory(Long id);
	List<Memory> getMemories();
	Memory getMemory(Long id);
	List<Memory> searchMemories(String keyWords);

	// Cpu
	Cpu saveCpu(Cpu cpu);
	Boolean deleteCpu(Long id);
	List<Cpu> getCpus();
	Cpu getCpu(Long id);
	List<Cpu> searchCpus(String keyWords);

}
