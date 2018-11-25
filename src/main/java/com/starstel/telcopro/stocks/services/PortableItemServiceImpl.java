package com.starstel.telcopro.stocks.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.starstel.telcopro.stocks.entities.PortableItem;
import com.starstel.telcopro.stocks.repositories.PortableItemRepository;
import com.starstel.telcopro.stocks.repositories.PortableRepository;

@Service
public class PortableItemServiceImpl implements PortableItemService {

	@Autowired
	PortableItemRepository portableItemRepository;
	
	@Autowired
	PortableRepository portableRepository;
	
	@Override
	public PortableItem save(PortableItem portableItem) {
		return portableItemRepository.save(portableItem);
	}

	@Override
	public Boolean delete(Long id) {
		portableItemRepository.deleteById(id);
		return true;
	}

	

	@Override
	public PortableItem getPortableItem(Long id) {
		return portableItemRepository.findById(id).get();
	}

	@Override
	public List<PortableItem> getPortableItems() {
		return portableItemRepository.findAll();
	}

	@Override
	public List<PortableItem> searchItems(String mc) {
		System.err.println("mc="+mc);
		return portableItemRepository.searchItems("%"+mc+"%");
	}

	@Override
	public Set<PortableItem> getPortableItems(Long id) {
		return portableRepository.findById(id).get().getPortableItem();
	}

}
