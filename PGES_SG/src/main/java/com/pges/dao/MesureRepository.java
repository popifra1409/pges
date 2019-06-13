package com.pges.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Mesure;

public interface MesureRepository extends JpaRepository<Mesure, Long> {
	@Query("select distinct m from Mesure m, Activite_Source av where  m.impact.idImpact=:x and m.impact.idImpact=av.impact.idImpact")
	public Page<Mesure> listMesuresActSources(@Param("x") Long idImpact, Pageable pageable);

	@Query("select m from Mesure m where m.impact.idImpact=:x")
	public List<Mesure> listMesuresImpact(@Param("x") Long idImpact);
}
