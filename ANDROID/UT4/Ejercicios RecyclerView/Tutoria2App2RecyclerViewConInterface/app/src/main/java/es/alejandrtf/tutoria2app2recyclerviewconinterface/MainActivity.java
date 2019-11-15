package es.alejandrtf.tutoria2app2recyclerviewconinterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity{
    private RecyclerView rvListaEquipos;
    private AdaptadorEquipos adaptadorEquipos;
    private String[] arrayDatosEquipos = {"Barcelona", "Real Madrid", "Atlético", "Valencia", "Sevilla", "Málaga", "Celta", "Villarreal", "Athletic", "Getafe", "Rayo", "Éibar", "Levante", "Espanyol", "Granada", "R.Sociedad", "Almería", "Deportivo", "Elche", "Córdoba"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtengo la referencia a la lista
        rvListaEquipos = findViewById(R.id.listaEquipos);
        //creo el adaptador
        adaptadorEquipos = new AdaptadorEquipos(arrayDatosEquipos);
        // le asigno el escuchador de clicks en los items de la lista al adaptador
        adaptadorEquipos.setOnItemClickListener(new AdaptadorEquipos.OnItemClickListener() {
            @Override
            public void onItemClick(int posicion) {
                //Este código se ejecutará cuando se pulse click en un elemento de la lista.
                //Programo que lance la nueva activiy sabiendo la posición que fue pulsada
                lanzarActivityVerEquipo(arrayDatosEquipos[posicion]);
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
     * @param nombreEquipo nombre de ese equipo de fútbol
     */
    private void lanzarActivityVerEquipo(String nombreEquipo) {
        Intent i = new Intent(this, VerEquipoActivity.class);
        i.putExtra("NombreEquipo", nombreEquipo);
        startActivity(i);
    }

}


