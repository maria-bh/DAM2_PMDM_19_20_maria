package es.alejandrtf.tutoria2app2recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rvListaEquipos;
    private AdaptadorEquipos adaptadorEquipos;
    private String[] arrayDatosEquipos={"Barcelona","Real Madrid","Atlético", "Valencia","Sevilla", "Málaga","Celta","Villarreal", "Athletic","Getafe","Rayo","Éibar","Levante","Espanyol", "Granada","R.Sociedad","Almería","Deportivo", "Elche","Córdoba" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtengo la referencia a la lista
        rvListaEquipos=findViewById(R.id.listaEquipos);
        //creo el adaptador
        adaptadorEquipos=new AdaptadorEquipos(arrayDatosEquipos);
        //asigno el adaptador
        rvListaEquipos.setAdapter(adaptadorEquipos);
        //especifico la distribución o diseño de la lista: lista, grid,...
        rvListaEquipos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }
}
