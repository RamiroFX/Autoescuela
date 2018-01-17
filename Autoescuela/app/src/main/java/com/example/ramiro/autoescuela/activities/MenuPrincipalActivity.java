package com.example.ramiro.autoescuela.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ramiro.autoescuela.R;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void lanzarRegistrarEstudiante(View view){
        Intent i = new Intent(this, RegistrarEstudianteActivity.class);
        startActivity(i);
    }

    public void lanzarAdministrarApp(){
        Intent i = new Intent(this, ContenedorAdministrarAppActivity.class);
        startActivity(i);
    }

    public void lanzarPrueba(View view){
        Intent i = new Intent(this, SeleccionarEstudianteActivity.class);
        startActivity(i);
    }

    private void mostrarMensaje(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch( item.getItemId() ) {
            case R.id.action_administrate:{
                lanzarAdministrarApp();
                break;
            }
        }
        return true;
    }
}
