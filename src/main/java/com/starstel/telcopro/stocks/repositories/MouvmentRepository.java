package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.Mouvment;

public interface MouvmentRepository extends JpaRepository<Mouvment, Long>
{

	@Query("select m from Mouvment m where lower(m.reference) like lower(:x) or lower(m.reference) like lower(:x) or "
			+ "lower(m.workSpaceSource.name) like lower(:x) or lower(m.workSpaceRecipient.name) like lower(:x) or "
			+ "lower(m.user.name) like lower(:x) or lower(m.user.surname) like lower(:x) or "
			+ "lower(m.recipient.designation) like lower(:x) or lower(m.recipient.location) like lower(:x) or "
			+ "lower(m.mouvmentType.name) like lower(:x) or lower(concat(m.date,'')) like lower(:x) or "
			+ "lower(concat(m.priceTotal,'')) like lower(:x) or lower(concat(m.quantity,'')) like lower(:x)")
	List<Mouvment> search(@Param("x") String keyWords);
}
