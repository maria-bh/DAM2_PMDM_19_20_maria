package es.alejandrtf.tutoria2app3recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class VerEquipoActivity extends AppCompatActivity {
    private TextView tvNombre, tvPuntos;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_equipo);

        initReferences();

        if (getIntent().hasExtra("posicion"))
            pos = getIntent().getIntExtra("posicion", 0);
        if (pos != -1) {
            Equipo equipo = MainActivity.listaEquipos.get(pos);
            tvNombre.setText(equipo.getNombreEquipo());
            tvPuntos.setText(String.valueOf(equipo.getPuntos()));
        }
    }

    /**
     * Método que asigna las referencias al XML
     */
    private void initReferences() {
        tvNombre = findViewById(R.id.tvNombreEquipo);
        tvPuntos = findViewById(R.id.tvPuntos);
    }
}
