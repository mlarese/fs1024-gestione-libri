package it.epicode.fs1024_gestione_libri.libri;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    public Libro findByTitolo(String titolo);
    public List<Libro> findByAutore(String autore);
    public List<Libro> findByGenere(String genere);

    public Boolean existsByTitolo(String titolo);

}
