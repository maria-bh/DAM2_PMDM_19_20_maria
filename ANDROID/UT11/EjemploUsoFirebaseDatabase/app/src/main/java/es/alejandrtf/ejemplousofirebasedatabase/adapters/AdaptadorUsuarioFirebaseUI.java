package es.alejandrtf.ejemplousofirebasedatabase.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import es.alejandrtf.ejemplousofirebasedatabase.R;
import es.alejandrtf.ejemplousofirebasedatabase.pojos.Usuario;
import es.alejandrtf.ejemplousofirebasedatabase.utilities.FirebaseUIHelper;

/**
 * INYECCIÓN DE DEPENDENCIAS
 * <p>
 * Estamos usando INYECCIÓN DE DEPENDENCIAS, pues inyectamos una dependencia hacia la
 * clase FirebaseUIHelper.
 * Se hace en el constructor.
 * La clase FirebaseUIHelper contiene todos los objetos y métodos para configurar
 * FirebaseUI de forma correcta.
 */
public class AdaptadorUsuarioFirebaseUI extends
        FirebaseRecyclerAdapter<Usuario, IAdaptadorUsuario.UsuarioHolder>
        implements IAdaptadorUsuario {

    private View.OnClickListener onClickListener;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     */
    public AdaptadorUsuarioFirebaseUI(FirebaseUIHelper firebaseUIHelper) {
        super(firebaseUIHelper.getOptionsUsuario());
    }


    @NonNull
    @Override
    public IAdaptadorUsuario.UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new IAdaptadorUsuario.UsuarioHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull IAdaptadorUsuario.UsuarioHolder holder, int position, @NonNull Usuario usuario) {
        holder.bindUsuario(usuario);
        holder.itemView.setOnClickListener(onClickListener);
    }


    @Override
    public void setOnItemClickListener(View.OnClickListener onClick) {
        onClickListener = onClick;

    }

    @Override
    public RecyclerView.Adapter toRecyclerAdapter() {
        return (RecyclerView.Adapter) this;
    }


    @Override
    public String getKey(int pos) {
        return super.getSnapshots().getSnapshot(pos).getKey();
    }


}
