package es.alejandrtf.ejemploserviciosreceptoresnotificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MostrarFicheroActivity extends AppCompatActivity {
    private String[] datosFichero;
    private TextView textoFichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_fichero);

        textoFichero = findViewById(R.id.tvContenidoFichero);

        if (getIntent().hasExtra(MainActivity.ReceptorAvisosServicio.EXTRA_KEY_DATOS_FICHERO)) {
            datosFichero = getIntent().getStringArrayExtra(MainActivity.ReceptorAvisosServicio.EXTRA_KEY_DATOS_FICHERO);
        }

        if (datosFichero != null && datosFichero.length > 0) {
            for (String linea :
                    datosFichero) {
                String[] lineaTroceada = linea.split(",");
                textoFichero.append(String.format("%-16s", lineaTroceada[0]) +
                        String.format("%-28s", lineaTroceada[1]) +
                        String.format("%-15s", lineaTroceada[2]) +
                        String.format("%10s", lineaTroceada[3]) + "\n"); // %-10s el "-" indica que rellene por la derecha espacios blancos
            }

        }

    }
}
