package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.MouvmentType;

public interface MouvmentTypeRepository extends JpaRepository<MouvmentType, Long>
{
	@Query("select m from MouvmentType m where lower(m.name) like lower(:x) or lower(m.description) like lower(:x)")
	List<MouvmentType> search(@Param("x") String keyWords);
}
