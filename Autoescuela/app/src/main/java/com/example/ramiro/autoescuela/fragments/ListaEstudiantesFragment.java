package com.example.ramiro.autoescuela.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.adaptadores.EstudianteAdaptador2;
import com.example.ramiro.autoescuela.datos.Estudiante;

public class ListaEstudiantesFragment extends Fragment implements OnItemClickListener{
    private long id;
    private ListView listEstudiantes;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup contenedor,
                             Bundle savedInstanceState) {

        View vista = inflador.inflate(R.layout.activity_estudiantes, contenedor, false);
        setHasOptionsMenu(true);
        return vista;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        ArrayList<Estudiante> misEstudiantes = Estudiante.getEstudiantes(getContext());
        Log.d("TAG", "CantidadEstudiantes: "+misEstudiantes.size());
        listEstudiantes = (ListView) getView().findViewById(R.id.list_estudiantes);
        listEstudiantes.setAdapter(new EstudianteAdaptador2(getActivity(), misEstudiantes));
        listEstudiantes.setOnItemClickListener(this);
    }

    public ListView getListEstudiantes() {
        return listEstudiantes;
    }

    public void setListEstudiantes(ListView listEstudiantes) {
        this.listEstudiantes = listEstudiantes;
    }
    @Override
    public void onItemClick(AdapterView<?> arg0, View vista, int posicion, long id) {
        EstudianteAdaptador2 adaptador = (EstudianteAdaptador2) listEstudiantes.getAdapter();
        Estudiante estudianteElegido = (Estudiante) adaptador.getItem(posicion);
        ((AdministrarEstudiantesFragment) getParentFragment()).verDatosEstudiante(estudianteElegido);
    }

}