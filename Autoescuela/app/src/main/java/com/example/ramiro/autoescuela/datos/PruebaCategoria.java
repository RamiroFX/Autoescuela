package com.example.ramiro.autoescuela.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ramiro.autoescuela.bd.AutoescuelaContract;
import com.example.ramiro.autoescuela.bd.AutoescuelaContract.TablaPruebaCategoria;
import com.example.ramiro.autoescuela.bd.BDHelper;

/**
 * Created by Ramiro on 21/11/2017.
 */

public class PruebaCategoria {
    int id;
    String descripcion;

    public PruebaCategoria(){

    }

    public PruebaCategoria(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "PruebaCategoria{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public static PruebaCategoria obtenerPruebaCategoriaPorId(int id, Context contexto) {
        PruebaCategoria unaPruebaCategoria = null;
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaPruebaCategoria.NOMBRE_COLUMNA_ID,
                TablaPruebaCategoria.NOMBRE_COLUMNA_DESCRIPCION
        };

        // 2. WHERE de la consulta
        String seleccion = TablaPruebaCategoria.NOMBRE_COLUMNA_ID+ " = ?";

        // 3. VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(id)};

        Cursor result = bd.query(
                TablaPruebaCategoria.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        if (result.moveToNext()) {
            unaPruebaCategoria = new PruebaCategoria(
                    result.getInt(0)
                    , result.getString(1)
            );
        }
        bdHelper.close();
        return unaPruebaCategoria;
    }
}
