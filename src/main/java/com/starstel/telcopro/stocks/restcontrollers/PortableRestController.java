package com.starstel.telcopro.stocks.restcontrollers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.starstel.telcopro.stocks.entities.AppColor;
import com.starstel.telcopro.stocks.entities.Camera;
import com.starstel.telcopro.stocks.entities.Cpu;
import com.starstel.telcopro.stocks.entities.Emplacement;
import com.starstel.telcopro.stocks.entities.Memory;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.Portable;
import com.starstel.telcopro.stocks.entities.PortableCategory;
import com.starstel.telcopro.stocks.entities.PortableItem;
import com.starstel.telcopro.stocks.entities.PortableUnit;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.entities.SystemOS;
import com.starstel.telcopro.stocks.services.AppColorService;
import com.starstel.telcopro.stocks.services.PortableItemService;
import com.starstel.telcopro.stocks.services.PortableService;
import com.starstel.telcopro.storage.Storageable;

@CrossOrigin("*")
@RestController
@RequestMapping("/stocks/portables")
public class PortableRestController {

	@Autowired
	private PortableService portableService;
	@Autowired
	private AppColorService appColorService;
	@Autowired
	private PortableItemService portableItemService;
	@Autowired
	private Storageable storageable;
	

	@RequestMapping(value="", method = RequestMethod.GET)
	public List<Portable> getPortables()
	{
		return portableService.getPortables();
	}

