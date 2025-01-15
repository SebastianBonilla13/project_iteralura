package com.project.literalura.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String fecha_nacimiento;
    private String fecha_fallecimiento;

    @OneToMany(mappedBy = "autor", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();;

    public Autor(){
    }

    public Autor(DatosAutor autorConsulta, Libro libro) {
        this.name = autorConsulta.name();
        //this.fecha_nacimiento = String.valueOf(LocalDate.parse(autorConsultag.fecha_nacimiento()));
        this.fecha_nacimiento = autorConsulta.fecha_nacimiento();
        //this.fecha_fallecimiento = String.valueOf(LocalDate.parse(autorConsultag.fecha_fallecimiento()));
        this.fecha_fallecimiento = autorConsulta.fecha_fallecimiento();
        this.libros.add(libro);
        //System.out.println("libro asignado");
    }

    @Override
    public String toString() {
        return
                "Autor: " + name + '\n' +
                "Fecha de nacimiento: " + fecha_nacimiento + '\n' +
                "Fecha de fallecimiento: " + fecha_fallecimiento + '\n' +
                "Libros: [" + libros.stream()
                    .map(Libro::getTitulo)
                    .collect(Collectors.joining(", ")) + "]\n";
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
