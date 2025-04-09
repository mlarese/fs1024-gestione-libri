package it.epicode.fs1024_gestione_libri.libri;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LibroServiceTest {
    @Autowired
    private LibroService libroService;
    @Autowired
    private LibroRepository libroRepository;

    @BeforeEach
    public void setUp() {
       libroRepository.deleteAll();
    }

    @Test
    @DisplayName("Test di salvataggio di un libro")
    public void testSave()  {
        System.out.println(libroRepository.count());

        Libro libro = new Libro();
        libro.setAutore("Mario rossi");
        libro.setGenere("fantasy");
        libro.setTitolo("Il signore degli anelli");

        libroService.createLibro(libro);
        assertNotNull(libro.getId());


        Libro libroPresoDaDb = libroService.findById(libro.getId());

        assertNotNull(libroPresoDaDb);
        assertEquals(libro.getAutore(), libroPresoDaDb.getAutore());
        assertEquals(libro.getGenere(), libroPresoDaDb.getGenere());
        assertEquals(libro.getTitolo(), libroPresoDaDb.getTitolo());

    }

    @Test
    @DisplayName("Test di ricerca di un libro per id")
    public void findByTitoloTest() {
        System.out.println(libroRepository.count());
        Libro libro = new Libro();
        libro.setAutore("Mario rossi");
        libro.setGenere("fantasy");
        libro.setTitolo("La notte del giaguaro");

        libroService.createLibro(libro);

        Libro libroRicercato = libroService.findByTitolo("La notte del giaguaro");
        assertNotNull(libroRicercato);

        assertEquals(libro.getAutore(), libroRicercato.getAutore());
        assertEquals(libro.getGenere(), libroRicercato.getGenere());
        assertEquals(libro.getTitolo(), libroRicercato.getTitolo());
    }

    @ParameterizedTest
    @DisplayName("Test di ricerca di un libro per autore")
    @CsvSource({"Mario Rossi, Le mie prigioni", "Giuseppe Verdi, Avatar"})
    public void findByAutoreTest(String autore, String titolo) {
        System.out.println(libroRepository.count());
        Libro libro = new Libro();
        libro.setAutore(autore);
        libro.setGenere("storico");
        libro.setTitolo(titolo);

        libroService.createLibro(libro);

        List<Libro> libri = libroService.findByAutore(autore);
        assertNotEquals(0, libri.size());
    }

}
