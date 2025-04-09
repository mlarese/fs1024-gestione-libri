package it.epicode.fs1024_gestione_libri.libri;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    // riceerca per id
    public Libro findById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }


    // ricerca per titolo
    public Libro findByTitolo(String titolo) {
        return libroRepository.findByTitolo(titolo);
    }

    // ricerca per autore
    public List<Libro> findByAutore(String autore) {
        return libroRepository.findByAutore(autore);
    }

    // ricerca per genere
    public List<Libro> findByGenere(String genere) {
        return libroRepository.findByGenere(genere);
    }


    // creazione di un libro
    public void createLibro(Libro libro) {
        if (libroRepository.existsByTitolo(libro.getTitolo())) {
            throw new EntityExistsException("Il libro con titolo " + libro.getTitolo() + " esiste già");
        }

        if(libro.getTitolo() == null || libro.getTitolo().isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere nullo o vuoto");
        }

        if(libro.getAutore() == null || libro.getAutore().isEmpty()) {
            throw new IllegalArgumentException("L'autore non può essere nullo o vuoto");
        }

        libroRepository.save(libro);
    }


    // modifica di un libro

    public void updateLibro(Libro libro) {
        if (libro.getId() == null) {
            throw new IllegalArgumentException("L'id non può essere nullo");
        }

        if(libro.getTitolo() == null || libro.getTitolo().isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere nullo o vuoto");
        }

        if(libro.getAutore() == null || libro.getAutore().isEmpty()) {
            throw new IllegalArgumentException("L'autore non può essere nullo o vuoto");
        }
        if (libroRepository.existsById(libro.getId())) {
            libroRepository.save(libro);
        } else {
            throw new EntityNotFoundException("Il libro con id " + libro.getId() + " non esiste");
        }
    }
    // eliminazione di un libro
    public void deleteLibro(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Il libro con id " + id + " non esiste");
        }
    }


}
