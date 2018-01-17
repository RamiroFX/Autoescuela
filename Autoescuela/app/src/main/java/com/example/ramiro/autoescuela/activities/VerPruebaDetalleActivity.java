package com.example.ramiro.autoescuela.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.adaptadores.PruebaCabeceraAdaptador;
import com.example.ramiro.autoescuela.adaptadores.PruebaDetalleAdaptador;
import com.example.ramiro.autoescuela.datos.Estudiante;
import com.example.ramiro.autoescuela.datos.PruebaCabecera;
import com.example.ramiro.autoescuela.datos.PruebaCategoria;
import com.example.ramiro.autoescuela.datos.PruebaDetalle;
import com.example.ramiro.autoescuela.datos.Respuesta;

import java.util.ArrayList;

public class VerPruebaDetalleActivity extends AppCompatActivity {
    private ListView listaPruebas;
    private TextView tvPruebaCategoria, tvPruebaCategoriaSubTitulo;
    private int idPruebaCabecera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_prueba_detalle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idPruebaCabecera = extras.getInt("idPruebaCabecera", -1);
            if (idPruebaCabecera < 0) {
                this.finish();
            }
        }
        PruebaCabecera pruebaCabecera = PruebaCabecera.obtenerPruebaPorId(idPruebaCabecera, this);
        PruebaCategoria pruebaCategoria = PruebaCategoria.obtenerPruebaCategoriaPorId(pruebaCabecera.getIdCategoria(), this);
        tvPruebaCategoria = (TextView) findViewById(R.id.tvPruebaCategoria);
        tvPruebaCategoria.setText(pruebaCategoria.getDescripcion());
        tvPruebaCategoriaSubTitulo = (TextView) findViewById(R.id.tvPruebaCategoriaSubTitulo);
        ArrayList<PruebaDetalle> pruebaDetalles = PruebaDetalle.obtenerPruebaDetallePorIdPruebaCabecera(idPruebaCabecera, this);
        double totalScore = pruebaDetalles.size();
        double obtainedScore = totalScore;
        for (int i = 0; i < pruebaDetalles.size(); i++) {
            Respuesta respuesta = Respuesta.obtenerRespuestaPorId(pruebaDetalles.get(i).getIdRespuesta(), this);
            if (respuesta.isEsCorrecta()) {
                obtainedScore--;
            }
        }
        float porcentage = (float) ((obtainedScore * 100) / totalScore);
        tvPruebaCategoriaSubTitulo.append(" "+porcentage + "%");

        listaPruebas = (ListView) findViewById(R.id.lvListaPruebaDetalle);
        listaPruebas.setAdapter(new PruebaDetalleAdaptador(this, pruebaDetalles));
    }
}
