package org.library.bibliotheque.service;

import org.library.bibliotheque.entity.Auteur;
import org.library.bibliotheque.entity.Livre;
import org.library.bibliotheque.repository.AuteurRepository;
import org.library.bibliotheque.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliothequeService {

    private final LivreRepository livreRepository;
    private final AuteurRepository auteurRepository;

    public BibliothequeService(LivreRepository livreRepository, AuteurRepository auteurRepository) {
        this.livreRepository = livreRepository;
        this.auteurRepository = auteurRepository;
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

    public Livre updateLivre(Long id, Livre livre) {
        Livre livreFound = livreRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Livre not found"));
        livreFound.setTitre(livre.getTitre());
        livreFound.setAnneePublication(livre.getAnneePublication());
        livreFound.setDisponible(livre.isDisponible());
        livreFound.setNoteMoyenne(livre.getNoteMoyenne());
        livreFound.setAuteur(livre.getAuteur());
        return livreRepository.save(livreFound);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    public List<Auteur> findAuteurs() {
        return this.auteurRepository.findAll();
    }

    public Auteur createAuteur(Auteur auteur) {
        return auteurRepository.save(auteur);
    }


}
