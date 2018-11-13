package com.starstel.telcopro.stocks.services;


import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.starstel.telcopro.stocks.entities.PointOfSale;

//import com.starstel.telcopro.stock.entities.Product;

public interface PointOfSaleService 
{
	PointOfSale save(PointOfSale pointOfSale);
	Boolean delete(Long id);
	List<PointOfSale> getPointOfSales();
	PointOfSale getPointOfSale(Long id);
	List<PointOfSale> search(String mc);
}
