package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.Emplacement;
import com.starstel.telcopro.stocks.entities.Mouvment;
import com.starstel.telcopro.stocks.entities.MouvmentLine;
import com.starstel.telcopro.stocks.entities.Portable;

public interface PortableRepository extends JpaRepository<Portable, Long> {
	@Query("SELECT mouvment FROM Mouvment mouvment WHERE mouvment.workSpaceSource.id = :id")
	public List<Mouvment> getMouvments(@Param("id") Long id);
	
	@Query("SELECT portable.emplacement  FROM Portable portable WHERE portable.id = :id")
	public List<Emplacement> getEmplacement(@Param("id") Long id);

	@Query("select ml.mouvment from MouvmentLine ml where ml.product.id = :id")
	public List<Mouvment> getAllMouvment(@Param("id") Long id);

	@Query("select p.portable from PortableItem p where lower(p.codeQrc) like lower(:x) or "
			+ "lower(p.codeBar) like lower(:x) or lower(p.serial) like lower(:x) or "
			+ "lower(p.reference) like lower(:x)")
	public Portable getPortable(@Param("x") String numeroSerie);
	@Query("select p from Portable p")
	/*@Query("select p from Portable p where lower(p.appColor.name) like lower(:x) or "
			+ "lower(p.designation) like lower(:x) or lower(p.portableCategory.name) like lower(:x) or "
			+ "lower(p.battery) like lower(:x) or lower(p.ipRating) like lower(:x)")*/
	public List<Portable> searchPortable(Portable portable);

	@Query("select p from Portable p where lower(p.appColor.name) like lower(:x) or "
			+ "lower(p.designation) like lower(:x) or lower(p.connection) like lower(:x) or "
			+ "lower(p.battery) like lower(:x) or lower(p.ipRating) like lower(:x) or lower(concat(p.quantity,'')) like lower(:x) or " 
			+ "lower(p.emplacement.name) like lower(:x) or lower(p.emplacement.entrepot.name) like lower(:x)")
	public List<Portable> searchPortable(@Param("x") String motCle);
	
}
