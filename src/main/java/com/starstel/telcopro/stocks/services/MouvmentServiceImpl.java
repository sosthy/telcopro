package com.starstel.telcopro.stocks.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.rh.repositories.EmployeeRepository;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.MouvmentLine;
import com.starstel.telcopro.stocks.entities.MouvmentType;
import com.starstel.telcopro.stocks.entities.Portable;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.repositories.MouvmentLineRepository;
import com.starstel.telcopro.stocks.repositories.MouvmentRepository;
import com.starstel.telcopro.stocks.repositories.MouvmentTypeRepository;
import com.starstel.telcopro.stocks.repositories.PortableRepository;

@Service
@Transactional
public class MouvmentServiceImpl implements MouvmentService
{
	@Autowired
	private MouvmentTypeRepository mouvmentTypeRepository;
	@Autowired
	private MouvmentRepository mouvmentRepository;
	@Autowired
	private MouvmentLineRepository mouvmentLineRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PortableRepository portableRepository;
	
	
	@Override
	public List<MouvmentType> listMouvmentType() 
	{
		return mouvmentTypeRepository.findAll();
	}
	
	@Override
	public MouvmentType saveMouvmentType(MouvmentType mouvmentType) 
	{
		return mouvmentTypeRepository.save(mouvmentType);
	}
	
	@Override
	public MouvmentType getMouvmentType(Long id) 
	{
		return mouvmentTypeRepository.findById(id).get();
	}
	
	@Override
	public Boolean deleteMouvmentType(Long id) 
	{
		mouvmentTypeRepository.deleteById(id);
		return true;
	}
	
	@Override
	public Set<Mouvment> getAllMouvmentOfMouvmentType(Long id)
	{
		MouvmentType mouvmentType = mouvmentTypeRepository.findById(id).get();
		
		if(mouvmentType != null)
			return mouvmentType.getMouvments();
		else
			return null;
	}
	
	@Override
	public Set<Mouvment> getAllMouvmentOfEmployee(Long id) 
	{
		Employee employee = employeeRepository.findById(id).get();
		
		if(employee != null)
			return employee.getMouvments();
		else
			return null;
	}
	
	@Override
	public List<Mouvment> listMouvment() 
	{
		return mouvmentRepository.findAll();
	}
	
	@Override
	public Mouvment saveMouvment(Mouvment mouvment) 
	{
		if(mouvment.getMouvmentType() != null)
			if(mouvment.getMouvmentType().getName().equals("APPROVISIONNEMENT")) {
				if(mouvment.getMouvmentLines() != null)
					mouvment.getMouvmentLines().forEach(mLine -> {
						mLine.setMouvment(mouvment);
						if(mLine.getProductsItem() != null) {
							mLine.getProductsItem().forEach(item -> {
								System.out.println(mLine.getProduct().getClass().getName());
								Portable portable = portableRepository.findById(mLine.getProduct().getId()).get();
								item.setPortable(portable);
								item.setIsAvailable(true);
								portable.getPortableItem().add(item);
							});
						}
				});
			}
			else if(mouvment.getMouvmentType().getName().equals("LIVRAISON")) {
				if(mouvment.getMouvmentLines() != null)
					mouvment.getMouvmentLines().forEach(mLine -> {
						mLine.setMouvment(mouvment);
						if(mLine.getProductsItem() != null) {
							mLine.getProductsItem().forEach(item -> {
								item.setIsAvailable(false);
							});
						}
				});
			}
		return mouvmentRepository.save(mouvment);
	}
	
	@Override
	public Boolean deleteMouvment(String reference) 
	{
		Mouvment m = mouvmentRepository.getByReference(reference);
		mouvmentRepository.delete(m);
		return true;
	}

	@Override
	public Set<Product> getProducts(Mouvment mouvment) {
		
		Set<Product> products = new HashSet<>();
		for(MouvmentLine ml: mouvment.getMouvmentLines())
			products.add(ml.getProduct());
		
		return products;
	}

	@Override
	public List<MouvmentLine> listMouvmentLine(Long id) {
		return mouvmentRepository.findById(id).get().getMouvmentLines();
	}
	
	@Override
	public MouvmentLine getMouvmentLine (Long id) {
		return mouvmentLineRepository.findById(id).get();
	}
	
	@Override
	public Set<MouvmentLine> listMouvmentLine() {
		return new HashSet<>(mouvmentLineRepository.findAll());
	}

	@Override
	public MouvmentLine saveMouvmentLine(MouvmentLine mouvmentLine) {
		/*
		mouvmentLine.getMouvment().setQuantity(mouvmentLine.getMouvment().getQuantity() + mouvmentLine.getQuantity());
		mouvmentLine.getMouvment().setPriceTotal(mouvmentLine.getMouvment().getPriceTotal() + mouvmentLine.getPriceTotal());
		mouvmentRepository.save(mouvmentLine.getMouvment());*/
		
		return mouvmentLineRepository.save(mouvmentLine);
	}

	@Override
	public Boolean deleteMouvmentLine(Long id) {
		mouvmentLineRepository.deleteById(id);
		return true;
	}

	@Override
	public Set<MouvmentLine> getAllMouvmentLineOfMouvmentType(Long id) {
		return new HashSet<>(mouvmentLineRepository.getAllMouvmentLineOfMouvmentType(id));
	}

	@Override
	public Mouvment getMouvment(String reference) {
		return mouvmentRepository.getByReference(reference);
	}

	@Override
	public List<MouvmentType> searchType(String keyWords) {
		return mouvmentTypeRepository.search("%"+keyWords+"%");
	}

	@Override
	public List<Mouvment> search(String keyWords) {
		return mouvmentRepository.search("%"+keyWords+"%");
	}

	@Override
	public List<MouvmentLine> searchLine(String keyWords) {
		return mouvmentLineRepository.search("%"+keyWords+"%");
	}
}
