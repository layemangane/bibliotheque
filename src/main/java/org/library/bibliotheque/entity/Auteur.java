package org.library.bibliotheque.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "auteurs")
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "auteur")
    private List<Livre> livres;

    public Auteur() {
    }

    public Auteur(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public List<Livre> getLivres() {
        return livres;
    }
}

