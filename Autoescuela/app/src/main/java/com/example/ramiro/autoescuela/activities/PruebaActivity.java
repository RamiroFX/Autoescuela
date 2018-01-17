package com.example.ramiro.autoescuela.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.datos.Pregunta;
import com.example.ramiro.autoescuela.datos.PruebaCabecera;
import com.example.ramiro.autoescuela.datos.PruebaCategoria;
import com.example.ramiro.autoescuela.datos.PruebaDetalle;
import com.example.ramiro.autoescuela.datos.Respuesta;

import java.util.ArrayList;

public class PruebaActivity extends AppCompatActivity {
    private static final String TAG = "PruebaActivity";
    private int idPruebaCategoria = -1;
    private int idEstudiante = -1;
    private int idPosicionPregunta = 0;
    private ArrayList<Pregunta> preguntas;
    private Respuesta respuesta;
    private ArrayList<Respuesta> respuestas;
    private ArrayList<PruebaDetalle> pruebaDetalles;
    private TextView tvPruebaCategoria;
    private TextView tvPregunta;
    private RadioButton rbRespuesta1, rbRespuesta2, rbRespuesta3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idPruebaCategoria = extras.getInt("idPruebaCategoria", -1);
            idEstudiante = extras.getInt("idEstudiante", -1);
        }
        pruebaDetalles= new ArrayList<>();
        preguntas = Pregunta.obtenerPreguntasPorCategoria(idPruebaCategoria, this );
        if(savedInstanceState != null){
            int idPruebaCategoriaGuardada = savedInstanceState.getInt("idPruebaCategoria", -1);
            int idPosicionPreguntaGuardada = savedInstanceState.getInt("idPosicionPregunta", 0);
            if(idPruebaCategoriaGuardada != -1){
                idPruebaCategoria = idPruebaCategoriaGuardada;
                idPosicionPregunta = idPosicionPreguntaGuardada;
            }
        }
        actualizarVista();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onSaveInstanceState(Bundle datosAGuardar) {
        Log.i(TAG, "Se guardaran los datos para mantener el estado de la actividad. IdCategoria: "+ idPruebaCategoria);
        super.onSaveInstanceState(datosAGuardar);
        datosAGuardar.putInt("idPruebaCategoria", idPruebaCategoria);
    }

    @Override
    public void onRestoreInstanceState(Bundle datosGuardados) {
        Log.i(TAG, "Se recuperan los datos para mantener el estado de la actividad");
        super.onSaveInstanceState(datosGuardados);
        idPruebaCategoria = datosGuardados.getInt("idPruebaCategoria");
        Log.i(TAG, "idPruebaCategoria recuperado: "+ idPruebaCategoria);
    }


    public void actualizarVista(){
        if ( preguntas == null ) {
            finish();
            return;
        }
        respuestas = Respuesta.obtenerRespuestaPorIdPregunta(preguntas.get(idPosicionPregunta).getId(), this);
        tvPruebaCategoria = (TextView) findViewById(R.id.tvPruebaCategoria);
        tvPruebaCategoria.setText(PruebaCategoria.obtenerPruebaCategoriaPorId(idPruebaCategoria, this).getDescripcion());

        tvPregunta = (TextView) findViewById(R.id.tvPregunta);
        tvPregunta.setText((idPosicionPregunta+1)+"/"+preguntas.size()+". "+preguntas.get(idPosicionPregunta).getPregunta());


        rbRespuesta1= (RadioButton) findViewById(R.id.rbRespuesta1);
        rbRespuesta1.setText(respuestas.get(0).getRespuesta());
        rbRespuesta1.setSelected(false);
        rbRespuesta2= (RadioButton) findViewById(R.id.rbRespuesta2);
        rbRespuesta2.setText(respuestas.get(1).getRespuesta());
        rbRespuesta2.setSelected(false);
        rbRespuesta3= (RadioButton) findViewById(R.id.rbRespuesta3);
        rbRespuesta3.setText(respuestas.get(2).getRespuesta());
        rbRespuesta3.setSelected(false);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbRespuesta1:
                if (checked)
                    respuesta=respuestas.get(0);
                    break;
            case R.id.rbRespuesta2:
                if (checked)
                    respuesta=respuestas.get(1);
                    break;
            case R.id.rbRespuesta3:
                if (checked)
                    respuesta=respuestas.get(2);
                    break;
        }
    }

    public void siguientePregunta(View view) {
        if(respuesta==null){
            return;
        }
        PruebaDetalle pd = new PruebaDetalle();
        pd.setIdPregunta(preguntas.get(idPosicionPregunta).getId());
        pd.setIdRespuesta(respuesta.getId());
        pruebaDetalles.add(pd);
        if(idPosicionPregunta<preguntas.size()-1){
            idPosicionPregunta++;
            actualizarVista();
        }else{
            PruebaCabecera unaPruebaCabecera = new PruebaCabecera();
            unaPruebaCabecera.setIdCategoria(idPruebaCategoria);
            unaPruebaCabecera.setIdEstudiante(idEstudiante);
            PruebaCabecera.guardarPrueba(unaPruebaCabecera,pruebaDetalles,this);
            mostrarMensaje("TERMINADO");
            this.finish();
        }
    }

    private void mostrarMensaje(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
