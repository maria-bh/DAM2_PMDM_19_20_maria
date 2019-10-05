package es.alejandrtf.primeraaplicacion2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PantallaSaludo extends AppCompatActivity {
    private TextView textoMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_saludo);


        String nombreUser=getIntent().getStringExtra("nombre");
        ((TextView)findViewById(R.id.tvMensaje)).setText("Hola"+nombreUser);

    }
}
