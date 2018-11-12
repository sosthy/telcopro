package com.starstel.telcopro.rh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starstel.telcopro.rh.entities.WorkSpace;
import com.starstel.telcopro.rh.repositories.WorkSpaceRepository;
import com.starstel.telcopro.stocks.entities.Mouvment;

@Service
public class WorkSpaceServiceImpl implements WorkSpaceService
{
	@Autowired
	private WorkSpaceRepository workSpaceRepository;
	
	@Override
	public List<WorkSpace> listWorkSpace() 
	{
		return workSpaceRepository.findAll();
	}

	@Override
	public WorkSpace saveWorkSpace(WorkSpace workSpace) 
	{
		return workSpaceRepository.save(workSpace);
	}

	@Override
	public Boolean deleteWorkSpace(Long id) 
	{
		workSpaceRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Mouvment> listMouvmentOfWorkSpace(Long id) 
	{	
		return workSpaceRepository.getMouvments();
	}

	@Override
	public WorkSpace getWorkSpace(Long id) {
		return workSpaceRepository.findById(id).get();
	}

	@Override
	public List<WorkSpace> saveWorkSpace(List<WorkSpace> workSpaces) {
		return workSpaceRepository.saveAll(workSpaces);
	}

	@Override
	public List<WorkSpace> searchWorkSpace(String keyWords) {
		return workSpaceRepository.search("%"+keyWords+"%");
	}

}
