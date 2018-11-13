package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.PortableUnit;

public interface PortableUnitRepository extends JpaRepository<PortableUnit, Long>
{
	@Query("select p from PortableUnit p where lower(p.name) like lower(:x)")
	List<PortableUnit> search(@Param("x") String keyWords);
}
