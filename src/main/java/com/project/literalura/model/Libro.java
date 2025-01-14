package com.project.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true, columnDefinition = "TEXT")
    private String titulo;
    private String idioma;
    private Integer numero_descargas;

    @ManyToMany(mappedBy = "libros")
    private List<Autor> autores;

    public Libro(){
    }

    public Libro(DatosLibro libroConsulta) {

        if (libroConsulta.titulo().length() > 255) { // cortar para texto +255 caracteres
            this.titulo = libroConsulta.titulo().substring(0, 255);
        }else{
            this.titulo = libroConsulta.titulo();
        }
        this.idioma = libroConsulta.idioma().get(0); // el primer idioma
        this.numero_descargas = libroConsulta.numero_descargas();
        this.autores = libroConsulta.autores().stream() // obteniendo autores
                .map(a -> new Autor(a) )
                .collect(Collectors.toList());
    }

    //private List<Autor> asignarAutores(DatosLibro libroConsulta) {
    //    libroConsulta.autores().forEach(a -> new Autor(a));
    //}

    @Override
    public String toString() {
        return "Libro{" +
                ", titulo='" + titulo + '\'' +
                ", idioma=" + idioma +
                ", numero_descargas=" + numero_descargas +
                ", autores=" + autores +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumero_descargas() {
        return numero_descargas;
    }

    public void setNumero_descargas(Integer numero_descargas) {
        this.numero_descargas = numero_descargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}
