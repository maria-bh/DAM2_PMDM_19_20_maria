package es.alejandrtf.tutoria2app4recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class AniadirActivity extends AppCompatActivity {
    private final int REQUEST_IMAGE_CAPTURE = 1;
    protected final static String RESULTADO_INSERCION="RESULTADO_INSERCION";

    private Button btAniadirFoto, btAniadirEquipo;
    private ImageView ivEscudo;
    private CheckBox cbSinFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir);

        initReferences();

        //registro oyente al checkbox SINFOTO
        cbSinFoto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    //está marcado
                    //Desactivo botón AÑADIR FOTO
                    btAniadirFoto.setEnabled(!isChecked);
                    //cargo la foto en el ImageView del escudo
                    ivEscudo.setImageResource(R.drawable.generica);
                    ivEscudo.setTag(R.drawable.generica);
                } else {
                    //no marcado
                    //Activo botón  AÑADIR FOTO
                    btAniadirFoto.setEnabled(!isChecked);
                    //quito la foto del escudo sólo si es la genérica. Porque puede ser que
                    //haya una foto tomada con la cámara.
                    if ((int) (ivEscudo.getTag()) == R.drawable.generica) {
                        ivEscudo.setImageDrawable(null);
                    }
                }
            }
        });

        //registro oyente al botón AÑADIR FOTO
        btAniadirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto();
            }
        });

        //registro oyente al botón AÑADIR EQUIPO NUEVO
        btAniadirEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aniadirEquipo();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap;
            imageBitmap = (Bitmap) extras.get("data");
            ivEscudo.setImageBitmap(imageBitmap);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Bitmap fotoEscudo;

        super.onSaveInstanceState(outState);
        //guardo la imagen del escudo por si se destruye la activity
        if (ivEscudo.getDrawable() != null)
            fotoEscudo = ((BitmapDrawable) ivEscudo.getDrawable()).getBitmap();
        else
            fotoEscudo = null;
        outState.putParcelable("IMAGEN_ESCUDO", fotoEscudo);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ivEscudo.setImageBitmap((Bitmap) savedInstanceState.getParcelable("IMAGEN_ESCUDO"));
    }


    /**
     * Método que asigna las referencias al XML
     */
    private void initReferences() {
        btAniadirFoto = findViewById(R.id.btAniadirFoto);
        btAniadirEquipo = findViewById(R.id.btAniadirEquipo);
        ivEscudo = findViewById(R.id.ivFotoAniadir);
        cbSinFoto = findViewById(R.id.cbSinFoto);

    }


    /**
     * Método que abre la aplicación de la camara y toma una foto
     */
    private void tomarFoto() {
        Intent lanzarCamaraIntent =
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (lanzarCamaraIntent.
                resolveActivity(getPackageManager()) != null) {
            startActivityForResult(lanzarCamaraIntent,
                    REQUEST_IMAGE_CAPTURE);

        }
    }


    /**
     * Método que añade un equipo al ArrayList de equipos y devuelve el resultado de dicha operación
     * a la activity que la llamó.
     */
    private void aniadirEquipo() {
        //creo el equipo comprobando datos
        Equipo equipo = crearEquipoNuevoFromDatosActivity();
        if (equipo != null) {
            //no hubo errores, por tanto, puedo añadirlo a mi arraylist.

            //busco la posición donde debo insertar el nuevo equipo, según
            //sus puntos.  Tener en cuenta que los equipos aparecen ordenados por
            //puntos en el ArrayList
            int pos = buscarPosInArrayList(MainActivity.listaEquipos,
                    equipo.getPuntos());

            //inserto el equipo en su pos
            MainActivity.listaEquipos.add(pos, equipo);

            //Desactivo botón AÑADIR EQUIPO
            btAniadirEquipo.setEnabled(false);

            //configuro el Intent a devolver a la activity principal
            Intent iDatosDevueltos = new Intent();
            iDatosDevueltos.putExtra(RESULTADO_INSERCION, "OK");
            setResult(RESULT_OK, iDatosDevueltos);

            //cierro la activity
            finish();
        }
    }


    /**
     * Método que crea un objeto Equipo a partir de los datos introducidos en el
     * formulario de Añadir Equipo.
     * Se comprueba que el nombre no sea vacío, escudo no vacío y si los puntos están
     * en blanco se ponen a 0
     *
     * @return el nuevo Equipo o null si hubo errores
     */
    private Equipo crearEquipoNuevoFromDatosActivity() {
        Equipo equipo = null;

        EditText etNombreEquipo, etPuntosEquipo; //para el nombre equipo y puntos

        //obtengo referencias para los nuevos objetos definidos
        etNombreEquipo = findViewById(R.id.etNombreEquipoAniadir);
        etPuntosEquipo = findViewById(R.id.etPuntosEquipoAniadir);

        //obtengo los puntos en formato String comprobando que el string no sea nulo.
        //No necesito comprobar que sea numero porque lo hace Android con el
        //Edittext:inputType
        String puntos = etPuntosEquipo.getText().
                toString().isEmpty() ? "0" : etPuntosEquipo.getText().toString();

        try {
            equipo = new Equipo(etNombreEquipo.getText().toString(),
                    ivEscudo.getDrawable(), Integer.parseInt(puntos));

        } catch (IllegalArgumentException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
        }

        return (equipo);
    }


    /**Método que busca la pos adecuada para insertar un equipo en un ArrayList
     * ordenado por los puntos de los equipos
     *
     * @param array  ArrayList de Equipos donde se busca la pos
     * @param puntos  los puntos a buscar
     * @return la posición correspondiente en la lista
     */
    private int buscarPosInArrayList(ArrayList<Equipo> array, int puntos) {
        for (Equipo equipo :array) {
            if(equipo.getPuntos()<puntos){
                return(array.indexOf(equipo));
            }
        }
        return array.size();
    }


}
