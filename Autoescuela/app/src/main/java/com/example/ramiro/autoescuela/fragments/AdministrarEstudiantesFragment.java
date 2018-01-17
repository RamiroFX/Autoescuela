package com.example.ramiro.autoescuela.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.datos.Estudiante;

public class AdministrarEstudiantesFragment extends Fragment {

    private VerDatosEstudiantesFragment verDatosEstudiantesFragment;

    public AdministrarEstudiantesFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        verDatosEstudiantesFragment = (VerDatosEstudiantesFragment) getChildFragmentManager().findFragmentById(R.id.ver_estudiantes_fragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_administrar_estudiantes, container, false);
    }

    public void verDatosEstudiante(Estudiante unEstudiante){
        verDatosEstudiantesFragment.completarDatosEstudiantes(unEstudiante);
    }
}
