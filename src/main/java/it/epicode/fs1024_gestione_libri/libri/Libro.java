package it.epicode.fs1024_gestione_libri.libri;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "libri")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @Column(nullable = false, length = 100)
    private String titolo;

    @Column(nullable = false, length = 100)
    private String autore;

    @Column( length = 100)
    private String genere;

    @Column(length = 100)
    private double prezzo;

    @Column(length = 100)
    private int iva;

    public double prezzoFinale(){
        return prezzo + (prezzo * iva / 100);
    }

}
