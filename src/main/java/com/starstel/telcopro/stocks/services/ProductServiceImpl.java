package com.starstel.telcopro.stocks.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starstel.telcopro.rh.repositories.EmployeeRepository;
import com.starstel.telcopro.stocks.entities.ProductCategory;
import com.starstel.telcopro.stocks.entities.Emplacement;
import com.starstel.telcopro.stocks.entities.Entrepot;
import com.starstel.telcopro.stocks.entities.MeasureUnit;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.MouvmentLine;
import com.starstel.telcopro.stocks.entities.MouvmentType;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.entities.Recipient;
import com.starstel.telcopro.stocks.entities.RecipientGroupe;
import com.starstel.telcopro.stocks.entities.State;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.repositories.CategoryRepository;
import com.starstel.telcopro.stocks.repositories.EmplacementRepository;
import com.starstel.telcopro.stocks.repositories.EntrepotRepository;
import com.starstel.telcopro.stocks.repositories.MeasureUnitRepository;
import com.starstel.telcopro.stocks.repositories.MouvmentLineRepository;
import com.starstel.telcopro.stocks.repositories.MouvmentRepository;
import com.starstel.telcopro.stocks.repositories.MouvmentTypeRepository;
import com.starstel.telcopro.stocks.repositories.ProductCategoryRepository;
import com.starstel.telcopro.stocks.repositories.ProductRepository;
import com.starstel.telcopro.stocks.repositories.RecipientGroupeRepository;
import com.starstel.telcopro.stocks.repositories.RecipientRepository;
import com.starstel.telcopro.stocks.repositories.StateRepository;
import com.starstel.telcopro.stocks.repositories.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService 
{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private EmplacementRepository emplacementRepository;
	
	@Autowired
	private MeasureUnitRepository measureUnitRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	
	@Override
	public List<Product> listProducts() {
		return productRepository.findAll();
	}
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	@Override
	public Boolean deleteProduct(Long id) {
		productRepository.deleteById(id);
		return true;
	}
	@Override
	public List<State> listStates() {
		return stateRepository.findAll();
	}
	@Override
	public State saveState(State state) {
		return stateRepository.save(state);
	}
	@Override
	public Boolean deleteState(Long id) {
		stateRepository.deleteById(id);
		return true;
	}
	@Override
	public State getState(Long id) {
		return stateRepository.findById(id).get();
	}
	@Override
	public List<MeasureUnit> listMeasureUnits() {
		return measureUnitRepository.findAll();
	}
	@Override
	public MeasureUnit saveMeasureUnit(MeasureUnit measureUnit) {
		return measureUnitRepository.save(measureUnit);
	}
	@Override
	public Boolean deleteMeasureUnit(Long id) {
		measureUnitRepository.deleteById(id);
		return true;
	}
	@Override
	public MeasureUnit getMeasureUnit(Long id) {
		return measureUnitRepository.findById(id).get();
	}

	@Override
	public List<Mouvment> getAllMouvmentOfProduct(Long id) {
		return productRepository.getAllMouvmentOfProduct(id);
	}
	@Override
	public List<Mouvment> getAllMouvmentOfEmployee(Long id) {
		return productRepository.getAllMouvmentOfEmployee(id);
	}
	@Override
	public Product getProduct(Long id) {
		return productRepository.findById(id).get();
	}
	@Override
	public List<Product> searchProducts(String keyWords) {
		return productRepository.searchProducts("%"+keyWords+"%");
	}
	@Override
	public List<ProductCategory> listProductCategorys() {
		return productCategoryRepository.findAll();
	}
	@Override
	public ProductCategory saveProductCategory(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}
	@Override
	public Boolean deleteProductCategory(Long id) {
		productCategoryRepository.deleteById(id);
		return true;
	}
	@Override
	public ProductCategory getProductCategory(Long id) {
		return productCategoryRepository.findById(id).get();
	}
	@Override
	public List<State> searchStates(String keyWords) {
		return stateRepository.search("%"+keyWords+"%");
	}
	@Override
	public List<ProductCategory> searchProductCategories(String keyWords) {
		return productCategoryRepository.search("%"+keyWords+"%");
	}
	@Override
	public List<MeasureUnit> searchMeasureUnits(String keyWords) {
		return measureUnitRepository.search("%"+keyWords+"%");
	}
}
