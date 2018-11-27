package com.starstel.telcopro.stocks.services;


import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.starstel.telcopro.stocks.entities.PortableItem;

//import com.starstel.telcopro.stock.entities.Product;

public interface PortableItemService 
{
	PortableItem save(PortableItem portableItem);
	Boolean delete(Long id);
	List<PortableItem> getPortableItems();
	Set<PortableItem> getPortableItems(Long id);
	PortableItem getPortableItem(Long id);
	List<PortableItem> searchItems(String mc);
}
