package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.PointOfSale;

public interface PointOfSaleRepository extends JpaRepository<PointOfSale, Long>
{
	@Query("select p from PointOfSale p where lower(p.name) like lower(:x) or lower(p.localisation) like lower(:x)")
	List<PointOfSale> search(@Param("x") String keyWords);
}
