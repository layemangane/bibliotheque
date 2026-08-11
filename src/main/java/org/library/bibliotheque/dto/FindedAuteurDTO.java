package org.library.bibliotheque.dto;

import org.library.bibliotheque.entity.Auteur;

import java.util.List;
import java.util.stream.Collectors;

public class FindedAuteurDTO {
    private String nom;
    private List<LivreCreationDTO> livres;

    public FindedAuteurDTO() {}

    public FindedAuteurDTO(Auteur  auteur) {
        this.nom = auteur.getNom();
        this.livres = auteur.getLivres().stream().map(LivreCreationDTO::new).collect(Collectors.toList());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<LivreCreationDTO> getLivres() {
        return livres;
    }

    public void setLivres(List<LivreCreationDTO> livres) {
        this.livres = livres;
    }
}
