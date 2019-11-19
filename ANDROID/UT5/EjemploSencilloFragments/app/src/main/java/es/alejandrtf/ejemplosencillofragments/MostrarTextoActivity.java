package es.alejandrtf.ejemplosencillofragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MostrarTextoActivity extends AppCompatActivity {
    String textoRecibido;
    MostrarTextoFragment mostrarTextoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_texto);

        textoRecibido=getIntent().getStringExtra("texto");
        mostrarTextoFragment= (MostrarTextoFragment) getSupportFragmentManager().findFragmentById(R.id.idFragmentMostrar);
        mostrarTextoFragment.mostrarTextoEnRojo(textoRecibido);
    }
}
