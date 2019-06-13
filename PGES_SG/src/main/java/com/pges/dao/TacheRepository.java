package com.pges.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Tache;

public interface TacheRepository extends JpaRepository<Tache, Long> {
	@Query("select t from Tache t where t.activite.idActNec=:x order by t.libTache  asc")
	public Page<Tache> listTaches(@Param("x") Long idActNec, Pageable pageable);

	@Query("select t from Tache t where t.activite.idActNec=:x")
	public List<Tache> listTacheActNec(@Param("x") Long idActNec);

	@Query("select t from Tache t where t.phase.idPhase=:x")
	public Page<Tache> listTachePhase(@Param("x") Long idPhase, Pageable pageable);
}
