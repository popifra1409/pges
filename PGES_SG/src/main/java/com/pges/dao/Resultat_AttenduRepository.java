package com.pges.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Resultat_Attendu;

public interface Resultat_AttenduRepository extends JpaRepository<Resultat_Attendu, Long> {
	@Query("select ra from Resultat_Attendu ra where ra.mesure.idMesure=:x")
	public Page<Resultat_Attendu> listeResultatsMesure(@Param("x") Long idMesure, Pageable pageable);

	@Query("select ra from Resultat_Attendu ra where ra.phase.idPhase=:x")
	public Page<Resultat_Attendu> listeResultatsPhase(@Param("x") Long idPhase, Pageable pageable);

	@Query("select ra from Resultat_Attendu ra where ra.mesure.idMesure=:x")
	public List<Resultat_Attendu> listResultatsMesure(@Param("x") Long idMesure);
}
