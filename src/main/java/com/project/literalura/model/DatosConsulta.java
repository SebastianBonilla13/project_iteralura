package com.project.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosConsulta(
        @JsonAlias("results") ArrayList<DatosLibro> resultadosLibros
) {
}
