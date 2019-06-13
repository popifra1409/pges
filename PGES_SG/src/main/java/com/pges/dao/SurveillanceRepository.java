package com.pges.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Surveillance;

public interface SurveillanceRepository extends JpaRepository<Surveillance, Long> {
	@Query("select su from Surveillance su where su.phase.idPhase=:x")
	public Page<Surveillance> listSurveillances(@Param("x") Long idPhase, Pageable pageable);

	@Query("select distinct su from Surveillance su, Impact i, Mesure m, Activite_Necessaire an, Tache t where su.phase.idPhase=:x and su.phase.idPhase=i.phase.idPhase and i.idImpact=m.impact.idImpact and an.mesure.idMesure=m.idMesure and an.idActNec=t.activite.idActNec")
	public Page<Surveillance> listTacheSurveillances(@Param("x") Long idPhase, Pageable pageable);

	@Query("select su from Surveillance su where su.tache.idTache=:x")
	public List<Surveillance> listSurveillanceTache(@Param("x") Long idTache);
}
