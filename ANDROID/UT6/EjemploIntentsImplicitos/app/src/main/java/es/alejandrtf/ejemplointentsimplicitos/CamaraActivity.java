package es.alejandrtf.ejemplointentsimplicitos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CamaraActivity extends AppCompatActivity {
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_IMAGE_CAPTURE_THUMBNAIL = 3;
    public static final int REQUEST_IMAGE_GALLERY = 2;
    public static final int SOLICITUD_PERMISO_WRITE_EXTERNAL_STORAGE = 10;

    private ImageButton ibCamara, ibGaleria, ibThumbnail;
    private ImageView ivImagen, ivThumbnail;
    private Uri fileUri; //uri para fichero que contendrá foto
    private boolean isThumbnail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        initReferences();
        setListenersBotones();
    }


    /**
     * Método obtiene referencias a objetos XML
     */
    private void initReferences() {
        ibCamara = findViewById(R.id.ivCamara);
        ibGaleria = findViewById(R.id.ivGaleria);
        ibThumbnail = findViewById(R.id.ibThumbnail);
        ivImagen = findViewById(R.id.ivImagen);
        ivThumbnail = findViewById(R.id.ivThumbnail);
    }


    /**
     * Método asigna listener botones
     */
    private void setListenersBotones() {
        ibThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isThumbnail = true;
                tomarFoto(isThumbnail);

            }
        });


        ibCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String justificacion = "Sin el permiso de escritura en memoria externa, no se pueden guardar las fotos";
                isThumbnail = false;

                //COMPRUEBO SI TENGO PERMISO PARA ALMACENAR EN MEMORIA EXTERNA LA FOTO, Y SI NO, LO PIDO
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        //lo pido
                        if (ActivityCompat.shouldShowRequestPermissionRationale(CamaraActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            new AlertDialog.Builder(CamaraActivity.this)
                                    .setTitle("Solicitud de permiso")
                                    .setMessage(justificacion)
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            ActivityCompat.requestPermissions(CamaraActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, SOLICITUD_PERMISO_WRITE_EXTERNAL_STORAGE);
                                        }
                                    })
                                    .show();
                        } else {
                            ActivityCompat.requestPermissions(CamaraActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, SOLICITUD_PERMISO_WRITE_EXTERNAL_STORAGE);
                        }

                    } else {
                        //PERMISO CONCEDIDO
                        tomarFoto(isThumbnail);
                    }
                } else {
                    //VERSIÓN ANTERIOR DE ANDROID

                    tomarFoto(isThumbnail);
                }
            }
        });


        ibGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
            }
        });
    }


    /**
     * Método que llama a la app de la cámara para tomar la foto y le pasa el fichero donde
     * debe almacenarla
     */
    private void tomarFoto(boolean isThumbnail) {
        //creo intent que lanza la cámara del dispositivo
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //compruebo que hay app de cámara en el dispositivo
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            if (!isThumbnail) {
                //Obtengo la uri del fichero donde grabaré la foto
                File ficheroFoto = createOutputPictureFile();
                if (ficheroFoto != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        //hay que usar FileProvider (file://URI da FileUriExposedException)
                        fileUri = FileProvider.getUriForFile(this,
                                "es.alejandrtf.ejemplointentsimplicitos.fileprovider",
                                ficheroFoto);
                    } else {
                        fileUri = Uri.fromFile(ficheroFoto);
                    }
                    //añado al intent esa uri del fichero
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    //lanzo el intent para que me abra la cámara
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }else {
                // isThumbnail
                //lanzo el intent para que me abra la cámara
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_THUMBNAIL);
            }
        }
    }


    /**
     * Método que crea el descriptor del fichero donde se guardará la foto
     *
     * @return el descriptor del fichero File creado
     */
    private File createOutputPictureFile() {
        //obtengo el directorio donde guardaré la foto: directorioFotosDelDispositivo/FotosAppEjemplo
        File picturesDirectory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "FotosAppEjemplo");
        if (!picturesDirectory.exists()) {
            //lo creo
            if (!picturesDirectory.mkdirs())
                return null;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File(picturesDirectory.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        return file;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {

            case SOLICITUD_PERMISO_WRITE_EXTERNAL_STORAGE:
                isThumbnail=false;
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("MIAPLI", "Permisos concedidos");
                    tomarFoto(isThumbnail);

                } else {
                    Toast.makeText(this, "Sin el permiso de grabar en memoria externa, no se pueden guardar las fotos", Toast.LENGTH_SHORT).show();
                    Log.i("MIAPLI", "Permisos denegados");

                }
                break;


        }
        return;
    }


    /**
     * Método que se ejecuta al volver de tomar la foto
     *
     * @param requestCode código de petición
     * @param resultCode  indica si fue bien o no la operación
     * @param data        los datos devueltos por la cámara
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE_THUMBNAIL && resultCode == RESULT_OK) {

            //RECOJO EL THUMBNAIL, si lo tengo
            if (data.getExtras().containsKey("data")) {
                Bitmap thumbnail = data.getParcelableExtra("data");
                ivThumbnail.setImageBitmap(thumbnail);
            }

        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            //cargo la imagen obtenida
            ivImagen.setImageURI(fileUri);
            Log.d("MIAPLI", fileUri.toString());

        } else if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            Uri imagenSeleccionadaUri = data.getData();
            try {
                // You can update this bitmap to your server
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagenSeleccionadaUri);
                ivImagen.setImageBitmap(bitmap);
            } catch (IOException e) {
                Log.e("MIAPLI", "Error recuperando la imagen de la galería", e);
            }
        }
    }


}
