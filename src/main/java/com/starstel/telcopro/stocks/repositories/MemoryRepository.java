package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.Memory;

public interface MemoryRepository extends JpaRepository<Memory, Long>
{
	@Query("select distinct m from Memory m inner join m.portableList p where lower(m.brand) like lower(:x) or "
			+ "lower(p.designation) like lower(:x) or  concat(m.rom,'') like lower(:x) or "
			+ "concat(m.ram,'') like lower(:x)")
	List<Memory> search(@Param("x") String keyWords);
}
