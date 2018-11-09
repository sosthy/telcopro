package com.starstel.telcopro.rh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.starstel.telcopro.rh.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> 
{
	@Query("select e from Employee e where lower(e.name) like lower(:x) or lower(e.surname) like lower(:x) or "
			+ "lower(e.photo) like lower(:x) or lower(e.website) like lower(:x) or lower(e.cni) like lower(:x) or "
			+ "lower(e.sex) like lower(:x) or concat(e.hiringDate,'') like lower(:x)")
	public List<Employee> search(@Param("x") String keyWords);
}
