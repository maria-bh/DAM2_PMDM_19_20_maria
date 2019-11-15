package es.alejandrtf.tutoria2app2recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class VerEquipoActivity extends AppCompatActivity {
    private TextView tvNombre;
    private String nombreEquipo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_equipo);

        initReferences();

        if(getIntent().hasExtra("NombreEquipo"))
            nombreEquipo=getIntent().getStringExtra("NombreEquipo");
        tvNombre.setText(nombreEquipo);
    }

    /**Método que asigna las referencias al XML
     *
     */
    private void initReferences() {
        tvNombre=findViewById(R.id.tvNombreEquipo);
    }
}
