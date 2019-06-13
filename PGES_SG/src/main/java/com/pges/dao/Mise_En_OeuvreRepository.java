package com.pges.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Mise_En_Oeuvre;

public interface Mise_En_OeuvreRepository extends JpaRepository<Mise_En_Oeuvre, Long> {
	@Query("select meo from Mise_En_Oeuvre meo where meo.phase.idPhase=:x")
	public Page<Mise_En_Oeuvre> listMeos(@Param("x") Long idPhase, Pageable pageable);

	@Query("select distinct meo from Mise_En_Oeuvre meo, Impact i, Mesure m, Activite_Necessaire an,Tache t where meo.phase.idPhase=:x and meo.phase.idPhase=i.phase.idPhase and i.idImpact=m.impact.idImpact and an.mesure.idMesure=m.idMesure and an.idActNec=t.activite.idActNec")
	public Page<Mise_En_Oeuvre> listTacheMeos(@Param("x") Long idPhase, Pageable pageable);

	@Query("select meo from Mise_En_Oeuvre meo where meo.tache.idTache=:x")
	public List<Mise_En_Oeuvre> listMeoTaches(@Param("x") Long idTache);
}
