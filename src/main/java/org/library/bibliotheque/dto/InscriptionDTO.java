package org.library.bibliotheque.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InscriptionDTO {

    @NotBlank(message = "L'email est obligatoire !")
    @Email(message = "Email invalid !")
    private String email;

    @Size(min = 8, message = "Le mot de passe doit avoir minimum 8 caractères !")
    private String motDePasse;

    public InscriptionDTO() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
}
