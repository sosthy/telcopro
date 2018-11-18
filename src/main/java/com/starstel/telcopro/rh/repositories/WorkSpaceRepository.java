package com.starstel.telcopro.rh.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.rh.entities.WorkSpace;
import com.starstel.telcopro.stocks.entities.Mouvment;

public interface WorkSpaceRepository extends JpaRepository<WorkSpace, Long> 
{
	@Query("select w from WorkSpace w where lower(w.name) like lower(:x) or lower(w.localisation) like lower(:x) or "
			+ "lower(w.workSpaceType) like lower(:x)")
	public List<WorkSpace> search(@Param("x") String keyWords);
	@Query("select m from Mouvment m")
	public List<Mouvment> getMouvments();
}
