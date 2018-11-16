package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, Long>
{
	@Query("select r from Recipient r where lower(r.designation) like lower(:x) or "
			+ "lower(r.location) like lower(:x) or lower(r.website) like lower(:x) or lower(r.phone) like lower(:x) or "
			+ "lower(r.groupe.name) like lower(:x)")
	List<Recipient> search(@Param("x") String keyWords);
}
