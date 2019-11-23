package es.alejandrtf.tutoria2app4recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rvListaEquipos;
    private AdaptadorEquipos adaptadorEquipos;
    public static ArrayList<Equipo> listaEquipos = new ArrayList<>();
    public final int COD_PETICION_INSERCION_EQUIPO = 1;
    private int posBorrar=-1; //indicará posición del equipo a borrar.
                              //Se anotará cando se elija un equipo.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializamos la lista de equipos sólo si está vacía
        if (listaEquipos.isEmpty())
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

                //anoto la posición que ocupa este equipo por si luego pulsa el botón
                // borrar
                posBorrar=posicion;

                //Programo que lance la nueva activiy sabiendo la posición que fue pulsada
                lanzarActivityVerEquipo(posicion);
            }
        });
        //asigno el adaptador
        rvListaEquipos.setAdapter(adaptadorEquipos);
        //especifico la distribución o diseño de la lista: lista, grid,...
        rvListaEquipos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if
        // it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_aniadir:
                lanzarActivityAniadir();
                return true;
            case R.id.action_borrar:
                borrarEquipoSeleccionado();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_PETICION_INSERCION_EQUIPO) {
            if (resultCode == RESULT_OK) {
                String resultadoInsercion =
                        data.getStringExtra(AniadirActivity.RESULTADO_INSERCION);
                if (resultadoInsercion.equals("OK")) {
                    //notifico al adaptador para que recargue lista
                    adaptadorEquipos.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(this, "INSERCIÓN CANCELADA: Equipo nuevo no insertado", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //guardo posBorrar por si me destruye la mainActiviy al abrir Activity_VerEquipoActivity
        outState.putInt("posBorrar",posBorrar);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        posBorrar=(int)savedInstanceState.get("posBorrar");
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


    /**
     * Método que coge los datos del array de recursos xml y rellena la lista de equipos
     * con esos datos.
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


    /**
     * Método que lanza la activity AniadirActivity
     * Es llamado desde una opción de un menu de opción (AÑADIR)
     */
    private void lanzarActivityAniadir() {
        Intent aniadirEquipoIntent = new Intent(this, AniadirActivity.class);
        startActivityForResult(aniadirEquipoIntent, COD_PETICION_INSERCION_EQUIPO);
    }


    /**
     * Método que borra el equipo que esté seleccionado en la lista
     * Es llamado desde una opción de un menú de opción (BORRAR)
     */
    private void borrarEquipoSeleccionado() {
        if(posBorrar!=-1) {
            listaEquipos.remove(posBorrar);
            adaptadorEquipos.notifyDataSetChanged();
            posBorrar = -1;
            Toast.makeText(this,"EQUIPO BORRADO CORRECTAMENTE!!!", Toast.LENGTH_SHORT)
                    .show();
        }else{
            Toast.makeText(this,"NINGÚN EQUIPO SELECCIONADO!!!", Toast.LENGTH_SHORT)
                    .show();
        }

    }


}


