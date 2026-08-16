package org.library.bibliotheque.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.library.bibliotheque.entity.Auteur;
import org.library.bibliotheque.repository.AuteurRepository;
import org.library.bibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class LivreControllerIntegrationTest {

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
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private AuteurRepository auteurRepository;

    @BeforeEach
    public void setup() {
        auteurRepository.save(new Auteur("Pr Massamba"));
    }

    @Test
    void getLivres_sansToken_doitRetourner403() throws Exception {
        mockMvc.perform(get("/api/livres"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void postLivre_avecTitreVide_doitRetourner403() throws Exception {
        String jsonValide = """
            {
                "titre": "Titre valide",
                "anneePublication": 2024,
                "disponible": true,
                "noteMoyenne": 4.0,
                "auteurId": 1
            }
            """;
        mockMvc.perform(
                post("/api/livres")
                .contentType("application/json")
                .content(jsonValide)
            ).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getLivres_avecUtilisateurMock_doitRetourner200() throws Exception {
        mockMvc.perform(get("/api/livres"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void postLivre_avecRoleAdmin_doitRetourner201() throws Exception {
        String jsonValide = """
            {
                "titre": "Titre valide",
                "anneePublication": 2024,
                "disponible": true,
                "noteMoyenne": 4.0,
                "auteurId": 1
            }
            """;
        mockMvc.perform(post("/api/livres")
            .contentType("application/json")
            .content(jsonValide)
        ).andExpect(status().isCreated());
    }
}
