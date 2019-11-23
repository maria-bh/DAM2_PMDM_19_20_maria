package es.alejandrtf.ejemplofragmentslistafutbol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import es.alejandrtf.ejemplofragmentslistafutbol.datos.Equipos;

public class MainActivity extends AppCompatActivity implements ListaEquipoFragment.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarDatos();
    }

    @Override
    public void onItemClick(Equipos.Equipo item) {
       //  Toast.makeText(this, item.getNombreEquipo(), Toast.LENGTH_SHORT).show();

        VerEquipoFragment verEquipoFragment= (VerEquipoFragment) getSupportFragmentManager().findFragmentById(R.id.verEquipoFragment);
        verEquipoFragment.mostrarEquipo(item);
    }

    /** Método que coge los datos del array de recursos xml y rellena la lista de equipos
     *  con esos datos.
     */
    private void cargarDatos() {
        Equipos.ITEMS.clear();
        String[] nombres = getResources().getStringArray(R.array.nombre_equipo);
        int[] puntos = getResources().getIntArray(R.array.puntos_equipo);

        TypedArray objetos = getResources().obtainTypedArray(R.array.escudo_equipo);
        Drawable[] imagenes = new Drawable[objetos.length()];
        for (int i = 0; i < objetos.length(); i++) {
            imagenes[i] = objetos.getDrawable(i);
        }

        for (int i = 0; i < nombres.length; i++) {
           Equipos.addEquipo(new Equipos.Equipo(nombres[i], imagenes[i], puntos[i]));
        }
    }
}
