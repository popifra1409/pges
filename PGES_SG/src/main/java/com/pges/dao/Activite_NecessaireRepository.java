package com.pges.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Activite_Necessaire;

public interface Activite_NecessaireRepository extends JpaRepository<Activite_Necessaire, Long> {
	@Query("select an from Activite_Necessaire an where an.mesure.idMesure=:x order by an.libActNec  asc")
	public Page<Activite_Necessaire> listActNec(@Param("x") Long idMesure, Pageable pageable);

	@Query("select an from Activite_Necessaire an where an.mesure.idMesure=:x order by an.libActNec  asc")
	public List<Activite_Necessaire> listActNecMesure(@Param("x") Long idMesure);
}
