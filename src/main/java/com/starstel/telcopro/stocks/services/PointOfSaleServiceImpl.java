package com.starstel.telcopro.stocks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starstel.telcopro.stocks.entities.PointOfSale;
import com.starstel.telcopro.stocks.repositories.PointOfSaleRepository;

@Service
public class PointOfSaleServiceImpl implements PointOfSaleService {

	@Autowired
	private PointOfSaleRepository pointOfSaleRepository;
	
	@Override
	public PointOfSale save(PointOfSale pointOfSale) {
		return pointOfSaleRepository.save(pointOfSale);
	}

	@Override
	public Boolean delete(Long id) {
		pointOfSaleRepository.deleteById(id);
		return true;
	}
	

	@Override
	public PointOfSale getPointOfSale(Long id) {
		return pointOfSaleRepository.findById(id).get();
	}

	@Override
	public List<PointOfSale> getPointOfSales() {
		return pointOfSaleRepository.findAll();
	}

	@Override
	public List<PointOfSale> search(String mc) {
		return pointOfSaleRepository.search("%"+mc+"%");
	}

}
