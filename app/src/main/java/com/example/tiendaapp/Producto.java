package com.example.tiendaapp;

import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String urlImagen;

    Producto() {}

    Producto(String nombre, Double precio, String urlImagen)
    {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    public int getId() { return id;   }

    public void setId(int id) { this.id = id; }

    public Double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public String toString() {
        return "nombre= " + nombre + ", ("+ precio + "$)";
    }
}

