package es.alejandrtf.primeraaplicacin2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private EditText etNombreJava;
    private Button btSaludarJava, btOcultarMostrar;
    private View.OnClickListener miListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNombreJava = findViewById(R.id.etNombre);
        btSaludarJava = findViewById(R.id.btSaludar);
        btOcultarMostrar = findViewById(R.id.btOcultar);
   /*
    FORMA 1:

    btSaludarJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        })*/

        // FORMA 2:
       /* miListener=new OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto=etNombreJava.getText().toString();
                Toast.makeText(MainActivity.this, "Hola "+texto, Toast.LENGTH_SHORT).show();

            }
        };*/
        // btSaludarJava.setOnClickListener(miListener);
        // FIN FORMA 2

        // usando interface
        // btSaludarJava.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
      /*  if(view.getId()==R.id.btSaludar) {
            String texto = etNombreJava.getText().toString();
            Toast.makeText(MainActivity.this, "Hola " + texto, Toast.LENGTH_SHORT).show();
        }*/
    }


    public void saludar(View v) {
        String texto = etNombreJava.getText().toString();
        Toast.makeText(MainActivity.this, "Hola " + texto, Toast.LENGTH_SHORT).show();

    }


    public void ocultarMostrar(View view) {
        if (etNombreJava.getVisibility() == VISIBLE) {
            etNombreJava.setVisibility(View.INVISIBLE);
            btOcultarMostrar.setText("MOSTRAR");
        } else {
            etNombreJava.setVisibility(VISIBLE);
            btOcultarMostrar.setText("OCULTAR");
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("visibilidad", etNombreJava.getVisibility());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int visibilidad = (int) savedInstanceState.get("visibilidad");
        etNombreJava.setVisibility(visibilidad);
        btOcultarMostrar.setText((visibilidad == VISIBLE) ? "Ocultar" : "Mostrar");


    }

    public void saludarOtraPantalla(View view) {
        Intent i=new Intent(this,PantallaSaludo.class);
        i.putExtra("nombre",etNombreJava.getText().toString());
        startActivity(i);
    }
}
