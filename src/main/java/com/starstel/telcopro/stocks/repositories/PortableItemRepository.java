package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.stocks.entities.PortableItem;

public interface PortableItemRepository extends JpaRepository<PortableItem, Long> {

	@Query("select p from PortableItem p  where (lower(p.codeQrc) like lower(:x) or lower(p.codeBar) like lower(:x) or "
			+ "lower(p.reference) like lower(:x) or lower(p.serial) like lower(:x)) and p.portable.id = :id")
	List<PortableItem> searchItems(@Param("x")String mc, @Param("id") Long idPortable);

}
