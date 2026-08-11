package org.library.bibliotheque.service;

import org.library.bibliotheque.dto.ConnexionDTO;
import org.library.bibliotheque.dto.InscriptionDTO;
import org.library.bibliotheque.entity.Utilisateur;
import org.library.bibliotheque.repository.UtilisateurRepository;
import org.library.bibliotheque.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Utilisateur inscrire(InscriptionDTO inscriptionDTO) {
        if (utilisateurRepository.existsByEmail(inscriptionDTO.getEmail())) {
            throw new IllegalStateException("Cet email est déjà utilisé");
        }
        String motDePasseHache = passwordEncoder.encode(inscriptionDTO.getMotDePasse());
        Utilisateur utilisateur = new Utilisateur(inscriptionDTO.getEmail(), motDePasseHache, "USER");
        return utilisateurRepository.save(utilisateur);
    }

    public String connecter(ConnexionDTO dto) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(dto.getEmail())
                .orElseThrow(()  -> new IllegalArgumentException("Email ou mot de passe incorrect"));
        if (!passwordEncoder.matches(dto.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new IllegalArgumentException("Email ou mot de passe incorrect");
        }
        return jwtService.genererToken(utilisateur.getEmail(), utilisateur.getRole());
    }

}
