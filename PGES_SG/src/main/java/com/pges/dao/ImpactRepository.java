package com.pges.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Impact;

public interface ImpactRepository extends JpaRepository<Impact, Long> {
	@Query("select i from Impact i where i.libImpact  like:x")
	public Page<Impact> chercherImpacts(@Param("x") String mc, Pageable pageable);

	@Query("select i from Impact i where i.phase.idPhase=:x order by i.libImpact asc")
	public Page<Impact> listImpacts(@Param("x") Long idPhase, Pageable pageable);

}
