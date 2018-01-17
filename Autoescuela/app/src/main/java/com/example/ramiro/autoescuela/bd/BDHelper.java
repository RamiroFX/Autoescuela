package com.example.ramiro.autoescuela.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ramiro.autoescuela.bd.AutoescuelaContract.*;

public class BDHelper extends SQLiteOpenHelper {

    public static final int VERSION_BD = 1;
    public static final String NOMBRE_BD = "Autoescuela.db";


    private static final String SQL_CREAR_TABLA_ESTUDIANTE =
            "CREATE TABLE " + TablaEstudiante.NOMBRE_TABLA +
                    " (" +
                    TablaEstudiante.NOMBRE_COLUMNA_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TablaEstudiante.NOMBRE_COLUMNA_NOMBRES + " TEXT NOT NULL," +
                    TablaEstudiante.NOMBRE_COLUMNA_APELLIDOS + " TEXT NOT NULL," +
                    TablaEstudiante.NOMBRE_COLUMNA_PIN        + " INTEGER NOT NULL," +
                    TablaEstudiante.NOMBRE_COLUMNA_AGE    + " INTEGER," +
                    TablaEstudiante.NOMBRE_COLUMNA_PIC    + " TEXT" +
                    " )";

    private static final String SQL_CREAR_TABLA_PRUEBA_CATEGORIA =
            "CREATE TABLE " + TablaPruebaCategoria.NOMBRE_TABLA +
                    " (" +
                    TablaPruebaCategoria.NOMBRE_COLUMNA_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TablaPruebaCategoria.NOMBRE_COLUMNA_DESCRIPCION + " TEXT NOT NULL UNIQUE" +
                    " )";

    private static final String SQL_CREAR_TABLA_PREGUNTA =
            "CREATE TABLE " + TablaPregunta.NOMBRE_TABLA +
                    " (" +
                    TablaPregunta.NOMBRE_COLUMNA_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TablaPregunta.NOMBRE_COLUMNA_ID_CATEGORIA + " INTEGER, " +
                    TablaPregunta.NOMBRE_COLUMNA_PREGUNTA + " TEXT NOT NULL UNIQUE, " +
                    " FOREIGN KEY ("+TablaPregunta.NOMBRE_COLUMNA_ID_CATEGORIA+") REFERENCES "+TablaPruebaCategoria.NOMBRE_TABLA+"("+TablaPruebaCategoria.NOMBRE_COLUMNA_ID+")" +
                    ");";

    private static final String SQL_CREAR_TABLA_RESPUESTA =
            "CREATE TABLE " + TablaRespuesta.NOMBRE_TABLA +
                    " (" +
                    TablaRespuesta.NOMBRE_COLUMNA_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TablaRespuesta.NOMBRE_COLUMNA_ID_PREGUNTA+ " INTEGER, " +
                    TablaRespuesta.NOMBRE_COLUMNA_RESPUESTA + " TEXT NOT NULL UNIQUE, " +
                    TablaRespuesta.NOMBRE_COLUMNA_ES_CORRECTA + " BOOLEAN DEFAULT 0 CHECK ("+TablaRespuesta.NOMBRE_COLUMNA_ES_CORRECTA+" IN (0,1)), " +
                    " FOREIGN KEY ("+TablaRespuesta.NOMBRE_COLUMNA_ID_PREGUNTA+") REFERENCES "+TablaPregunta.NOMBRE_TABLA+"("+TablaPregunta.NOMBRE_COLUMNA_ID+")" +
                    ");";

    private static final String SQL_CREAR_TABLA_PRUEBA_CABECERA =
            "CREATE TABLE " + TablaPruebaCabecera.NOMBRE_TABLA +
                    " (" +
                    TablaPruebaCabecera.NOMBRE_COLUMNA_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TablaPruebaCabecera.NOMBRE_COLUMNA_ID_ESTUDIANTE+ " INTEGER, " +
                    TablaPruebaCabecera.NOMBRE_COLUMNA_ID_CATEGORIA+ " INTEGER, " +
                    TablaPruebaCabecera.NOMBRE_COLUMNA_TIEMPO + " DATETIME DEFAULT (DATETIME('now','localtime')), " +
                    " FOREIGN KEY ("+TablaPruebaCabecera.NOMBRE_COLUMNA_ID_ESTUDIANTE+") REFERENCES "+TablaEstudiante.NOMBRE_TABLA+"("+TablaEstudiante.NOMBRE_COLUMNA_ID+"), " +
                    " FOREIGN KEY ("+TablaPruebaCabecera.NOMBRE_COLUMNA_ID_CATEGORIA+") REFERENCES "+TablaPruebaCategoria.NOMBRE_TABLA+"("+TablaPruebaCategoria.NOMBRE_COLUMNA_ID+") " +
                    ");";

    private static final String SQL_CREAR_TABLA_PRUEBA_DETALLE=
            "CREATE TABLE " + TablaPruebaDetalle.NOMBRE_TABLA +
                    " (" +
                    TablaPruebaDetalle.NOMBRE_COLUMNA_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TablaPruebaDetalle.NOMBRE_COLUMNA_ID_CABECERA + " INTEGER, " +
                    TablaPruebaDetalle.NOMBRE_COLUMNA_ID_PREGUNTA + " INTEGER, " +
                    TablaPruebaDetalle.NOMBRE_COLUMNA_ID_RESPUESTA + " INTEGER, " +
                    " FOREIGN KEY ("+TablaPruebaDetalle.NOMBRE_COLUMNA_ID_CABECERA+") REFERENCES "+TablaPruebaCabecera.NOMBRE_TABLA+"("+TablaPruebaCabecera.NOMBRE_COLUMNA_ID+"), " +
                    " FOREIGN KEY ("+TablaPruebaDetalle.NOMBRE_COLUMNA_ID_PREGUNTA+") REFERENCES "+TablaPregunta.NOMBRE_TABLA+"("+TablaPregunta.NOMBRE_COLUMNA_ID+"), " +
                    " FOREIGN KEY ("+TablaPruebaDetalle.NOMBRE_COLUMNA_ID_RESPUESTA+") REFERENCES "+TablaRespuesta.NOMBRE_TABLA+"("+TablaRespuesta.NOMBRE_COLUMNA_ID+") " +
                    ");";

    private static final String SQL_ELIMINAR_TABLA_ESTUDIANTE=
            "DROP TABLE IF EXISTS " + TablaEstudiante.NOMBRE_TABLA;

    public BDHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL( SQL_CREAR_TABLA_ESTUDIANTE);
        db.execSQL( SQL_CREAR_TABLA_PRUEBA_CATEGORIA);
        db.execSQL( SQL_CREAR_TABLA_PREGUNTA);
        db.execSQL( SQL_CREAR_TABLA_RESPUESTA);
        db.execSQL( SQL_CREAR_TABLA_PRUEBA_CABECERA);
        db.execSQL( SQL_CREAR_TABLA_PRUEBA_DETALLE);
        db.execSQL( AutoescuelaInserts.InsertTablaPruebaCategoria.INSERT);
        db.execSQL( AutoescuelaInserts.InsertTablaPregunta.INSERT);
        db.execSQL( AutoescuelaInserts.InsertTablaRespuesta.INSERT);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_ELIMINAR_TABLA_ESTUDIANTE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
