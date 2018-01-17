package com.example.ramiro.autoescuela.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.datos.Estudiante;

import java.util.ArrayList;

/**
 * Created by Ramiro on 31/7/2017.
 */

public class EstudianteAdaptador extends RecyclerView.Adapter<EstudianteAdaptador.EstudianteViewHolder> {

    ArrayList<Estudiante> estudianteList;

    public EstudianteAdaptador(Context context) {
        this.estudianteList = Estudiante.getEstudiantes(context);
    }

    @Override
    public EstudianteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_student, parent, false);

        EstudianteViewHolder estudianteViewHolder = new EstudianteViewHolder(itemView);

        return estudianteViewHolder;
    }

    @Override
    public void onBindViewHolder(EstudianteViewHolder holder, int position) {
        Estudiante item = estudianteList.get(position);
        holder.bindEstudiante(item);
    }

    @Override
    public int getItemCount() {
        return estudianteList.size();
    }

    public static class EstudianteViewHolder
            extends RecyclerView.ViewHolder {

        private TextView tvNombreEstudiante;
        private TextView tvPINEstudiante;

        public EstudianteViewHolder(View itemView) {
            super(itemView);

            tvNombreEstudiante = (TextView)itemView.findViewById(R.id.name_element_student);
            tvPINEstudiante = (TextView)itemView.findViewById(R.id.PIN_element_student);
        }

        public void bindEstudiante(Estudiante estudiante) {
            tvNombreEstudiante.setText(estudiante.getNombres()+" "+estudiante.getApellidos());
            tvPINEstudiante.setText(""+estudiante.getCi());
        }
    }
}
