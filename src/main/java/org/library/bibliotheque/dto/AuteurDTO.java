package org.library.bibliotheque.dto;

public class AuteurDTO {
    private String nom;

    public AuteurDTO(String nom) {
        this.nom = nom;
    }

    public AuteurDTO() {
    }

    public String getNom() {
        return nom;
    }
}
