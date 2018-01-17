package com.example.ramiro.autoescuela.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ramiro.autoescuela.R;

public class SeleccionarPruebaActivity extends AppCompatActivity {

    int idEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_prueba);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idEstudiante = extras.getInt("idEstudiante", -1);
            if (idEstudiante < 0) {
                this.finish();
            }
        }
    }


    public void lanzarPruebaNormasYLegislacion(View view) {
        int id = 1;
        Intent i = new Intent(SeleccionarPruebaActivity.this, PruebaActivity.class);
        i.putExtra("idPruebaCategoria", id);
        i.putExtra("idEstudiante", idEstudiante);
        startActivity(i);
    }

    public void lanzarPruebaSenhalesTransito(View view) {
        mostrarMensaje("FALTA IMPLEMENTAR");
    }

    public void lanzarPruebaPrimerosAuxilios(View view) {
        mostrarMensaje("FALTA IMPLEMENTAR");
    }

    public void lanzarPruebaMecanica(View view) {
        mostrarMensaje("FALTA IMPLEMENTAR");
    }

    private void mostrarMensaje(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
