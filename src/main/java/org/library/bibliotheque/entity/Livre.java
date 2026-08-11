package org.library.bibliotheque.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "livres")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "annee_publication")
    private int anneePublication;

    private boolean disponible;

    @Column(name = "note_moyenne")
    private double noteMoyenne;

    @ManyToOne()
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;

    public Livre() {
    }

    public Livre(String titre, int anneePublication, boolean disponible, double noteMoyenne, Auteur auteur) {
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.disponible = disponible;
        this.noteMoyenne = noteMoyenne;
        this.auteur = auteur;
    }

    public Long getId() {
        return id;
    }

    public double getNoteMoyenne() {
        return noteMoyenne;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public String getTitre() {
        return titre;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setNoteMoyenne(double noteMoyenne) {
        this.noteMoyenne = noteMoyenne;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false; // Si l'objet est nul ou prvient d'une classe differente de celle-ci, on return false
        Livre livre = (Livre) o; // Initialisation d'un objet Livre avec casting du type Livre
        return Objects.equals(titre, livre.titre) && Objects.equals(auteur, livre.auteur); // retourne true si les titres et les auteurs sont les memes sinon false
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre, auteur);
    }


}

