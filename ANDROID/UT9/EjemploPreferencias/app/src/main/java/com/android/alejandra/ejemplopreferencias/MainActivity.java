package com.android.alejandra.ejemplopreferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    //preferencias
    private boolean musica;
    private String numeroIntentos;
    private String idColor;

    //Vistas que necesito
    private TextView tvMusica, tvIntentos;
    private ConstraintLayout layoutPrincipal;
    private Button btConfigurar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReferenciasVistas();





        btConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,PreferenciasActivity.class);
                startActivity(i);
            }
        });


        //EJEMPLO DE USO DE PREFERENCIAS COMO SISTEMA DE ALMACENAMIENTO
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("jubilado", true);
        editor.putString("nombre", "Ana");
        editor.commit();
    }

    private void actualizarPantallaSegunPreferenciasElegidas() {
        tvMusica.setText((musica) ? "SÍ" : "NO");
        tvIntentos.setText(numeroIntentos);
        layoutPrincipal.setBackgroundColor(Color.parseColor(idColor));
    }


    @Override
    protected void onResume() {
        super.onResume();
        leerPreferencias();
        //MUESTRO EN LA ACTIVITY LO QUE ELIGIO EL USUARIO
        actualizarPantallaSegunPreferenciasElegidas();
    }

    private void getReferenciasVistas() {
        tvIntentos=findViewById(R.id.tvIntentos);
        tvMusica=findViewById(R.id.tvMusica);
        layoutPrincipal=findViewById(R.id.clPrincipal);
        btConfigurar=findViewById(R.id.btConfiguracion);
    }


    public void leerPreferencias() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        musica = prefs.getBoolean("key_musica", false);
        idColor = prefs.getString("key_color", "#FFFFFF");
        numeroIntentos = prefs.getString("key_numero_intentos", "10");


    }
}
