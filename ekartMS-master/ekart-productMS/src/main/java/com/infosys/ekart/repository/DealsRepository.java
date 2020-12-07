package com.infosys.ekart.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.infosys.ekart.entity.Deals;

@Repository
public interface DealsRepository  extends JpaRepository<Deals, Integer> {
	
	@Query(value ="select t from Deals t where t.fromdate<=?1 and t.todate>=?1")
	public List<Deals> findDealsByDate(LocalDate date);
	
}
