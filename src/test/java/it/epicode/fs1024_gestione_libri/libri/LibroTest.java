package it.epicode.fs1024_gestione_libri.libri;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LibroTest {

    @Test
    @DisplayName("Test di calcolo dell'iva")
    public void testPrezzoFinale() {
        Libro libro = new Libro();
        libro.setPrezzo(100.0);
        libro.setIva(22);
        double prezzoFinale = libro.prezzoFinale();

        assertNotNull(libro);
        assertEquals(122.0, prezzoFinale);

    }
}
