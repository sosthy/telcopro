package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.AppColor;
import com.starstel.telcopro.stocks.entities.Portable;
import com.starstel.telcopro.stocks.entities.Product;

public interface AppColorRepository extends JpaRepository<AppColor, Long>
{
	@Query("select a.name from AppColor a")
	List<String> getColors();
	@Query("select p from Product p where lower(p.appColor.name) like lower(concat('%',:x,'%'))")
	List<Product> getProductByColor(@Param("x") String string);
	@Query("select p from Portable p where lower(p.appColor.name) like lower(concat('%',:x,'%'))")
	List<Portable> getPortableByColor(@Param("x") String string);
	@Query("select distinct p.appColor.name from Portable p")
	List<String> getPortableColor();
	@Query("select a from AppColor a where lower(a.name) like lower(:x)")
	List<AppColor> search(@Param("x") String keyWords);
	
}
