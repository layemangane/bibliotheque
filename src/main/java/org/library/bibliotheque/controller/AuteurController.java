package org.library.bibliotheque.controller;

import org.library.bibliotheque.dto.AuteurDTO;
import org.library.bibliotheque.entity.Auteur;
import org.library.bibliotheque.service.BibliothequeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auteur")
public class AuteurController {

    private final BibliothequeService bibliothequeService;

    public AuteurController(BibliothequeService bibliothequeService) {
        this.bibliothequeService = bibliothequeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuteurDTO>> getAuteurs() {
        List<AuteurDTO> findedAuteurDTOS = bibliothequeService.findAuteurs()
                .stream()
                .map(AuteurDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(findedAuteurDTOS);
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Auteur> addAuteur(@RequestBody Auteur auteur) {
        Auteur savedAuteur = bibliothequeService.createAuteur(auteur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuteur);
    }
}
