package com.example.ramiro.autoescuela.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.datos.PruebaCabecera;

import java.util.ArrayList;

/**
 * Created by Ramiro on 18/11/2017.
 */

public class PruebaCabeceraAdaptador extends BaseAdapter {

    private final Activity actividad;
    private final ArrayList<PruebaCabecera> lista;

    public PruebaCabeceraAdaptador(Activity actividad, ArrayList<PruebaCabecera> lista){
        super();
        this.actividad = actividad;
        this.lista = lista;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.elemento_test, null, true);

        TextView nombreElemento = (TextView) view.findViewById(R.id.time_element_test);
        nombreElemento.setText(""+lista.get(posicion).getTiempo());

        TextView cantIntegrantes = (TextView) view.findViewById(R.id.category_element_test);
        switch (lista.get(posicion).getIdCategoria()){
            case 1:{
                cantIntegrantes.setText("Normas y legislaciones");
                break;
            }
            case 2:{
                cantIntegrantes.setText(""+lista.get(posicion).getIdCategoria());
                break;
            }
            case 3:{
                cantIntegrantes.setText(""+lista.get(posicion).getIdCategoria());
                break;
            }
            case 4:{
                cantIntegrantes.setText(""+lista.get(posicion).getIdCategoria());
                break;
            }
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
        PruebaCabecera pruebaCabecera = lista.get(arg0);
        return pruebaCabecera.getId();

    }

    public boolean eliminarElemento(int posicion){
        boolean eliminada = false;
        PruebaCabecera pruebaCabecera = lista.remove(posicion);
        if(pruebaCabecera != null){
            eliminada = true;
        }
        return eliminada;
    }

}
