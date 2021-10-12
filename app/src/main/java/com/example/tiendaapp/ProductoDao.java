package com.example.tiendaapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductoDao {

    @Query("select * from productos")
    List<Producto> obtenerProducto();

    @Insert
    void agregar(Producto producto);

    @Update
    void editar(Producto producto);

    @Delete
    void eliminar(Producto producto);

    @Query("select * from productos where id = :parametro")
    Producto obtenerPorId(int parametro);
}
