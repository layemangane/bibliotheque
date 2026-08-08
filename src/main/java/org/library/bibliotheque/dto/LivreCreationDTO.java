package org.library.bibliotheque.dto;

public class LivreCreationDTO {
    private String titre;
    private int anneePublication;
    private boolean disponible;
    private double noteMoyenne;
    private Long auteurId;

    // Constructeur vide obligatoire pour la désérialisation JSON par Jackson
    public LivreCreationDTO() {}

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