package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.Emplacement;

public interface EmplacementRepository extends JpaRepository<Emplacement, Long>
{
	@Query("select distinct e from Emplacement e where lower(e.name) like lower(:x) or "
			+ "lower(e.entrepot.name) like lower(:x)")
	List<Emplacement> search(@Param("x") String keyWords);
}
