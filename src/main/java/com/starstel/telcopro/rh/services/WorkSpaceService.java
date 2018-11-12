package com.starstel.telcopro.rh.services;

import java.util.List;
import java.util.Set;

import com.starstel.telcopro.rh.entities.WorkSpace;
import com.starstel.telcopro.stocks.entities.Mouvment;

public interface WorkSpaceService 
{
	List<WorkSpace> listWorkSpace();
	WorkSpace getWorkSpace(Long id);
	WorkSpace saveWorkSpace(WorkSpace workSpace);
	List<WorkSpace> saveWorkSpace(List<WorkSpace> workSpaces);
	Boolean deleteWorkSpace(Long id);
	List<Mouvment> listMouvmentOfWorkSpace(Long id);
	List<WorkSpace> searchWorkSpace(String keyWords);
}
