package com.starstel.telcopro.stocks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.starstel.telcopro.stocks.entities.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long>
{

	@Query("select c from Commande c where lower(c.reference) like lower(:x) or lower(c.reference) like lower(:x) or "
			+ "lower(c.workSpaceReceiver.name) like lower(:x) or lower(c.workSpaceReceiver.location) like lower(:x) or "
			+ "lower(c.user.name) like lower(:x) or lower(c.user.surname) like lower(:x) or "
			+ "lower(c.receiver.name) like lower(:x) or lower(c.receiver.workSpace.name) like lower(:x) or "
			+ "lower(c.recipient.designation) like lower(:x) or lower(c.recipient.location) like lower(:x) or "
			+ "lower(c.mouvmentType.name) like lower(:x) or lower(concat(c.date,'')) like lower(:x) or "
			+ "lower(concat(c.priceTotal,'')) like lower(:x) or lower(concat(c.quantity,'')) like lower(:x)")
	List<Commande> search(@Param("x") String keyWords);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Commande c WHERE c.reference= :ref")
	void deleteByReference(@Param("ref") String reference);
	
	@Query("SELECT c FROM Commande c WHERE  lower(c.reference) = lower(:ref)")
	Object getByReference(@Param("ref") String reference);

	@Query("SELECT c FROM Commande c WHERE c.user.id = :id")
	List<Commande> getAllCommandeDealByEmployee(@Param("id") Long id);
	
	@Query("SELECT c FROM Commande c WHERE c.receiver.id = :id")
	List<Commande> getAllCommandeEmitByEmployee(@Param("id") Long id);
	
	@Query("SELECT DISTINCT c FROM Commande c WHERE c.workSpaceReceiver.id = :id or c.receiver.workSpace.id = :id")
	List<Commande> getAllCommandeReceiveByWorkSpace(@Param("id") Long id);
	
	@Query("SELECT c FROM Commande c WHERE c.mouvmentType.id = :id")
	List<Commande> getAllCommandeOfMouvmentType(@Param("id") Long id);
	
}
