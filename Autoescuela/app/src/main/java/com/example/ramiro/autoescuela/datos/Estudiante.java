package com.example.ramiro.autoescuela.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ramiro.autoescuela.bd.BDHelper;
import com.example.ramiro.autoescuela.bd.AutoescuelaContract.TablaEstudiante;

import java.util.ArrayList;

/**
 * Created by Ramiro on 29/7/2017.
 */

public class Estudiante {

    private int id, ci, edad;
    private String nombres, apellidos, foto;

    public Estudiante() {

    }

    public Estudiante(int id, int ci, int edad, String nombres, String apellidos, String foto) {
        this.id = id;
        this.ci = ci;
        this.edad = edad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", ci=" + ci +
                ", edad=" + edad +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }

    public static void saveStudent(Estudiante estudiante, Context contexto) {

        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TablaEstudiante.
                        NOMBRE_COLUMNA_NOMBRES,
                estudiante.getNombres());

        values.put(TablaEstudiante.
                        NOMBRE_COLUMNA_APELLIDOS,
                estudiante.getApellidos());

        values.put(TablaEstudiante.
                        NOMBRE_COLUMNA_AGE,
                estudiante.getEdad());

        values.put(TablaEstudiante.
                        NOMBRE_COLUMNA_PIN,
                estudiante.getCi());

        values.put(TablaEstudiante.
                        NOMBRE_COLUMNA_PIC,
                estudiante.getFoto());

        bd.insert(TablaEstudiante.NOMBRE_TABLA, null, values);
        bdHelper.close();
    }

    public static Estudiante getEstudianteByPIN(int PIN, Context contexto) {
        Estudiante unEstudiante = null;
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaEstudiante.NOMBRE_COLUMNA_ID,
                TablaEstudiante.NOMBRE_COLUMNA_PIN,
                TablaEstudiante.NOMBRE_COLUMNA_AGE,
                TablaEstudiante.NOMBRE_COLUMNA_NOMBRES,
                TablaEstudiante.NOMBRE_COLUMNA_APELLIDOS,
                TablaEstudiante.NOMBRE_COLUMNA_PIC
        };

        // 2. WHERE de la consulta
        String seleccion = TablaEstudiante.NOMBRE_COLUMNA_PIN + " = ?";

        // 3. VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(PIN)};

        Cursor result = bd.query(
                TablaEstudiante.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        if (result.moveToNext()) {
            unEstudiante = new Estudiante(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getInt(2)
                    , result.getString(3)
                    , result.getString(4)
                    , result.getString(5)
            );
        }
        bdHelper.close();
        return unEstudiante;
    }

    public static Estudiante getEstudianteById(int id, Context contexto) {
        Estudiante unEstudiante = null;
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaEstudiante.NOMBRE_COLUMNA_ID,
                TablaEstudiante.NOMBRE_COLUMNA_PIN,
                TablaEstudiante.NOMBRE_COLUMNA_AGE,
                TablaEstudiante.NOMBRE_COLUMNA_NOMBRES,
                TablaEstudiante.NOMBRE_COLUMNA_APELLIDOS,
                TablaEstudiante.NOMBRE_COLUMNA_PIC
        };

        // 2. WHERE de la consulta
        String seleccion = TablaEstudiante.NOMBRE_COLUMNA_ID+ " = ?";

        // 3. VALORES DEL WHERE de la consulta
        String[] argumentosSeleccion = {String.valueOf(id)};

        Cursor result = bd.query(
                TablaEstudiante.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                seleccion,                      // Columnas para la clausula WHERE
                argumentosSeleccion,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        if (result.moveToNext()) {
            unEstudiante = new Estudiante(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getInt(2)
                    , result.getString(3)
                    , result.getString(4)
                    , result.getString(5)
            );
        }
        bdHelper.close();
        return unEstudiante;
    }

    public static ArrayList getEstudiantes(Context contexto) {
        ArrayList<Estudiante> estudianteArrayList = new ArrayList<>();
        BDHelper bdHelper = new BDHelper(contexto);
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // 1. COLUMNAS a traer
        String[] proyeccion = {
                TablaEstudiante.NOMBRE_COLUMNA_ID,
                TablaEstudiante.NOMBRE_COLUMNA_PIN,
                TablaEstudiante.NOMBRE_COLUMNA_AGE,
                TablaEstudiante.NOMBRE_COLUMNA_NOMBRES,
                TablaEstudiante.NOMBRE_COLUMNA_APELLIDOS,
                TablaEstudiante.NOMBRE_COLUMNA_PIC
        };

        // 2. WHERE de la consulta
        String seleccion = TablaEstudiante.NOMBRE_COLUMNA_ID+ " = ?";

        Cursor result = bd.query(
                TablaEstudiante.NOMBRE_TABLA,      // La tabla a consultar
                proyeccion,                     // Las columnas a retornar
                null,                      // Columnas para la clausula WHERE
                null,            // Valores para la clausula WHERE
                null,                           // Agrupacion de las filas
                null,                           // Filtro de la agrupacion de filas
                null                            // La ordenacion del resultado
        );

        while(result.moveToNext()) {
            Estudiante unEstudiante = new Estudiante(
                    result.getInt(0)
                    , result.getInt(1)
                    , result.getInt(2)
                    , result.getString(3)
                    , result.getString(4)
                    , result.getString(5)
            );
            estudianteArrayList.add(unEstudiante);
        }
        bdHelper.close();
        return estudianteArrayList;
    }
}
