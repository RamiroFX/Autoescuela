package com.example.ramiro.autoescuela.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ramiro.autoescuela.bd.BDHelper;
import com.example.ramiro.autoescuela.bd.AutoescuelaContract.TablaPregunta;

import java.util.ArrayList;

/**
 * Created by Ramiro on 21/11/2017.
 */

public class Pregunta {

    int id, idPruebaCategoria;
    String pregunta;

    public Pregunta() {
    }

    public Pregunta(int id, int idPruebaCategoria, String pregunta) {
        this.id = id;
        this.idPruebaCategoria = idPruebaCategoria;
        this.pregunta = pregunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPruebaCategoria() {
        return idPruebaCategoria;
    }

    public void setIdPruebaCategoria(int idPruebaCategoria) {
        this.idPruebaCategoria = idPruebaCategoria;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id=" + id +
                ", idPruebaCategoria=" + idPruebaCategoria +
                ", pregunta='" + pregunta + '\'' +
                '}';
    }

    public static ArrayList<Pregunta> obtenerPreguntasPorCategoria(int idPruebaCategoria, Context contexto) {
        ArrayList<Pregunta> preguntas= new ArrayList<>();
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaPregunta.NOMBRE_COLUMNA_ID,
                TablaPregunta.NOMBRE_COLUMNA_ID_CATEGORIA,
                TablaPregunta.NOMBRE_COLUMNA_PREGUNTA
        };

        // 2. WHERE de la consulta
        String seleccion = TablaPregunta.NOMBRE_COLUMNA_ID_CATEGORIA+ " = ?";

        // 3. VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(idPruebaCategoria)};

        Cursor result = bd.query(
                TablaPregunta.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        while (result.moveToNext()) {
            Pregunta unaPregunta = new Pregunta(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getString(2)
            );
            preguntas.add(unaPregunta);
        }
        bdHelper.close();
        return preguntas;
    }

    public static Pregunta obtenerPreguntaPorId(int idPregunta, Context contexto) {
        Pregunta pregunta= null;
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaPregunta.NOMBRE_COLUMNA_ID,
                TablaPregunta.NOMBRE_COLUMNA_ID_CATEGORIA,
                TablaPregunta.NOMBRE_COLUMNA_PREGUNTA
        };

        // 2. WHERE de la consulta
        String seleccion = TablaPregunta.NOMBRE_COLUMNA_ID+ " = ?";

        // 3. VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(idPregunta)};

        Cursor result = bd.query(
                TablaPregunta.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        if (result.moveToNext()) {
            pregunta = new Pregunta(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getString(2)
            );
        }
        bdHelper.close();
        return pregunta;
    }
}
