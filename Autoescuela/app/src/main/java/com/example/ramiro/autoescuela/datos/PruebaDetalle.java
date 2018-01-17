package com.example.ramiro.autoescuela.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ramiro.autoescuela.bd.AutoescuelaContract;
import com.example.ramiro.autoescuela.bd.AutoescuelaContract.TablaPruebaDetalle;
import com.example.ramiro.autoescuela.bd.BDHelper;

import java.util.ArrayList;

/**
 * Created by Ramiro on 22/11/2017.
 */

public class PruebaDetalle {

    int id, idPruebaCabecera, idPregunta, idRespuesta;

    public PruebaDetalle() {
    }

    public PruebaDetalle(int id, int idPruebaCabecera, int idPregunta, int idRespuesta) {
        this.id = id;
        this.idPruebaCabecera = idPruebaCabecera;
        this.idPregunta = idPregunta;
        this.idRespuesta = idRespuesta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPruebaCabecera() {
        return idPruebaCabecera;
    }

    public void setIdPruebaCabecera(int idPruebaCabecera) {
        this.idPruebaCabecera = idPruebaCabecera;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    @Override
    public String toString() {
        return "PruebaDetalle{" +
                "id=" + id +
                ", idPruebaCabecera=" + idPruebaCabecera +
                ", idPregunta=" + idPregunta +
                ", idRespuesta=" + idRespuesta +
                '}';
    }

    public static ArrayList<PruebaDetalle> obtenerPruebaDetallePorIdPruebaCabecera(int idPruebaCabecera, Context contexto) {
        ArrayList<PruebaDetalle> pruebaDetalles= new ArrayList<>();
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaPruebaDetalle.NOMBRE_COLUMNA_ID,
                TablaPruebaDetalle.NOMBRE_COLUMNA_ID_CABECERA,
                TablaPruebaDetalle.NOMBRE_COLUMNA_ID_PREGUNTA,
                TablaPruebaDetalle.NOMBRE_COLUMNA_ID_RESPUESTA
        };

        // 2. WHERE de la consulta
        String seleccion = TablaPruebaDetalle.NOMBRE_COLUMNA_ID_CABECERA+ " = ?";

        // 3. VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(idPruebaCabecera)};

        Cursor result = bd.query(
                TablaPruebaDetalle.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        while (result.moveToNext()) {
            PruebaDetalle unaPruebaDetalle = new PruebaDetalle(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getInt(2)
                    , result.getInt(3)
            );
            pruebaDetalles.add(unaPruebaDetalle);
        }
        bdHelper.close();
        return pruebaDetalles;
    }
}
