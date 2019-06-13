package com.pges.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Activite_Source;

public interface Activite_SourceRepository extends JpaRepository<Activite_Source, Long> {
	@Query("select av from Activite_Source av where av.libActSource  like:x")
	public Page<Activite_Source> chercherActSource(@Param("x") String mc, Pageable pageable);

	@Query("select av from Activite_Source av where av.impact.idImpact=:x order by av.libActSource  asc")
	public Page<Activite_Source> listActSources(@Param("x") Long idImpact, Pageable pageable);

	@Query("select av from Activite_Source av where av.impact.idImpact=:x")
	public Page<Activite_Source> chercherActSourceImpact(@Param("x") Long idImpact, Pageable pageable);
}
