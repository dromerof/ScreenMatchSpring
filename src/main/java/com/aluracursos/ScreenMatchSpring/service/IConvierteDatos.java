package com.aluracursos.ScreenMatchSpring.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
