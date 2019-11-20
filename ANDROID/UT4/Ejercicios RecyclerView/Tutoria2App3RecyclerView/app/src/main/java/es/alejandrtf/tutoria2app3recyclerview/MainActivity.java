package es.alejandrtf.tutoria2app3recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rvListaEquipos;
    private AdaptadorEquipos adaptadorEquipos;
    public static ArrayList<Equipo> listaEquipos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarDatos();

        //obtengo la referencia a la lista
        rvListaEquipos = findViewById(R.id.listaEquipos);
        //creo el adaptador
        adaptadorEquipos = new AdaptadorEquipos(listaEquipos);
        // le asigno el escuchador de clicks en los items de la lista al adaptador
        adaptadorEquipos.setOnItemClickListener(new AdaptadorEquipos.OnItemClickListener() {
            @Override
            public void onItemClick(int posicion) {
                //Este código se ejecutará cuando se pulse click en un elemento de la lista.
                //Programo que lance la nueva activiy sabiendo la posición que fue pulsada
                lanzarActivityVerEquipo(posicion);
            }
        });
        //asigno el adaptador
        rvListaEquipos.setAdapter(adaptadorEquipos);
        //especifico la distribución o diseño de la lista: lista, grid,...
        rvListaEquipos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }


    /**
     * Método que lanza la activity que muestra los datos del equipo de fútbol cuya posición se indica
     *
     * @param pos posición de ese equipo de fútbol en la lista
     */
    private void lanzarActivityVerEquipo(int pos) {
        Intent i = new Intent(this, VerEquipoActivity.class);
        i.putExtra("posicion", pos);
        startActivity(i);
    }


    /** Método que coge los datos del array de recursos xml y rellena la lista de equipos
     *  con esos datos.
     */
    private void cargarDatos() {
        String[] nombres = getResources().getStringArray(R.array.nombre_equipo);
        int[] puntos = getResources().getIntArray(R.array.puntos_equipo);

        TypedArray objetos = getResources().obtainTypedArray(R.array.escudo_equipo);
        Drawable[] imagenes = new Drawable[objetos.length()];
        for (int i = 0; i < objetos.length(); i++) {
            imagenes[i] = objetos.getDrawable(i);
        }

        for (int i = 0; i < nombres.length; i++) {
            listaEquipos.add(new Equipo(nombres[i], imagenes[i], puntos[i]));
        }
    }

}


