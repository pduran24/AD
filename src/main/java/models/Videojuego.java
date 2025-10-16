package models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Videojuego implements Serializable {

    private Integer id;
    private String nombre;
    private String desarrollador;
    private Integer anio;
    private String genero;
    private String plataforma;

}
