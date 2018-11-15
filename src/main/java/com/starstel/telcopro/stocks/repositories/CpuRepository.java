package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.Cpu;

public interface CpuRepository extends JpaRepository<Cpu, Long>
{
	@Query("select distinct c from Cpu c inner join c.portableList p where lower(c.brand) like lower(:x) or  concat(c.frequency,'') like lower(:x) or "
			+ "lower(p.designation) like lower(:x)")
	List<Cpu> search(@Param("x") String keyWords);
}
