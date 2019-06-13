package com.pges.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Personne;
import com.pges.entities.Site;

public interface SiteRepository extends JpaRepository<Site, Long>{
	@Query("select s from Site s order by s.nomSite asc")
	public Page<Site> listSites(Pageable pageable);
	@Query("select s from Site s where s.nomSite  like:x")
	public Page<Site> chercherSites(@Param("x") String mc, Pageable pageable);
}
