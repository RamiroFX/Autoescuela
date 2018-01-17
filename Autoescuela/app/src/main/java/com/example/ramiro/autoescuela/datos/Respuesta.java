package com.example.ramiro.autoescuela.datos;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ramiro.autoescuela.bd.AutoescuelaContract;
import com.example.ramiro.autoescuela.bd.AutoescuelaContract.TablaRespuesta;
import com.example.ramiro.autoescuela.bd.BDHelper;

import java.util.ArrayList;

/**
 * Created by Ramiro on 22/11/2017.
 */

public class Respuesta {

    int id, idPregunta;
    String respuesta;
    boolean esCorrecta;

    public Respuesta() {
    }

    public Respuesta(int id, int idPregunta, String respuesta, boolean esCorrecta) {
        this.id = id;
        this.idPregunta = idPregunta;
        this.respuesta = respuesta;
        this.esCorrecta = esCorrecta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "id=" + id +
                ", idPregunta=" + idPregunta +
                ", respuesta='" + respuesta + '\'' +
                ", esCorrecta=" + esCorrecta +
                '}';
    }

    public static ArrayList<Respuesta> obtenerRespuestaPorIdPregunta(int idPregunta, Context contexto) {
        ArrayList<Respuesta> respuestas = new ArrayList<>();
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaRespuesta.NOMBRE_COLUMNA_ID,
                TablaRespuesta.NOMBRE_COLUMNA_ID_PREGUNTA,
                TablaRespuesta.NOMBRE_COLUMNA_RESPUESTA,
                TablaRespuesta.NOMBRE_COLUMNA_ES_CORRECTA
        };

        // 2. WHERE de la consulta
        String seleccion = TablaRespuesta.NOMBRE_COLUMNA_ID_PREGUNTA + " = ?";

        // 3. VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(idPregunta)};

        Cursor result = bd.query(
                TablaRespuesta.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        while (result.moveToNext()) {
            Respuesta unaRespuesta = new Respuesta(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getString(2)
                    , result.getInt(3) > 0
            );
            respuestas.add(unaRespuesta);
        }
        bdHelper.close();
        return respuestas;
    }

    public static Respuesta obtenerRespuestaPorId(int idRespuesta, Context contexto) {
        Respuesta respuesta = null;
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaRespuesta.NOMBRE_COLUMNA_ID,
                TablaRespuesta.NOMBRE_COLUMNA_ID_PREGUNTA,
                TablaRespuesta.NOMBRE_COLUMNA_RESPUESTA,
                TablaRespuesta.NOMBRE_COLUMNA_ES_CORRECTA
        };

        // 2. WHERE de la consulta
        String seleccion = TablaRespuesta.NOMBRE_COLUMNA_ID + " = ?";

        // 3. VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(idRespuesta)};

        Cursor result = bd.query(
                TablaRespuesta.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        if (result.moveToNext()) {
            respuesta = new Respuesta(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getString(2)
                    , result.getInt(3) > 0
            );
        }
        bdHelper.close();
        return respuesta;
    }

    public static Respuesta obtenerRespuestaCorrectaPorIdPregunta(int idPregunta, Context contexto) {
        Respuesta respuesta = null;
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaRespuesta.NOMBRE_COLUMNA_ID,
                TablaRespuesta.NOMBRE_COLUMNA_ID_PREGUNTA,
                TablaRespuesta.NOMBRE_COLUMNA_RESPUESTA,
                TablaRespuesta.NOMBRE_COLUMNA_ES_CORRECTA
        };

        // 2. WHERE de la consulta
        String seleccion = TablaRespuesta.NOMBRE_COLUMNA_ID_PREGUNTA + " = ?" +
                " AND " + TablaRespuesta.NOMBRE_COLUMNA_ES_CORRECTA + " > 0";

        // 3. VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(idPregunta)};

        Cursor result = bd.query(
                TablaRespuesta.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        if (result.moveToNext()) {
            respuesta = new Respuesta(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getString(2)
                    , result.getInt(3) > 0
            );
        }
        bdHelper.close();
        return respuesta;
    }
}
