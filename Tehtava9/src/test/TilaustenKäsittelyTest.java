package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ohjelma.Asiakas;
import ohjelma.Tuote;
import ohjelma.IHinnoittelija;
import ohjelma.TilaustenKäsittely;
import ohjelma.Tilaus;

public class TilaustenKäsittelyTest {

    @Mock
    IHinnoittelija hinnoittelijaMock;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    

    @Test
    public void hintaAlle100() {
        // Arrange
        float alkuSaldo = 100.0f;
        float listaHinta = 30.0f;
        float alennus = 20.0f;
        float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));
        Asiakas asiakas = new Asiakas(alkuSaldo);
        Tuote tuote = new Tuote("TDD in Action", listaHinta);

        // Record behavior of the mock
        when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(alennus);

        // Act
        TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
        käsittelijä.setHinnoittelija(hinnoittelijaMock);
        käsittelijä.käsittele(new Tilaus(asiakas, tuote));


        // Assert
        assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
        verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote);
    }

    @Test
    public void hinta100TaiYli() {
            // Arrange
            float alkuSaldo = 100.0f;
            float listaHinta = 110.0f;
            float alennus = 20.0f;
            float loppuSaldo = alkuSaldo - (listaHinta * (1 - (alennus + 5.0f) / 100));
            Asiakas asiakas = new Asiakas(alkuSaldo);
            Tuote tuote = new Tuote("TDD in Action", listaHinta);

            // Record behavior of the mock
            when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(alennus + 5.0f);

            // Act
            TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
            käsittelijä.setHinnoittelija(hinnoittelijaMock);
            käsittelijä.käsittele(new Tilaus(asiakas, tuote));


            // Assert
            assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);
            verify(hinnoittelijaMock, times(2)).getAlennusProsentti(asiakas, tuote);
        }
}