	@RequestMapping(value="", method = RequestMethod.POST)
	public Portable save(@RequestBody Portable portable, @RequestParam(name="photo") MultipartFile photo) {
		storageable.store(photo);
		return portableService.save(portable);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		return portableService.delete(id);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Portable getPortable(@PathVariable Long id) {
		return portableService.getPortable(id);
	}

	@RequestMapping(value="/search", method = RequestMethod.GET)
	public List<Portable> searchPortable(@RequestParam(name="mc",defaultValue="") String motCle) {
		return portableService.searchPortable(motCle);
	}

	@RequestMapping(value="/search", method = RequestMethod.POST)
	public List<Portable> searchPortable(@RequestBody Portable portable) {
		return portableService.searchPortable(portable);
	}
	
	@RequestMapping(value="/emplacement-of-portable/{id}", method = RequestMethod.GET)
	public List<Emplacement> getEmplacement(@PathVariable Long id) {
		return portableService.getEmplacement(id);
	}

	@RequestMapping(value="/mouvment-of-portable/{id}", method = RequestMethod.GET)
	public List<Mouvment> getAllMouvment(@PathVariable Long id) {
		return portableService.getAllMouvment(id);
	}

	@RequestMapping(value="/cameras", method = RequestMethod.POST)
	public Camera saveCamera(@RequestBody Camera camera) {
		return portableService.saveCamera(camera);
	}

	@RequestMapping(value="/cameras/{id}", method = RequestMethod.DELETE)
	public Boolean deleteCamera(@PathVariable Long id) {
		return portableService.deleteCamera(id);
	}

	@RequestMapping(value="/cameras", method = RequestMethod.GET)
	public List<Camera> getCameras() {
		return portableService.getCameras();
	}

	@RequestMapping(value="/cameras/search",method=RequestMethod.GET)
	public List<Camera> searchCameras(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return portableService.searchCameras(keyWords);
	}

	@RequestMapping(value="/cameras/{id}", method = RequestMethod.GET)
	public Camera getCamera(@PathVariable Long id) {
		return portableService.getCamera(id);
	}

	@RequestMapping(value="/system-os", method = RequestMethod.POST)
	public SystemOS saveSystemOS(@RequestBody SystemOS systemOS) {
		return portableService.saveSystemOS(systemOS);
	}

	@RequestMapping(value="/system-os/{id}", method = RequestMethod.DELETE)
	public Boolean deleteSystemOS(@PathVariable Long id) {
		return portableService.deleteSystemOS(id);
	}

	@RequestMapping(value="/system-os", method = RequestMethod.GET)
	public List<SystemOS> getSystemOSs() {
		return portableService.getSystemOSs();
	}

	@RequestMapping(value="/system-os/search",method=RequestMethod.GET)
	public List<SystemOS> searchSystemOS(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return portableService.searchSystemOS(keyWords);
	}

	@RequestMapping(value="/system-os/{id}", method = RequestMethod.GET)
	public SystemOS getSystemOS(@PathVariable Long id) {
		return portableService.getSystemOS(id);
	}

	@RequestMapping(value="/categories", method = RequestMethod.POST)
	public PortableCategory savePortableCategory(@RequestBody PortableCategory portableCategory) {
		return portableService.savePortableCategory(portableCategory);
	}

	@RequestMapping(value="/categories/{id}", method = RequestMethod.DELETE)
	public Boolean deletePortableCategory(@PathVariable Long id) {
		return portableService.deletePortableCategory(id);
	}

	@RequestMapping(value="/categories", method = RequestMethod.GET)
	public List<PortableCategory> getPortableCategories() {
		return portableService.getPortableCategories();
	}

	@RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
	public PortableCategory getPortableCategory(@PathVariable Long id) {
		return portableService.getPortableCategory(id);
	}

	@RequestMapping(value="/categories/search",method=RequestMethod.GET)
	public List<PortableCategory> searchPortableCategories(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return portableService.searchPortableCategories(keyWords);
	}

	@RequestMapping(value="/units", method = RequestMethod.POST)
	public PortableUnit savePortableUnit(@RequestBody PortableUnit portableCategory) {
		return portableService.savePortableUnit(portableCategory);
	}

	@RequestMapping(value="/units/{id}", method = RequestMethod.DELETE)
	public Boolean deletePortableUnit(@PathVariable Long id) {
		return portableService.deletePortableUnit(id);
	}

	@RequestMapping(value="/units", method = RequestMethod.GET)
	public List<PortableUnit> getPortableUnits() {
		return portableService.getPortableUnits();
	}

	@RequestMapping(value="/units/{id}", method = RequestMethod.GET)
	public PortableUnit getPortableUnit(@PathVariable Long id) {
		return portableService.getPortableUnit(id);
	}

	@RequestMapping(value="/units/search",method=RequestMethod.GET)
	public List<PortableUnit> searchPortableUnits(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return portableService.searchPortableUnits(keyWords);
	}

	@RequestMapping(value="/colors-of-portable", method = RequestMethod.GET)
	public List<String> getPortableColor() {
		return appColorService.getPortableColor();
	}

	@RequestMapping(value="/color/{color}", method = RequestMethod.GET)
	public List<Portable> getPortableColor(@PathVariable String color) {
		return appColorService.getPortableByColor(color);
	}

	@RequestMapping(value="/items", method = RequestMethod.GET)
	public List<PortableItem> getPortableItems() {
		return portableItemService.getPortableItems();
	}

	@RequestMapping(value="/items/{id}", method = RequestMethod.GET)
	public PortableItem getPortableItem(@PathVariable Long id) {
		return portableItemService.getPortableItem(id);
	}

	@RequestMapping(value="/items", method = RequestMethod.POST)
	public PortableItem saveItem(PortableItem portableItem) {
		return portableItemService.save(portableItem);
	}

	@RequestMapping(value="/items/{id}", method = RequestMethod.DELETE)
	public Boolean deleteItem(@PathVariable Long id) {
		portableItemService.delete(id);
		return true;
	}

	@RequestMapping(value="/search-items", method = RequestMethod.GET)
	public List<PortableItem> getPortableItems(@RequestParam(name="mc",defaultValue="") String mc) {
		return portableItemService.searchItems(mc);
	}

	@RequestMapping(value="/num/{numeroSerie}", method = RequestMethod.GET)
	public Portable getPortable(@PathVariable String numeroSerie) {
		return portableService.getPortable(numeroSerie);
	}

	@RequestMapping(value="/memories", method = RequestMethod.POST)
	public Memory saveMemory(@RequestBody Memory memory) {
		return portableService.saveMemory(memory);
	}

	@RequestMapping(value="/memories/{id}", method = RequestMethod.DELETE)
	public Boolean deleteMemory(@PathVariable Long id) {
		return portableService.deleteMemory(id);
	}

	@RequestMapping(value="/memories", method = RequestMethod.GET)
	public List<Memory> getMemories() {
		return portableService.getMemories();
	}

	@RequestMapping(value="/memories/{id}", method = RequestMethod.GET)
	public Memory getMemory(@PathVariable Long id) {
		return portableService.getMemory(id);
	}

	@RequestMapping(value="/memories/search",method=RequestMethod.GET)
	public List<Memory> searchMemories(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return portableService.searchMemories(keyWords);
	}

	@RequestMapping(value="/cpus", method = RequestMethod.POST)
	public Cpu saveCpu(@RequestBody Cpu cpu) {
		return portableService.saveCpu(cpu);
	}

	@RequestMapping(value="/cpus/{id}", method = RequestMethod.DELETE)
	public Boolean deleteCpu(@PathVariable Long id) {
		return portableService.deleteCpu(id);
	}

	@RequestMapping(value="/cpus", method = RequestMethod.GET)
	public List<Cpu> getCpus() {
		return portableService.getCpus();
	}

	@RequestMapping(value="/cpus/{id}", method = RequestMethod.GET)
	public Cpu getCpu(@PathVariable Long id) {
		return portableService.getCpu(id);
	}

	@RequestMapping(value="/cpus/search",method=RequestMethod.GET)
	public List<Cpu> searchCpus(@RequestParam(name="mc", defaultValue="") String keyWords) {
		return portableService.searchCpus(keyWords);
	}

	@RequestMapping(value="/colors",method=RequestMethod.POST)
	public AppColor saveAppColor(@RequestBody AppColor appColor) {
		return appColorService.saveAppColor(appColor);
	}
}
