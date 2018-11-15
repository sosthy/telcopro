package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.SystemOS;

public interface SystemOSRepository extends JpaRepository<SystemOS, Long>
{
	@Query("select distinct s from SystemOS s inner join s.portableList p where lower(s.name) like lower(:x) or "
			+ "lower(s.version) like lower(:x) or lower(p.designation) like lower(:x)")
	List<SystemOS> search(@Param("x") String keyWords);
}
