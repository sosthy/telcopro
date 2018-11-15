package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.MeasureUnit;

public interface MeasureUnitRepository extends JpaRepository<MeasureUnit, Long>
{
	@Query("select distinct m from MeasureUnit m inner join m.productList p where lower(m.unity) like lower(:x) or "
			+ "lower(p.designation) like lower(:x)")
	List<MeasureUnit> search(@Param("x") String keyWords);
}
