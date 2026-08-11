package org.library.bibliotheque.dto;

public class InscriptionDTO {
    private String email;
    private String motDePasse;

    public InscriptionDTO() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
}
