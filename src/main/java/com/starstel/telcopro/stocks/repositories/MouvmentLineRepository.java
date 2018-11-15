package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.MouvmentLine;

public interface MouvmentLineRepository extends JpaRepository<MouvmentLine, Long>
{
	@Query("SELECT mouvmentLine FROM MouvmentLine mouvmentLine WHERE mouvmentLine.mouvment.mouvmentType.id = :id")
	public List<MouvmentLine> getAllMouvmentLineOfMouvmentType(@Param("id") Long id);
	@Query("SELECT mouvmentLine FROM MouvmentLine mouvmentLine WHERE mouvmentLine.mouvment.recipient.id = :id")
	public List<MouvmentLine> getAllMouvmentLineOfRecipient(@Param("id") Long id);
	@Query("select distinct m from MouvmentLine m where lower(m.note) like lower(:x) or lower(m.mouvment.reference) like lower(:x) or "
			+ "lower(m.product.designation) like lower(:x) or concat(m.quantity,'') like lower(:x) or "
			+ " concat(m.priceTotal,'') like lower(:x) or concat(m.priceUnit,'') like lower(:x)")
	List<MouvmentLine> search(@Param("x") String keyWords);
	
}
