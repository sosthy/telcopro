package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.starstel.telcopro.stocks.entities.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>
{
	
}