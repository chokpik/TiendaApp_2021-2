package com.example.tiendaapp;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductoDao {

    @Query("select * from productos")
    List<Producto> obtenerProducto();
}
