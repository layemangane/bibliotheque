package org.library.bibliotheque.dto;

import org.library.bibliotheque.entity.Livre;

public class LivreDTO {
    String titre;
    int anneePublication;
    boolean disponible;
    double noteMoyenne;
    String nomAuteur;

    public LivreDTO(Livre livre) {
        this.titre = livre.getTitre();
        this.anneePublication = livre.getAnneePublication();
        this.disponible = livre.isDisponible();
        this.noteMoyenne = livre.getNoteMoyenne();
        this.nomAuteur = livre.getAuteur().getNom();
    }

    public String getTitre() {
        return titre;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public double getNoteMoyenne() {
        return noteMoyenne;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }
}
