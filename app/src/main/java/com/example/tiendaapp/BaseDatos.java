package com.example.tiendaapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Producto.class},version = 1)
public abstract class BaseDatos extends RoomDatabase {

    public abstract ProductoDao productoDao();

    private static BaseDatos instancia = null;

    public static BaseDatos getInstance(Context context){
        if(instancia == null)
        {
            instancia = Room.databaseBuilder(context, BaseDatos.class,"tiendapp.db");
        }
    }


}
