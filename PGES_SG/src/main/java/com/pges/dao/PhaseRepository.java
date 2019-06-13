package com.pges.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Personne;
import com.pges.entities.Phase;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
	@Query("select ph from Phase ph where ph.projet.idProjet=:x order by ph.dateDebut")
	public Page<Phase> listPhases(@Param("x") Long idProjet, Pageable pageable);

	@Query("select ph from Phase ph where ph.libellePhase  like:x")
	public Page<Phase> chercherPhases(@Param("x") String mc, Pageable pageable);
}
