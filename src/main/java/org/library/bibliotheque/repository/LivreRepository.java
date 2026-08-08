package org.library.bibliotheque.repository;

import org.library.bibliotheque.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

    @Query("SELECT l FROM Livre l JOIN FETCH l.auteur WHERE l.disponible = true")
    List<Livre> findByDisponibleTrue();

    List<Livre> findByAuteurNom(String nomAuteur);

    List<Livre> findByAnneePublicationGreaterThan(int annee);

    List<Livre> findByNoteMoyenneGreaterThanEqualOrderByNoteMoyenneDesc(double note);

    Optional<Livre> findByTitreAndAuteurNom(String titre, String nomAuteur);

    boolean existsByTitre(String titre);

    long countByDisponibleTrue();
}
