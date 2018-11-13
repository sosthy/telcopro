package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, Long>
{
	@Query("select r from Recipient r inner join r.mouvments m where lower(r.name) like lower(:x) or "
			+ "lower(r.surname) like lower(:x) or lower(r.enterprise) like lower(:x) or lower(r.fonction) like lower(:x) or "
			+ "lower(r.service) like lower(:x) or lower(m.reference) like lower(:x)")
	List<Recipient> search(@Param("x") String keyWords);
}
