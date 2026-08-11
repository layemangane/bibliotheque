package org.library.bibliotheque.controller;

import org.library.bibliotheque.dto.ConnexionDTO;
import org.library.bibliotheque.dto.CreatedUserDTO;
import org.library.bibliotheque.dto.InscriptionDTO;
import org.library.bibliotheque.dto.ResponseTokenDTO;
import org.library.bibliotheque.entity.Utilisateur;
import org.library.bibliotheque.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/inscription")
    public ResponseEntity<CreatedUserDTO> inscription(@RequestBody InscriptionDTO inscriptionDTO) {
        Utilisateur savedUser = authService.inscrire(inscriptionDTO);
        CreatedUserDTO createdUserDTO = new CreatedUserDTO(savedUser.getId(), savedUser.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
    }

    @PostMapping("/connexion")
    public ResponseEntity<ResponseTokenDTO> connexion(@RequestBody ConnexionDTO connexionDTO) {
        String token = authService.connecter(connexionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseTokenDTO(token));
    }



}
