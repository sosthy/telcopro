package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long>
{

	@Query("select distinct p from ProductCategory p inner join p.products pp where lower(p.name) like lower(:x) or "
			+ "lower(p.notes) like lower(:x) or lower(pp.designation) like lower(:x)")
	List<ProductCategory> search(@Param("x") String keyWords);
}
