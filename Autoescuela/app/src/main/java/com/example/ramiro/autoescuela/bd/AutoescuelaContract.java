package com.example.ramiro.autoescuela.bd;



public final class AutoescuelaContract {

    private AutoescuelaContract() { }

    public static class TablaEstudiante {
        public static final String NOMBRE_TABLA = "estudiante";
        public static final String NOMBRE_COLUMNA_ID = "id";
        public static final String NOMBRE_COLUMNA_NOMBRES = "nombres";
        public static final String NOMBRE_COLUMNA_APELLIDOS = "apellidos";
        public static final String NOMBRE_COLUMNA_PIN = "pin";
        public static final String NOMBRE_COLUMNA_AGE = "edad";
        public static final String NOMBRE_COLUMNA_PIC = "foto";
    }

    public static class TablaPruebaCategoria {
        public static final String NOMBRE_TABLA = "prueba_categoria";
        public static final String NOMBRE_COLUMNA_ID = "id";
        public static final String NOMBRE_COLUMNA_DESCRIPCION = "descripcion";
    }

    public static class TablaPregunta {
        public static final String NOMBRE_TABLA = "pregunta";
        public static final String NOMBRE_COLUMNA_ID = "id";
        public static final String NOMBRE_COLUMNA_ID_CATEGORIA= "id_prueba_categoria";
        public static final String NOMBRE_COLUMNA_PREGUNTA = "pregunta";
    }

    public static class TablaRespuesta {
        public static final String NOMBRE_TABLA = "respuesta";
        public static final String NOMBRE_COLUMNA_ID = "id";
        public static final String NOMBRE_COLUMNA_ID_PREGUNTA= "id_pregunta";
        public static final String NOMBRE_COLUMNA_RESPUESTA = "respuesta";
        public static final String NOMBRE_COLUMNA_ES_CORRECTA= "es_correcta";
    }

    public static class TablaPruebaCabecera {
        public static final String NOMBRE_TABLA = "prueba_cabecera";
        public static final String NOMBRE_COLUMNA_ID = "id";
        public static final String NOMBRE_COLUMNA_ID_ESTUDIANTE= "id_estudiante";
        public static final String NOMBRE_COLUMNA_ID_CATEGORIA = "id_prueba_categoria";
        public static final String NOMBRE_COLUMNA_TIEMPO= "tiempo";
    }

    public static class TablaPruebaDetalle {
        public static final String NOMBRE_TABLA = "prueba_detalle";
        public static final String NOMBRE_COLUMNA_ID = "id";
        public static final String NOMBRE_COLUMNA_ID_CABECERA= "id_prueba_cabecera";
        public static final String NOMBRE_COLUMNA_ID_PREGUNTA = "id_pregunta";
        public static final String NOMBRE_COLUMNA_ID_RESPUESTA= "id_respuesta";
    }
}
