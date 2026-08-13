package org.library.bibliotheque.dto;

import jakarta.validation.constraints.*;
import org.library.bibliotheque.entity.Livre;

public class LivreCreationDTO {

    @NotBlank(message = "Le titre est obligatoire !")
    private String titre;

    @Min(value = 1000, message = "L'année de publication doit être valide")
    @Max(value = 2100, message = "L'année de publication doit être valide")
    private int anneePublication;

    private boolean disponible;

    @DecimalMin(value = "0.0", message = "La note ne peut pas être négative")
    @DecimalMax(value = "5.0", message = "La note ne peut pas dépasser 5")
    private double noteMoyenne;

    @NotNull(message = "L'auteur est obligatoire")
    private Long auteurId;

    // Constructeur vide obligatoire pour la désérialisation JSON par Jackson
    public LivreCreationDTO() {}

    public LivreCreationDTO(Livre livre) {
        this.titre = livre.getTitre();
        this.anneePublication = livre.getAnneePublication();
        this.disponible = livre.isDisponible();
        this.noteMoyenne = livre.getNoteMoyenne();
        this.auteurId = livre.getAuteur().getId();
    }

    // Getters (nécessaires pour que Jackson puisse lire les valeurs après désérialisation)
    public String getTitre() { return titre; }
    public int getAnneePublication() { return anneePublication; }
    public boolean isDisponible() { return disponible; }
    public double getNoteMoyenne() { return noteMoyenne; }
    public Long getAuteurId() { return auteurId; }

    // Setters (nécessaires pour que Jackson puisse remplir l'objet depuis le JSON)
    public void setTitre(String titre) { this.titre = titre; }
    public void setAnneePublication(int anneePublication) { this.anneePublication = anneePublication; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public void setNoteMoyenne(double noteMoyenne) { this.noteMoyenne = noteMoyenne; }
    public void setAuteurId(Long auteurId) { this.auteurId = auteurId; }
}
