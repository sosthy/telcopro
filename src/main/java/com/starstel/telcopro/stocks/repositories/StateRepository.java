package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.State;

public interface StateRepository extends JpaRepository<State, Long>
{
	@Query("select distinct s from State s inner join s.productList p where lower(s.name) like lower(:x) or "
			+ "lower(p.designation) like lower(:x)")
	List<State> search(@Param("x") String keyWords);
}
