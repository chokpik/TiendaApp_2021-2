package com.example.tiendaapp;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Producto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private Double precio;

    @Nullable
    private String descripcion;

    @ColumnInfo(name = "url_imagen")
    private String urlImagen;

    Producto() {}

    Producto(String nombre, Double precio, String urlImagen)
    {
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;
        this.descripcion = "Sin descripcion";
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

