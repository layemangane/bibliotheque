package org.library.bibliotheque.controller;

import org.library.bibliotheque.dto.LivreCreationDTO;
import org.library.bibliotheque.dto.LivreDTO;
import org.library.bibliotheque.entity.Auteur;
import org.library.bibliotheque.entity.Livre;
import org.library.bibliotheque.exception.LivreNonTrouveException;
import org.library.bibliotheque.repository.AuteurRepository;
import org.library.bibliotheque.service.BibliothequeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final BibliothequeService bibliothequeService;
    private final AuteurRepository auteurRepository;

    public LivreController(BibliothequeService bibliothequeService, AuteurRepository auteurRepository) {
        this.bibliothequeService = bibliothequeService;
        this.auteurRepository = auteurRepository;
    }

    @GetMapping
    public ResponseEntity<List<LivreDTO>> getLivresDisponibles() {
        List<LivreDTO> livres = bibliothequeService.livresDisponibles().stream()
                .map(LivreDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(livres);
    }

    @GetMapping("/auteur/{nomAuteur}")
    public ResponseEntity<List<LivreDTO>> getLivresByNomAuteur(@PathVariable String nomAuteur) {
        List<LivreDTO> livreDTOS = bibliothequeService.livresByNomAuteur(nomAuteur).stream()
                .map(LivreDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(livreDTOS);
    }

    @GetMapping("/apres/{annee}")
    public ResponseEntity<List<LivreDTO>> getLivresPublieApresAnnee(@PathVariable int annee) {
        List<LivreDTO> livresApres = bibliothequeService.livresPubliesApres(annee).stream()
                .map(LivreDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(livresApres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivreDTO> getLivreById(@PathVariable Long id) {
        return bibliothequeService.getLivreById(id)
                .map(LivreDTO::new)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new LivreNonTrouveException("Livre non trouvé avec l'id : " + id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LivreDTO> createLivre(@RequestBody LivreCreationDTO creationDTO) {
        Optional<Auteur> auteurOptional = auteurRepository.findById(creationDTO.getAuteurId());
        if (auteurOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Livre newLivre = new Livre(
                creationDTO.getTitre(),
                creationDTO.getAnneePublication(),
                creationDTO.isDisponible(),
                creationDTO.getNoteMoyenne(),
                auteurOptional.get()
        );

        Livre livreSaved = bibliothequeService.createLivre(newLivre);
        LivreDTO livreDTO = new LivreDTO(livreSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(livreDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LivreDTO> updateLivre(@PathVariable Long id, @RequestBody LivreCreationDTO livreCreationDTO) {
        Optional<Auteur> auteurOptional = auteurRepository.findById(livreCreationDTO.getAuteurId());
        if (auteurOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Livre newLivre = new Livre(
                livreCreationDTO.getTitre(),
                livreCreationDTO.getAnneePublication(),
                livreCreationDTO.isDisponible(),
                livreCreationDTO.getNoteMoyenne(),
                auteurOptional.get()
        );
        Livre updatedLivre = bibliothequeService.updateLivre(id, newLivre);
        LivreDTO livreDTO = new LivreDTO(updatedLivre);
        return ResponseEntity.status(HttpStatus.OK).body(livreDTO);
    }

}
