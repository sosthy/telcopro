package com.starstel.telcopro.stocks.services;

import java.util.List;
import java.util.Set;

import com.starstel.telcopro.stocks.entities.MeasureUnit;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.entities.ProductCategory;
import com.starstel.telcopro.stocks.entities.State;
import com.starstel.telcopro.stocks.entities.Product;

public interface ProductService 
{
	// Product
	List<Product> listProducts();
	Product saveProduct(Product product);
	Boolean deleteProduct(Long id);
	Product getProduct(Long id);
	List<Product> searchProducts(String keyWords);
	
	// State
	List<State> listStates();
	State saveState(State state);
	Boolean deleteState(Long id);
	State getState(Long id);
	List<State> searchStates(String keyWords);
	
	// ProductCategory
	List<ProductCategory> listProductCategorys();
	ProductCategory saveProductCategory(ProductCategory productCategory);
	Boolean deleteProductCategory(Long id);
	ProductCategory getProductCategory(Long id);
	List<ProductCategory> searchProductCategories(String keyWords);
	
	// MeasureUnit
	List<MeasureUnit> listMeasureUnits();
	MeasureUnit saveMeasureUnit(MeasureUnit measureUnit);
	Boolean deleteMeasureUnit(Long id);
	MeasureUnit getMeasureUnit(Long id);
	List<MeasureUnit> searchMeasureUnits(String keyWords);

	List<Mouvment> getAllMouvmentOfProduct(Long id);
	List<Mouvment> getAllMouvmentOfEmployee(Long id);
	
}
