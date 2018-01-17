package com.example.ramiro.autoescuela.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.datos.Estudiante;

import java.util.ArrayList;

/**
 * Created by Ramiro on 18/11/2017.
 */

public class EstudianteAdaptador2 extends BaseAdapter {

    private final Activity actividad;
    private final ArrayList<Estudiante> lista;

    public EstudianteAdaptador2(Activity actividad, ArrayList<Estudiante> lista){
        super();
        this.actividad = actividad;
        this.lista = lista;
    }

    @Override
    public View getView(int posicion, View vista, ViewGroup padre) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.elemento_student, null, true);

        TextView nombreElemento = (TextView) view.findViewById(R.id.name_element_student);
        nombreElemento.setText(lista.get(posicion).getNombres()+" "+lista.get(posicion).getApellidos());

        TextView cantIntegrantes = (TextView) view.findViewById(R.id.PIN_element_student);
        cantIntegrantes.setText(""+lista.get(posicion).getCi());
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
        Estudiante unEstudiante = lista.get(arg0);
        return unEstudiante.getId();

    }

    public boolean eliminarElemento(int posicion){
        boolean eliminada = false;

        Estudiante unEstudiante = lista.remove(posicion);
        if(unEstudiante != null){
            eliminada = true;
        }

        return eliminada;
    }

}
