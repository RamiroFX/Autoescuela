package com.example.ramiro.autoescuela.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.datos.Estudiante;

public class SeleccionarEstudianteActivity extends AppCompatActivity {

    private static final String PIN_ERROR = "Hubo un problema al guardar el CI. Asegúrese de introducir solo números enteros y positivos.";
    private EditText etCedula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_estudiante);
        etCedula = (EditText) findViewById(R.id.etCedula);
    }


    public void seleccionarEstudiante(View view){
        int cedula;
        try {
            cedula = Integer.valueOf(etCedula.getText().toString());
        } catch (Exception ex) {
            mostrarMensaje(PIN_ERROR);
            return;
        }
        if(cedula<1){
            mostrarMensaje(PIN_ERROR);
            return;
        }
        Estudiante estudianteByPIN = Estudiante.getEstudianteByPIN(cedula, this);
        if (estudianteByPIN != null) {
            mostrarMensaje("Estudiante encontrado");
            Intent i = new Intent( SeleccionarEstudianteActivity.this, SeleccionarPruebaActivity.class);
            i.putExtra("idEstudiante", estudianteByPIN.getId());
            startActivity( i );
        }else {
            mostrarMensaje("Estudiante no registrado");
            Intent i = new Intent(SeleccionarEstudianteActivity.this, RegistrarEstudianteActivity.class);
            i.putExtra("cedula",cedula);
            startActivity(i);
        }
    }

    private void mostrarMensaje(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
