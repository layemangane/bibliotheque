package org.library.bibliotheque.dto;

import org.library.bibliotheque.entity.Auteur;

public class AuteurDTO {
    private Long id;
    private String nom;

    public AuteurDTO(Auteur auteur) {
        this.id = auteur.getId();
        this.nom = auteur.getNom();
    }

    public AuteurDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
