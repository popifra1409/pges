package com.pges.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Suivi;

public interface SuiviRepository extends JpaRepository<Suivi, Long> {
	@Query("select sui from Suivi sui where sui.phase.idPhase=:x")
	public Page<Suivi> listSuivis(@Param("x") Long idPhase, Pageable pageable);

	@Query("select distinct sui from Suivi sui, Impact i, Mesure m, Resultat_Attendu r where sui.phase.idPhase=:x and sui.phase.idPhase=i.phase.idPhase and i.idImpact=m.impact.idImpact and m.idMesure=r.mesure.idMesure")
	public Page<Suivi> listResultatSuivis(@Param("x") Long idPhase, Pageable pageable);

	@Query("select sui from Suivi sui where sui.resultat.idResultat=:x")
	public List<Suivi> listSuiviResultat(@Param("x") Long idResultat);
}
