package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.PortableCategory;

public interface PortableCategoryRepository extends JpaRepository<PortableCategory, Long>
{
	@Query("select distinct p from PortableCategory p inner join p.portables pp where lower(p.name) like lower(:x) or "
			+ "lower(p.notes) like lower(:x) or lower(pp.designation) like lower(:x)")
	List<PortableCategory> search(@Param("x") String keyWords);
}
