package org.library.bibliotheque.repository;

import org.junit.jupiter.api.Test;
import org.library.bibliotheque.entity.Auteur;
import org.library.bibliotheque.entity.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Testcontainers
public class LivreRepositoryIntegrationTest {

    @Container
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer("postgres:18")
            .withDatabaseName("bibliotheque_test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configurerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private AuteurRepository auteurRepository;

    @Test
    void doitSauvegarderEtRetrouverUnLivre() {
        Auteur auteur = auteurRepository.save(new Auteur("Faty Dieng"));
        Livre livre = new Livre("Titre Test", 2024, true, 4.0, auteur);

        Livre livreSauvegarde = livreRepository.save(livre);

        assertTrue(livreRepository.findById(livreSauvegarde.getId()).isPresent());
    }

    @Test
    void doitSauvegarderEtRetrouverLesLivresDisponibles() {
        Auteur auteur = auteurRepository.save(new Auteur("Faty Dieng"));
        livreRepository.save(new Livre("Titre Test 1", 2024, true, 4.0, auteur));
        livreRepository.save(new Livre("Titre Test 2", 2020, true, 5.0, auteur));

        List<Livre> livresDisponibles = livreRepository.findByDisponibleTrue();
        assertEquals(2, livresDisponibles.size());
        assertTrue(livresDisponibles.stream().allMatch(Livre::isDisponible));
    }
}
