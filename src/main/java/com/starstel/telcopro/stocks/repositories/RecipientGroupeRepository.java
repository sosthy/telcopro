package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.RecipientGroupe;

public interface RecipientGroupeRepository extends JpaRepository<RecipientGroupe, Long>
{
	@Query("select distinct r from RecipientGroupe r inner join r.recipients rr where lower(r.name) like lower(:x) or "
			+ "lower(rr.designation) like lower(:x) or lower(rr.website) like lower(:x)")
	List<RecipientGroupe> search(@Param("x") String keyWords);
}
