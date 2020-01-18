package es.alejandrtf.ejemplousofirebasedatabase.utilities;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import es.alejandrtf.ejemplousofirebasedatabase.pojos.Usuario;
import es.alejandrtf.ejemplousofirebasedatabase.storage.implementaciones.UsuariosFirebase;

public class FirebaseUIHelper {
    private FirebaseRecyclerOptions<Usuario> optionsUsuario;
    private Query queryLast50Usuarios;

    /** En el constructor definimos todas las consultas */
    public FirebaseUIHelper() {
        queryLast50Usuarios = FirebaseDatabase.getInstance()
                .getReference()
                .child(UsuariosFirebase.NODO_USUARIOS)
                .limitToLast(50);
        setOptionsUsuario(queryLast50Usuarios);

    }

    public FirebaseRecyclerOptions<Usuario> getOptionsUsuario() {
        return optionsUsuario;
    }

    private void setOptionsUsuario(Query query) {
        optionsUsuario = new FirebaseRecyclerOptions.Builder<Usuario>()
                .setQuery(query, Usuario.class)
                .build();
    }
}
