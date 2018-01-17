package com.example.ramiro.autoescuela.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ramiro.autoescuela.activities.VerPruebaDetalleActivity;
import com.example.ramiro.autoescuela.bd.AutoescuelaContract;
import com.example.ramiro.autoescuela.bd.AutoescuelaContract.TablaPruebaCabecera;
import com.example.ramiro.autoescuela.bd.BDHelper;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Ramiro on 21/11/2017.
 */

public class PruebaCabecera {
    int id, idEstudiante, idCategoria;
    Timestamp tiempo;

    public PruebaCabecera() {
    }

    public PruebaCabecera(int id, int idEstudiante, int idCategoria, Timestamp tiempo) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.idCategoria = idCategoria;
        this.tiempo = tiempo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Timestamp getTiempo() {
        return tiempo;
    }

    public void setTiempo(Timestamp tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "PruebaCabecera{" +
                "id=" + id +
                ", idEstudiante=" + idEstudiante +
                ", idCategoria=" + idCategoria +
                ", tiempo=" + tiempo +
                '}';
    }

    public static void guardarPrueba(PruebaCabecera pruebaCabecera, ArrayList<PruebaDetalle> pruebaDetalles, Context contexto) {

        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        ContentValues PruebaCabeceraValues = new ContentValues();
        ContentValues PruebaDetalleValues = new ContentValues();

        PruebaCabeceraValues.put(TablaPruebaCabecera.
                        NOMBRE_COLUMNA_ID_ESTUDIANTE,
                pruebaCabecera.getIdEstudiante());

        PruebaCabeceraValues.put(TablaPruebaCabecera.
                        NOMBRE_COLUMNA_ID_CATEGORIA,
                pruebaCabecera.getIdCategoria());

        bd.beginTransaction();
        try {
            Long idPruebaCabecera = bd.insert(TablaPruebaCabecera.NOMBRE_TABLA, null, PruebaCabeceraValues);
            for (PruebaDetalle unaPruebaDetalle: pruebaDetalles) {
                PruebaDetalleValues.put(AutoescuelaContract.TablaPruebaDetalle.
                                NOMBRE_COLUMNA_ID_CABECERA,
                        idPruebaCabecera);

                PruebaDetalleValues.put(AutoescuelaContract.TablaPruebaDetalle.
                                NOMBRE_COLUMNA_ID_PREGUNTA,
                        unaPruebaDetalle.getIdPregunta());

                PruebaDetalleValues.put(AutoescuelaContract.TablaPruebaDetalle.
                                NOMBRE_COLUMNA_ID_RESPUESTA,
                        unaPruebaDetalle.getIdRespuesta());

                bd.insert(AutoescuelaContract.TablaPruebaDetalle.NOMBRE_TABLA, null, PruebaDetalleValues);
            }
            bd.setTransactionSuccessful();
        } finally {
            bd.endTransaction();
        }
        bdHelper.close();
    }

    public static ArrayList obtenerPruebasPorEstudiante(int idEstudiante, Context contexto) {
        ArrayList<PruebaCabecera> pruebaCabeceras = new ArrayList<>();
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaPruebaCabecera.NOMBRE_COLUMNA_ID,
                TablaPruebaCabecera.NOMBRE_COLUMNA_ID_ESTUDIANTE,
                TablaPruebaCabecera.NOMBRE_COLUMNA_ID_CATEGORIA,
                TablaPruebaCabecera.NOMBRE_COLUMNA_TIEMPO
        };

        // 2. WHERE de la consulta
        String seleccion = TablaPruebaCabecera.NOMBRE_COLUMNA_ID_ESTUDIANTE+ " = ?";

        // 3.VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(idEstudiante)};

        Cursor result = bd.query(
                TablaPruebaCabecera.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        while(result.moveToNext()) {
            PruebaCabecera pruebaCabecera = new PruebaCabecera(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getInt(2)
                    , Timestamp.valueOf(result.getString(3))
            );
            pruebaCabeceras.add(pruebaCabecera);
        }
        bdHelper.close();
        return pruebaCabeceras;
    }

    public static PruebaCabecera obtenerPruebaPorId(int idPruebaCabecera, Context contexto) {
        PruebaCabecera pruebaCabecera = null;
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaPruebaCabecera.NOMBRE_COLUMNA_ID,
                TablaPruebaCabecera.NOMBRE_COLUMNA_ID_ESTUDIANTE,
                TablaPruebaCabecera.NOMBRE_COLUMNA_ID_CATEGORIA,
                TablaPruebaCabecera.NOMBRE_COLUMNA_TIEMPO
        };

        // 2. WHERE de la consulta
        String seleccion = TablaPruebaCabecera.NOMBRE_COLUMNA_ID+ " = ?";

        // 3.VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(idPruebaCabecera)};

        Cursor result = bd.query(
                TablaPruebaCabecera.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        if(result.moveToNext()) {
            pruebaCabecera = new PruebaCabecera(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getInt(2)
                    , Timestamp.valueOf(result.getString(3))
            );
        }
        bdHelper.close();
        return pruebaCabecera;
    }
}
