package com.project.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String fecha_nacimiento;
    private String fecha_fallecimiento;

    @ManyToMany
    @JoinTable(
            name = "autor_libro",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )
    private List<Libro> libros;

    public Autor(){
    }

    public Autor(DatosAutor autorConsultag) {
        this.name = autorConsultag.name();
        System.out.println("HOLAAAAAAAAAA");
        //this.fecha_nacimiento = String.valueOf(LocalDate.parse(autorConsultag.fecha_nacimiento()));
        this.fecha_nacimiento = autorConsultag.fecha_nacimiento();
        //this.fecha_fallecimiento = String.valueOf(LocalDate.parse(autorConsultag.fecha_fallecimiento()));
        this.fecha_fallecimiento = autorConsultag.fecha_fallecimiento();
        System.out.println("HOLAAAAAAAAAA");

        this.libros = asignarLibros(); // PENDIENTE
        System.out.println("llibro asignado");
    }

    private List<Libro> asignarLibros() {
        List<Libro> libros = new ArrayList<>();
        Libro libro1 = new Libro();
        libro1.setTitulo("HOLA");


        libros.add(libro1);
        // iterar o agregar, o agregar de otra manera (cuando se crea al autor)
        return libros;
    }


    @Override
    public String toString() {
        return "Autor{" +
                ", name='" + name + '\'' +
                ", fecha_nacimiento='" + fecha_nacimiento + '\'' +
                ", fecha_fallecimiento='" + fecha_fallecimiento + '\'' +
                ", libros=" + libros +
                '}';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_fallecimiento() {
        return fecha_fallecimiento;
    }

    public void setFecha_fallecimiento(String fecha_fallecimiento) {
        this.fecha_fallecimiento = fecha_fallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
