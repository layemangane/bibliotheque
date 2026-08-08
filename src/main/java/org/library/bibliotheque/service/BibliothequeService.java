package org.library.bibliotheque.service;

import org.library.bibliotheque.entity.Livre;
import org.library.bibliotheque.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliothequeService {

    private final LivreRepository livreRepository;

    public BibliothequeService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public List<Livre> livresDisponibles() {
        return livreRepository.findByDisponibleTrue();
    }

    public List<Livre> livresByNomAuteur(String nomAuteur) {
        return livreRepository.findByAuteurNom(nomAuteur);
    }

    public List<Livre> livresPubliesApres(int annee) {
        return livreRepository.findByAnneePublicationGreaterThan(annee);
    }

    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    public Livre createLivre(Livre livre) {
        return livreRepository.save(livre);
    }


}
