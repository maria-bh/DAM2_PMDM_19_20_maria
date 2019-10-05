package es.alejandrtf.primeraaplicacion2019;

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
    private Button btSaludarJava, btOcultarMostrar, btSaludarOtraPantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReferenciasViewsFromXML();

        // BOTÓN SALUDAR
        btSaludarJava.setOnClickListener(this);
        // BOTÓN OCULTAR/MOSTRAR
        btOcultarMostrar.setOnClickListener(this);
        // BOTÓN SALUDAR EN OTRA PANTALLA
        btSaludarOtraPantalla.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSaludar:
                String texto = etNombreJava.getText().toString();
                Toast.makeText(MainActivity.this, "Hola " + texto, Toast.LENGTH_SHORT).show();
                return;
            case R.id.btOcultar:
                if (etNombreJava.getVisibility() == VISIBLE) {
                    etNombreJava.setVisibility(View.INVISIBLE);
                    btOcultarMostrar.setText("MOSTRAR");
                } else {
                    etNombreJava.setVisibility(VISIBLE);
                    btOcultarMostrar.setText("OCULTAR");
                }
                return;
            case R.id.btSaludarNuevaPantalla:
                Intent i = new Intent(this, PantallaSaludo.class);
                i.putExtra("nombre", etNombreJava.getText().toString());
                startActivity(i);
                return;
        }
    }


    /**
     * Inicializa los objetos Java de tipo View con sus correspondidentes Views
     * en XML
     */
    private void initReferenciasViewsFromXML() {
        etNombreJava = findViewById(R.id.etNombre);
        btSaludarJava = findViewById(R.id.btSaludar);
        btOcultarMostrar = findViewById(R.id.btOcultar);
        btSaludarOtraPantalla = findViewById(R.id.btSaludarNuevaPantalla);
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
