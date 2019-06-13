package com.pges.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {
	@Query("select e from Entreprise e where e.personne.idPersonne=:x order by e.nomEntr asc")
	public Page<Entreprise> listEntreprises(@Param("x") Long idPromoteur, Pageable pageable);

	@Query("select e from Entreprise e where e.nomEntr  like:x")
	public Page<Entreprise> chercheEntreprises(@Param("x") String mc, Pageable pageable);
}
