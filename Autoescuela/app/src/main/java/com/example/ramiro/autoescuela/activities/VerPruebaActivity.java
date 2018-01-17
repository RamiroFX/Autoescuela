package com.example.ramiro.autoescuela.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.adaptadores.PruebaCabeceraAdaptador;
import com.example.ramiro.autoescuela.datos.Estudiante;
import com.example.ramiro.autoescuela.datos.PruebaCabecera;

import java.util.ArrayList;

public class VerPruebaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listaPruebas;
    private TextView tvDatosEstudiante;
    private int idEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_prueba);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idEstudiante = extras.getInt("idEstudiante", -1);
            if (idEstudiante < 0) {
                this.finish();
            }
        }
        Estudiante unEstudiante = Estudiante.getEstudianteById(idEstudiante, this);
        String estudiante = " " + unEstudiante.getNombres() + " " + unEstudiante.getApellidos();
        tvDatosEstudiante = (TextView) findViewById(R.id.tvDatosEstudiante);
        tvDatosEstudiante.setText(estudiante);
        ArrayList<PruebaCabecera> pruebas = PruebaCabecera.obtenerPruebasPorEstudiante(idEstudiante, this);
        listaPruebas = (ListView) findViewById(R.id.lvListaPruebas);
        listaPruebas.setAdapter(new PruebaCabeceraAdaptador(this, pruebas));
        listaPruebas.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PruebaCabeceraAdaptador adaptador = (PruebaCabeceraAdaptador) listaPruebas.getAdapter();
        PruebaCabecera pruebaCabeceraElegida = (PruebaCabecera) adaptador.getItem(position);
        int idPruebaCabecera= pruebaCabeceraElegida.getId();
        Intent i = new Intent(VerPruebaActivity.this, VerPruebaDetalleActivity.class);
        i.putExtra("idPruebaCabecera", idPruebaCabecera);
        startActivity(i);
    }
}
