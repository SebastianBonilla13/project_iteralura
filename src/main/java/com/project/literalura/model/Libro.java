package com.project.literalura.model;

import jakarta.persistence.*;

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

    @ManyToOne()
    private Autor autor;

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
        this.autor = new Autor(libroConsulta.autores().get(0), this);
    }

    @Override
    public String toString() {
        return "----- LIBRO ----- \n" +
                "Título: " + titulo + '\n' +
                "Autor: " + (autor != null ? autor.getName() : "null") + '\n' +
                "Idioma: " + idioma + '\n' +
                "Numero de descargas: " + numero_descargas + '\n' +
                "----------------" + '\n'
                ;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}