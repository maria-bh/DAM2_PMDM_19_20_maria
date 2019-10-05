package es.alejandrtf.primeraaplicacion2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    private EditText etNombreJava;
    private Button btOcultarMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReferenciasViewsFromXML();
    }


    /**
     * Inicializa los objetos Java de tipo View con sus correspondidentes Views
     * en XML
     */
    private void initReferenciasViewsFromXML() {
        etNombreJava = findViewById(R.id.etNombre);
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

    /**
     * Método que se ejecuta al pulsar el botón SALUDAR
     *
     * @param view Botón pulsado
     */
    public void saludar(View view) {
        String texto = etNombreJava.getText().toString();
        Toast.makeText(MainActivity.this, "Hola " + texto, Toast.LENGTH_SHORT).show();
    }


    /**
     * Método que se ejecuta al pulsar el botón OCULTAR
     *
     * @param view Botón pulsado
     */
    public void ocultarMostrar(View view) {
        if (etNombreJava.getVisibility() == VISIBLE) {
            etNombreJava.setVisibility(View.INVISIBLE);
            btOcultarMostrar.setText("MOSTRAR");
        } else {
            etNombreJava.setVisibility(VISIBLE);
            btOcultarMostrar.setText("OCULTAR");
        }

    }


    /**
     * Método que se ejecuta al pulsar el botón SALUDAR EN OTRA PANTALLA
     *
     * @param view Botón pulsado
     */
    public void saludarOtraPantalla(View view) {
        Intent i = new Intent(this, PantallaSaludo.class);
        i.putExtra("nombre", etNombreJava.getText().toString());
        startActivity(i);

    }
}
