package com.starstel.telcopro.stocks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starstel.telcopro.stocks.entities.AppColor;
import com.starstel.telcopro.stocks.entities.Portable;
import com.starstel.telcopro.stocks.entities.Product;
import com.starstel.telcopro.stocks.repositories.AppColorRepository;

@Service
public class AppColorServiceImpl implements AppColorService
{
	@Autowired
	private AppColorRepository appColorRepository;

	@Override
	public AppColor saveAppColor(AppColor appColor) {
		return appColorRepository.save(appColor);
	}

	@Override
	public Boolean deleteAppColor(Long id) {
		appColorRepository.deleteById(id);
		return true;
	}

	@Override
	public List<AppColor> getAppColors() {
		return appColorRepository.findAll();
	}

	@Override
	public AppColor getAppColor(Long id) {
		return appColorRepository.findById(id).get();
	}

	@Override
	public List<String> getColors() {
		return appColorRepository.getColors();
	}

	@Override
	public List<Product> getProductByColor(String name) {
		return appColorRepository.getProductByColor("%"+name+"%");
	}

	@Override
	public List<Portable> getPortableByColor(String name) {
		return appColorRepository.getPortableByColor("%"+name+"%");
	}

	@Override
	public List<String> getPortableColor() {
		return appColorRepository.getPortableColor();
	}

	@Override
	public List<AppColor> search(String keyWords) {
		return appColorRepository.search("%"+keyWords+"%");
	}
	
}
