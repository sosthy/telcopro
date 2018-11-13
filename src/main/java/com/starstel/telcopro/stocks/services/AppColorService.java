package com.starstel.telcopro.stocks.services;

import java.util.List;

import com.starstel.telcopro.stocks.entities.AppColor;
import com.starstel.telcopro.stocks.entities.Portable;
import com.starstel.telcopro.stocks.entities.Product;

public interface AppColorService 
{
	AppColor saveAppColor(AppColor appColor);
	Boolean deleteAppColor(Long id);
	List<AppColor> getAppColors();
	AppColor getAppColor(Long id);
	List<String> getColors();
	List<String> getPortableColor();
	List<Product> getProductByColor(String name);
	List<Portable> getPortableByColor(String name);
	List<AppColor> search(String keyWords);
}
