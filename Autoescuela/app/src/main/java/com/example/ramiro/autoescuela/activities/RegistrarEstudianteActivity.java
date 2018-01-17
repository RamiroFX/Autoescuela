package com.example.ramiro.autoescuela.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ramiro.autoescuela.R;
import com.example.ramiro.autoescuela.datos.Estudiante;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarEstudianteActivity extends AppCompatActivity {

    private static final String TAG = "RegistrarEstudiante";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_TAKE_PHOTO = 2;
    private String mCurrentPhotoPath;

    private static final String PIN_ERROR = "Hubo un problema al guardar el CI. Asegúrese de introducir solo números enteros y positivos.";
    private static final String AGE_ERROR = "Hubo un problema al guardar la edad. Asegúrese de introducir solo números enteros.";
    private static final String PIC_ERROR = "Debe tomar una foto del estudiante para continuar.";
    private static final String PIN_IN_USE = "El CI ingresado ya se encuentra en uso.";
    private static final String STUDENT_SAVED = "El estudiante se registró con éxito.";
    private static final String PHOTO_IMAGE_FILE_ERROR = "Hubo un error al crear la foto.";
    private EditText etNombres, etApellidos, etCedula, etEdad;
    private Bitmap bitmap;
    public ImageView ivFoto;
    int cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_estudiante);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            cedula = extras.getInt("cedula", -1);
        }
        ivFoto = (ImageView) findViewById(R.id.ivStudentPicture);
        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etCedula = (EditText) findViewById(R.id.etPIN);
        etEdad = (EditText) findViewById(R.id.etAge);
        if(cedula>0){
            etCedula.setText(cedula+"");
        }
    }

    public void crearEstudiante(View view) {
        String nombre = etNombres.getText().toString();
        String apellido = etApellidos.getText().toString();
        String foto = mCurrentPhotoPath;
        int cedula = 0;
        int age = 0;
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
            mostrarMensaje(PIN_IN_USE);
            return;
        }
        try {
            age = Integer.valueOf(etEdad.getText().toString());
        } catch (Exception ex) {
            mostrarMensaje(AGE_ERROR);
            return;
        }
        if(foto==null || foto.isEmpty()){
            mostrarMensaje(PIC_ERROR);
            return;
        }
        Estudiante estudiante = new Estudiante(0, cedula, age, nombre, apellido,foto);
        Estudiante.saveStudent(estudiante, this);
        mostrarMensaje(STUDENT_SAVED);
        super.finish();
    }

    public void lanzarActividadTomarFoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                mostrarMensaje(PHOTO_IMAGE_FILE_ERROR);
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.ramiro.autoescuela.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void mostrarMensaje(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setPic() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();
        }
    }
}
