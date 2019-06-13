package com.pges.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pges.entities.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
	@Modifying
	@Query(value = "insert into Implantation(idProjet,idSite,codeEntr,produitExp)VALUES (?1,?2,?3,?4)", nativeQuery = true)
	public void implanter(@Param("idProjet") Long idProjet, @Param("idSite") Long idSite,
			@Param("codeEntr") Long codeEntr, @Param("produitExp") String produitExp);

	@Query("select p from Projet p where p.entreprise.codeEntr like:x order by p.dateDebut")
	public Page<Projet> listProjets(@Param("x") String codeEntr, Pageable pageable);
}
