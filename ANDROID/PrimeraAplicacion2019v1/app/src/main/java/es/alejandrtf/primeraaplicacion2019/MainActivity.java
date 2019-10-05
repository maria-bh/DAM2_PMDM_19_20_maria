package es.alejandrtf.primeraaplicacion2019;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.alejandrtf.primeraaplicacion2019.R;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    private EditText etNombreJava;
    private Button btSaludarJava, btOcultarMostrar, btSaludarOtraPantalla;
    private View.OnClickListener miListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReferenciasViewsFromXML();

        // BOTÓN SALUDAR
        btSaludarJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = etNombreJava.getText().toString();
                Toast.makeText(MainActivity.this, "Hola " + texto, Toast.LENGTH_SHORT).show();

            }
        });

        // BOTÓN OCULTAR/MOSTRAR
        btOcultarMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNombreJava.getVisibility() == VISIBLE) {
                    etNombreJava.setVisibility(View.INVISIBLE);
                    btOcultarMostrar.setText("MOSTRAR");
                } else {
                    etNombreJava.setVisibility(VISIBLE);
                    btOcultarMostrar.setText("OCULTAR");
                }
            }
        });

        // BOTÓN SALUDAR EN OTRA PANTALLA
        btSaludarOtraPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, es.alejandrtf.primeraaplicacin2019.PantallaSaludo.class);
                i.putExtra("nombre", etNombreJava.getText().toString());
                startActivity(i);
            }
        });


    }


    /**
     * Inicializa los objetos Java de tipo View con sus correspondidentes Views
     * en XML
     */
    private void initReferenciasViewsFromXML() {
        etNombreJava = findViewById(R.id.etNombre);
        btSaludarJava = findViewById(R.id.btSaludar);
        btOcultarMostrar = findViewById(R.id.btOcultar);
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

}
