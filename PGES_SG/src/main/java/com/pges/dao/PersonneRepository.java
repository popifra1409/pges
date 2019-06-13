package com.pges.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
	@Query("select p from Personne p")
	public Page<Personne> listPersonnes(Pageable pageable);

	@Query("select p from Personne p where p.nomPersonne  like:x")
	public Page<Personne> chercherPersonnes(@Param("x") String mc, Pageable pageable);

	@Modifying
	@Query("update Personne p set p.nomPersonne=:nomPersonne,p.pnomPersonne=:pnomPersonne,p.sexe=:sexe,p.adresse=:adresse,p.telPersonne=:telPersonne,p.fonction=:fonction where p.idPersonne=:idPersonne")
	public void modifierPersonne(@Param("idPersonne") Long idPersonne, @Param("nomPersonne") String nomPersonne,
			@Param("pnomPersonne") String pnomPersonne, @Param("sexe") String sexe, @Param("adresse") String adresse,
			@Param("telPersonne") String telPersonne, @Param("fonction") String fonction);
}
