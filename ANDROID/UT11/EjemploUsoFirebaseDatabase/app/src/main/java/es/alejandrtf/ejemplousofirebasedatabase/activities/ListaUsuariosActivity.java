package es.alejandrtf.ejemplousofirebasedatabase.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import es.alejandrtf.ejemplousofirebasedatabase.R;
import es.alejandrtf.ejemplousofirebasedatabase.adapters.IAdaptadorUsuario;
import es.alejandrtf.ejemplousofirebasedatabase.factories.FactoriaAdaptadorUsuario;

public class ListaUsuariosActivity extends AppCompatActivity {
    protected static String EXTRA_POS_USUARIO="pos_usuario";

    private RecyclerView rvListaUsuarios;
    private static IAdaptadorUsuario adaptadorUsuario;
    private enum TipoOperacion{AÑADIR,VISUALIZAR};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarActivityVisualizarUsuario(TipoOperacion.AÑADIR,-1);
            }
        });

        initReferences();
        adaptadorUsuario = FactoriaAdaptadorUsuario.getAdaptadorUsuario(FactoriaAdaptadorUsuario.TipoAdaptador.FIREBASEUI);
        adaptadorUsuario.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ListaUsuariosActivity", "Has pulsado el elemento" + rvListaUsuarios.getChildAdapterPosition(view));
                mostrarUsuario(rvListaUsuarios.getChildAdapterPosition(view));
            }        });

        adaptadorUsuario.startListening();

        rvListaUsuarios.setAdapter(adaptadorUsuario.toRecyclerAdapter());
        rvListaUsuarios.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvListaUsuarios.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initReferences() {
        rvListaUsuarios = findViewById(R.id.rvListadoUsuarios);
    }


    @Override
    protected void onDestroy() {
        adaptadorUsuario.stopListening();
        super.onDestroy();
    }


    /** Lanza la activity VisualizarUsuarioActivity en modo añadir o para visualizar o editar,
     *  según los parámetros indicados.
     * @param tipoOperacion puede ser AÑADIR para añadir o VISUALIZAR para editar o ver o borrar
     * @param posUsuario será la posición de ese usuario en la RecyclerView o -1 si estamos añadiendo
     */
    private void lanzarActivityVisualizarUsuario(TipoOperacion tipoOperacion,int posUsuario) {
        Intent i = new Intent(this, VisualizarUsuarioActivity.class);
        if(tipoOperacion==TipoOperacion.VISUALIZAR){
            // paso la posición de ese elemento a la activity
            i.putExtra(EXTRA_POS_USUARIO,posUsuario);
        }
        startActivity(i);
    }

    /** Lanza la activity VisualizarUsuario pasándole la posición que ocupa ese usuario en
     * la recyclerView
     *
     * @param posUsuario posición de ese usuario en la RecyclerView
     */
    private void mostrarUsuario(int posUsuario){
        lanzarActivityVisualizarUsuario(TipoOperacion.VISUALIZAR,posUsuario);
    }


    /** Necesito el adaptador en otras activities y de esta forma lo puedo lograr
     *
     * @return el adaptador que usa esta lista
     */
    public static IAdaptadorUsuario getAdaptador(){
        return adaptadorUsuario;
    }

}
