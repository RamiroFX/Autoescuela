package com.example.ramiro.autoescuela.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.activities.VerPruebaActivity;
import com.example.ramiro.autoescuela.datos.Estudiante;

/**
 * Created by Ramiro on 26/8/2017.
 */

public class VerDatosEstudiantesFragment extends Fragment {

    int idEstudiante;

    public VerDatosEstudiantesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ver_datos_estudiantes, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Button button = (Button) getView().findViewById(R.id.btnViewTest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarVerPruebas();
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    private void setPic(ImageView ivFoto, String mCurrentPhotoPath) {
        // Get the dimensions of the View
        int targetW = ivFoto.getWidth();
        int targetH = ivFoto.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        ivFoto.setImageBitmap(bitmap);
    }

    public void completarDatosEstudiantes(Estudiante unEstudiante) {
        idEstudiante = unEstudiante.getId();
        //TODO implementar mecanismo en caso de que se elimine la foto externamente
        String ubicacionFoto = unEstudiante.getFoto();
        ImageView vistaFoto = (ImageView) getView().findViewById(R.id.ivStudentPicture);
        setPic(vistaFoto, ubicacionFoto);

        String nombres = "   " + unEstudiante.getNombres();
        TextView vistaTelefono = (TextView) getView().findViewById(R.id.etNombres);
        vistaTelefono.setText(nombres);

        String apellidos = "   " + unEstudiante.getApellidos();
        TextView vistaDireccion = (TextView) getView().findViewById(R.id.etApellidos);
        vistaDireccion.setText(apellidos);

        String cedula = "   " + unEstudiante.getCi();
        TextView vistaCedula = (TextView) getView().findViewById(R.id.etPIN);
        vistaCedula.setText(cedula);

        String edad = "   " + unEstudiante.getEdad();
        TextView vistaEdad = (TextView) getView().findViewById(R.id.etAge);
        vistaEdad.setText(edad);
    }

    public void lanzarVerPruebas() {
        if (idEstudiante > 0) {
            Intent i = new Intent(getActivity(), VerPruebaActivity.class);
            i.putExtra("idEstudiante", idEstudiante);
            startActivity(i);
        }
    }
}
