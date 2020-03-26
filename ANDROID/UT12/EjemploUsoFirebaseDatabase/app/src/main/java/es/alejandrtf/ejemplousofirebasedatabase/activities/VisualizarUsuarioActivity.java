package es.alejandrtf.ejemplousofirebasedatabase.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.alejandrtf.ejemplousofirebasedatabase.R;
import es.alejandrtf.ejemplousofirebasedatabase.pojos.Usuario;
import es.alejandrtf.ejemplousofirebasedatabase.storage.implementaciones.UsuariosFirebase;
import es.alejandrtf.ejemplousofirebasedatabase.storage.interfaces.IUsuariosAsync;

public class VisualizarUsuarioActivity extends AppCompatActivity {
    private int posUsuarioEnRecyclerView;
    private Button btAniadir, btEditar;
    private EditText etNombre, etEmail;
    private IUsuariosAsync usuarios; //operaciones usuarios con la BD
    private Usuario usuarioVisualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initReferences();
        //FIREBASE
        usuarios = new UsuariosFirebase();

        posUsuarioEnRecyclerView = getPosUsuario();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(posUsuarioEnRecyclerView!=-1)
                    borrarUsuario(posUsuarioEnRecyclerView);
            }
        });


        if (posUsuarioEnRecyclerView == -1) {
            //MODO AÑADIR NUEVO
            btAniadir.setEnabled(true);
            btEditar.setEnabled(false);
        } else {
            // MODO VISUALIZAR/EDITAR/BORRAR
            btAniadir.setEnabled(false);
            btEditar.setEnabled(true);
            usuarioVisualizar = getUsuario(posUsuarioEnRecyclerView);
            if (usuarioVisualizar != null)
                actualizarUI(usuarioVisualizar);
        }

    }


    private void initReferences() {
        btAniadir = findViewById(R.id.btAniadirUsuario);
        btEditar = findViewById(R.id.btEditarUsuario);
        etEmail = findViewById(R.id.etEmailVisualizar);
        etNombre = findViewById(R.id.etNombreUsuarioVisualizar);
    }


    /**
     * Recoge del intent la pos del Usuario en la RecyclerView o -1 si es para añadir nuevo
     *
     * @return la pos
     */
    private int getPosUsuario() {
        int pos = getIntent().hasExtra(ListaUsuariosActivity.EXTRA_POS_USUARIO)
                ? getIntent().getIntExtra(ListaUsuariosActivity.EXTRA_POS_USUARIO, -1)
                : -1;
        return pos;
    }


    /**
     * Devuelve el usuario que ocupa la posición pos en la RecyclerView
     *
     * @param pos posición en la lista RecyclerView
     * @return el usuario
     */
    private Usuario getUsuario(int pos) {
        return ListaUsuariosActivity.getAdaptador().getItem(pos);
    }


    private void actualizarUI(Usuario usuario) {
        etNombre.setText(usuario.getNombre());
        etEmail.setText(usuario.getCorreo());
    }


    private void borrarUsuario(int pos){
        usuarios.borrar(ListaUsuariosActivity.getAdaptador().getKey(posUsuarioEnRecyclerView));
        finish();
    }


    // Métodos ejecutan al pulsar los botones UI
    public void addUsuario(View view) {
        Usuario usuario = new Usuario(etNombre.getText().toString(), etEmail.getText().toString());
        usuarios.aniade(usuario);
    }

    public void actualizarUsuario(View view) {
        Usuario usuario=new Usuario(etNombre.getText().toString(), etEmail.getText().toString());
        usuarios.actualiza(ListaUsuariosActivity.getAdaptador().getKey(posUsuarioEnRecyclerView),usuario);
    }
}
