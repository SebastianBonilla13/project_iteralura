package com.project.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosAutor(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") String fecha_nacimiento,
        @JsonAlias("death_year") String fecha_fallecimiento
        // @JsonAlias("") List<DatosLibro> libros relacionar luego
        ) {
}
