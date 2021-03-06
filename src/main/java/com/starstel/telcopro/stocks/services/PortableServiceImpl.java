package com.starstel.telcopro.stocks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.starstel.telcopro.stocks.entities.Camera;
import com.starstel.telcopro.stocks.entities.Cpu;
import com.starstel.telcopro.stocks.entities.Emplacement;
import com.starstel.telcopro.stocks.entities.Memory;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.Portable;
import com.starstel.telcopro.stocks.entities.PortableCategory;
import com.starstel.telcopro.stocks.entities.PortableUnit;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.entities.SystemOS;
import com.starstel.telcopro.stocks.repositories.AppColorRepository;
import com.starstel.telcopro.stocks.repositories.CameraRepository;
import com.starstel.telcopro.stocks.repositories.CpuRepository;
import com.starstel.telcopro.stocks.repositories.MemoryRepository;
import com.starstel.telcopro.stocks.repositories.PortableCategoryRepository;
import com.starstel.telcopro.stocks.repositories.PortableRepository;
import com.starstel.telcopro.stocks.repositories.PortableUnitRepository;
import com.starstel.telcopro.stocks.repositories.SystemOSRepository;
import com.starstel.telcopro.storage.entities.Storage;
import com.starstel.telcopro.storage.services.Storageable;


@Service
public class PortableServiceImpl implements PortableService {

	@Autowired
	private PortableRepository portableRepository;
	
	@Autowired
	private CameraRepository cameraRepository;
	
	@Autowired
	private SystemOSRepository systemOSRepository;
	
	@Autowired
	private PortableUnitRepository portableUnitRepository;
	
	@Autowired
	private PortableCategoryRepository portableCategoryRepository;
	
	@Autowired
	private MemoryRepository memoryRepository;
	
	@Autowired
	private CpuRepository cpuRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Storageable storager;
	
	@Override
	public Portable save(Portable portable) {
		return loadCalculatedField(portableRepository.save(portable));
	}
	
	@Override
	public Portable save(MultipartFile portableImageFile, Portable portable) {
		String imageName = storager.store(portableImageFile, Storage.DIRECTORY_PORTABLES_IMAGES);
		
		if (portable.getId() == null) {
			portable.setImage(imageName);
		}
		else if(portableImageFile != null) {
				storager.delete(portable.getImage());
				portable.setImage(imageName);
			}
			else {
				if (portable.getImage().isEmpty()) {
					Portable port = getPortable(portable.getId());
					if(!port.getImage().isEmpty()) 
						storager.delete(port.getImage());
				}
			}
		return save(portable);
	}
	
	@Override
	public Boolean delete(Long id) {
		String imageName = getPortable(id).getImage();
		portableRepository.deleteById(id);
		if(!imageName.isEmpty()) 
			storager.delete(imageName);
		return true;
	}

	@Override
	public List<Portable> getPortables() {
		List<Portable> portables = portableRepository.findAll();
		portables.forEach(portable -> {
			portable = loadCalculatedField(portable);
		});
		return portables;
	}

	@Override
	public Portable getPortable(Long id) {
		return portableRepository.findById(id).get();
	}

	@Override
	public List<Emplacement> getEmplacement(Long id) {
		return portableRepository.getEmplacement(id);
	}

	@Override
	public List<Mouvment> getAllMouvment(Long id) {
		return portableRepository.getAllMouvment(id);
	}

	@Override
	public Camera saveCamera(Camera camera) {
		return cameraRepository.save(camera);
	}

	@Override
	public Boolean deleteCamera(Long id) {
		cameraRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Camera> getCameras() {
		return cameraRepository.findAll();
	}

	@Override
	public Camera getCamera(Long id) {
		return cameraRepository.findById(id).get();
	}

	@Override
	public SystemOS saveSystemOS(SystemOS systemOS) {
		return systemOSRepository.save(systemOS);
	}

	@Override
	public Boolean deleteSystemOS(Long id) {
		systemOSRepository.deleteById(id);
		return null;
	}

	@Override
	public List<SystemOS> getSystemOSs() {
		return systemOSRepository.findAll();
	}

	@Override
	public SystemOS getSystemOS(Long id) {
		return systemOSRepository.findById(id).get();
	}

	@Override
	public PortableCategory savePortableCategory(PortableCategory portableCategory) {
		return portableCategoryRepository.save(portableCategory);
	}

	@Override
	public Boolean deletePortableCategory(Long id) {
		portableCategoryRepository.deleteById(id);
		return true;
	}

	@Override
	public List<PortableCategory> getPortableCategories() {
		return portableCategoryRepository.findAll();
	}

	@Override
	public PortableCategory getPortableCategory(Long id) {
		return portableCategoryRepository.findById(id).get();
	}

	@Override
	public PortableUnit savePortableUnit(PortableUnit portableUnit) {
		return portableUnitRepository.save(portableUnit);
	}

	@Override
	public Boolean deletePortableUnit(Long id) {
		portableUnitRepository.deleteById(id);
		return true;
	}

	@Override
	public List<PortableUnit> getPortableUnits() {
		return portableUnitRepository.findAll();
	}

	@Override
	public PortableUnit getPortableUnit(Long id) {
		return portableUnitRepository.findById(id).get();
	}

	@Override
	public Memory saveMemory(Memory memory) {
		return memoryRepository.save(memory);
	}

	@Override
	public Boolean deleteMemory(Long id) {
		memoryRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Memory> getMemories() {
		return memoryRepository.findAll();
	}

	@Override
	public Memory getMemory(Long id) {
		return memoryRepository.findById(id).get();
	}

	@Override
	public Cpu saveCpu(Cpu cpu) {
		return cpuRepository.save(cpu);
	}

	@Override
	public Boolean deleteCpu(Long id) {
		cpuRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Cpu> getCpus() {
		return cpuRepository.findAll();
	}

	@Override
	public Cpu getCpu(Long id) {
		return cpuRepository.findById(id).get();
	}

	@Override
	public Portable getPortable(String numeroSerie) {
		return portableRepository.getPortable(numeroSerie);
	}

	@Override
	public List<Portable> searchPortable(String motCle) {
		return portableRepository.searchPortable("%"+motCle+"%");
	}

	@Override
	public List<Portable> searchPortable(Portable portable) {
		
		return portableRepository.searchPortable(portable);
	}

	@Override
	public List<Camera> searchCameras(String keyWords) {
		return cameraRepository.search("%"+keyWords+"%");
	}

	@Override
	public List<SystemOS> searchSystemOS(String keyWords) {
		return systemOSRepository.search("%"+keyWords+"%");
	}

	@Override
	public List<PortableCategory> searchPortableCategories(String keyWords) {
		return portableCategoryRepository.search("%"+keyWords+"%");
	}

	@Override
	public List<PortableUnit> searchPortableUnits(String keyWords) {
		return portableUnitRepository.search("%"+keyWords+"%");
	}

	@Override
	public List<Memory> searchMemories(String keyWords) {
		return memoryRepository.search("%"+keyWords+"%");
	}

	@Override
	public List<Cpu> searchCpus(String keyWords) {
		return cpuRepository.search("%"+keyWords+"%");
	}
	
	public Portable loadCalculatedField(Portable portable) {
		portable.setQuantity(portableRepository.countItem(portable.getId()));
		return portable;
	}
}
