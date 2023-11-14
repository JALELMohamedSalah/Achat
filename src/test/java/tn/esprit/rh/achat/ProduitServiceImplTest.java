package tn.esprit.rh.achat;

import tn.esprit.rh.achat.services.ProduitServiceImpl;
import  tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.controllers.ProduitRestController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ProduitServiceImplTest {

    @InjectMocks
    ProduitServiceImpl produitService;

    @Mock
    ProduitRepository produitRepository;

    @Test
    void addProduitTest() {
        // Initialisation du mockito
        MockitoAnnotations.initMocks(this);

        // Données de test
        Produit produit = new Produit();

        // Appel de la méthode à tester
        produitService.addProduit(produit);

        // Vérification que la méthode save a été appelée avec le bon argument (le produit)
        verify(produitRepository, times(1)).save(produit);
    }

    @Test
    void deleteProduitTest() {
        // Initialisation du mockito
        MockitoAnnotations.initMocks(this);

        // Données de test
        Long produitId = 1L;

        // Appel de la méthode à tester
        produitService.deleteProduit(produitId);

        // Vérification que la méthode deleteById a été appelée avec le bon argument (l'ID du produit)
        verify(produitRepository, times(1)).deleteById(produitId);
    }
}


