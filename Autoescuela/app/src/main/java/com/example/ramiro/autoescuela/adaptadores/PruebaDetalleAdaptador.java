package com.example.ramiro.autoescuela.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.datos.Pregunta;
import com.example.ramiro.autoescuela.datos.PruebaDetalle;
import com.example.ramiro.autoescuela.datos.Respuesta;

import java.util.ArrayList;

/**
 * Created by Ramiro on 18/11/2017.
 */

public class PruebaDetalleAdaptador extends BaseAdapter {

    private final Activity actividad;
    private final ArrayList<PruebaDetalle> lista;

    public PruebaDetalleAdaptador(Activity actividad, ArrayList<PruebaDetalle> lista){
        super();
        this.actividad = actividad;
        this.lista = lista;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.elemento_prueba_detalle, null, true);
        int idPregunta = lista.get(posicion).getIdPregunta();
        int idRespuesta = lista.get(posicion).getIdRespuesta();
        Pregunta pregunta = Pregunta.obtenerPreguntaPorId(idPregunta, this.actividad);
        Respuesta respuesta = Respuesta.obtenerRespuestaPorId(idRespuesta, this.actividad);
        Respuesta respuestaCorrecta = Respuesta.obtenerRespuestaCorrectaPorIdPregunta(idPregunta, this.actividad);

        TextView tvPregunta = (TextView) view.findViewById(R.id.tvPregunta);
        tvPregunta.setText(pregunta.getPregunta());

        TextView etRespuestaSeleccionada = (TextView) view.findViewById(R.id.etRespuestaSeleccionada);
        etRespuestaSeleccionada.setText(respuesta.getRespuesta());
        if(respuesta.isEsCorrecta()){
            ImageView ivPreguntaResultado = (ImageView) view.findViewById(R.id.ivPreguntaResultado);
            ivPreguntaResultado.setImageResource(android.R.drawable.ic_delete);
            TextView etRespuestaCorrecta = (TextView) view.findViewById(R.id.etRespuestaCorrecta);

            etRespuestaCorrecta.setText(respuestaCorrecta.getRespuesta());
        }else{
            ImageView ivPreguntaResultado = (ImageView) view.findViewById(R.id.ivPreguntaResultado);
            ivPreguntaResultado.setImageResource(android.R.drawable.presence_online);

            TextView etRespuestaCorrectaTitle = (TextView) view.findViewById(R.id.etRespuestaCorrectaTitle);
            etRespuestaCorrectaTitle.setVisibility(View.INVISIBLE);
        }
        return view;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int arg0) {
        if(lista != null){
            return lista.get(arg0);
        }
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        PruebaDetalle pruebaDetalle = lista.get(arg0);
        return pruebaDetalle.getId();

    }

    public boolean eliminarElemento(int posicion){
        boolean eliminada = false;
        PruebaDetalle pruebaDetalle = lista.remove(posicion);
        if(pruebaDetalle != null){
            eliminada = true;
        }
        return eliminada;
    }

}
