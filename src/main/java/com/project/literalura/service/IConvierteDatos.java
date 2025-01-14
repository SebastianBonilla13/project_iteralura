package com.project.literalura.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
