package es.alejandrtf.ejemplosencillofragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements PedirTextoFragment.OnFragmentInteractionListener {
    private MostrarTextoFragment mostrarTextoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(String texto) {
        mostrarTextoFragment = (MostrarTextoFragment) getSupportFragmentManager().findFragmentById(R.id.idFragmentMostrarTexto);
       if(mostrarTextoFragment!=null)
        mostrarTextoFragment.mostrarTextoEnRojo(texto);
       else{
           Intent i=new Intent(this,MostrarTextoActivity.class);
           i.putExtra("texto",texto);
           startActivity(i);
       }
    }
}
