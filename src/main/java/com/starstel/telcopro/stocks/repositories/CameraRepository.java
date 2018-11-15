package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.Camera;

public interface CameraRepository extends JpaRepository<Camera, Long>
{
	@Query("select distinct c from Camera c inner join c.portableList p where concat(c.frontCamera,'') like lower(:x) or "
			+ "concat(c.backCamera,'') like lower(:x) or lower(p.designation) like lower(:x)")
	List<Camera> search(@Param("x") String keyWords);
}
