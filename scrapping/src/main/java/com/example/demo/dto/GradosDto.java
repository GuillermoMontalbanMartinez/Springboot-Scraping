package com.example.demo.dto;

/**
 * @atributes nombre del grado
 * @atributes codigo del grado
 */
public class GradosDto {
    private String nombre;
    private String codigo;

    public GradosDto(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
