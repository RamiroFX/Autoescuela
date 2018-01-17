package com.example.ramiro.autoescuela.adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.bd.AutoescuelaContract.TablaEstudiante;
import com.example.ramiro.autoescuela.datos.Estudiante;

/**
 * Created by Ramiro on 26/8/2017.
 */

public class EstudianteCursorRecyclerViewAdapter extends CursorRecyclerViewAdapter<EstudianteAdaptador.EstudianteViewHolder> {


    public EstudianteCursorRecyclerViewAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(EstudianteAdaptador.EstudianteViewHolder viewHolder, Cursor cursor) {
        Estudiante item = new Estudiante();
        item.setId(cursor.getInt(1));
        item.setNombres(cursor.getString(2));
        item.setApellidos(cursor.getString(3));
        item.setCi(cursor.getInt(4));
        item.setEdad(cursor.getInt(5));
        item.setFoto(cursor.getString(6));
        viewHolder.bindEstudiante(item);
    }


    @Override
    public EstudianteAdaptador.EstudianteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_student, parent, false);

        EstudianteAdaptador.EstudianteViewHolder estudianteViewHolder = new EstudianteAdaptador.EstudianteViewHolder(itemView);

        return estudianteViewHolder;
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
