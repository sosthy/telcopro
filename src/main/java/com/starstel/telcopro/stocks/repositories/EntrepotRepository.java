package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.Entrepot;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.Product;

public interface EntrepotRepository extends JpaRepository<Entrepot, Long>
{
	@Query("SELECT product FROM Product product WHERE product.emplacement.entrepot.id = :id")
	public List<Product> getAllStockOfEntrepot(@Param("id") Long id);
	
	@Query("SELECT mouvment FROM Mouvment mouvment WHERE mouvment.workSpaceSource.id = :id")
	public List<Mouvment> getAllMouvmentOfEntrepot(@Param("id") Long id);
	
	@Query("SELECT COUNT(item) FROM PortableItem item WHERE item.portable.emplacement.entrepot.id = :id")
	public Long getPortableItemCountOfEntrepot(@Param("id") Long id);
	
	@Query("SELECT COUNT(item) FROM PortableItem item WHERE item.portable.emplacement.id = :id")
	public Long getPortableItemCountOfEmplacement(@Param("id") Long id);
	@Query("select distinct e from Entrepot e where lower(e.name) like lower(:x) or lower(e.localisation) like lower(:x) or "
			+ "(select count(em) from Emplacement em where lower(em.name) like lower(:x) and em.entrepot.id = e.id) > 0")
	List<Entrepot> search(@Param("x") String keyWords);
}
