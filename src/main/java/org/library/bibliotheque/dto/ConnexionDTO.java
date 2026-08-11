package org.library.bibliotheque.dto;

public class ConnexionDTO {
    private String email;
    private String motDePasse;

    public ConnexionDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
