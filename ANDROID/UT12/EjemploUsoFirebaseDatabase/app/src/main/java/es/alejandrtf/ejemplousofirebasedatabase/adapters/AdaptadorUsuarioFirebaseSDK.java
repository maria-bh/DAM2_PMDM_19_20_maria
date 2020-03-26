package es.alejandrtf.ejemplousofirebasedatabase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import es.alejandrtf.ejemplousofirebasedatabase.R;
import es.alejandrtf.ejemplousofirebasedatabase.pojos.Usuario;
import es.alejandrtf.ejemplousofirebasedatabase.storage.implementaciones.UsuariosFirebase;


/**
 * INYECCIÓN DE DEPENDENCIAS
 * <p>
 * Estamos usando INYECCIÓN DE DEPENDENCIAS, pues inyectamos una dependencia hacia la
 * clase UsuariosFirebase.
 * Se hace en el constructor.
 * La clase UsuariosFirebase es la implementación del interface IUsuariosAsync, que
 * contiene todos los objetos y métodos para trabajar con usuarios en una implementación
 * hecha con Firebase.
 *
 * El interface IUsuariosAsync no hace referencia a ninguna implementación: podría ser
 * una BD relacional o no. En UsuariosFirebase implementamos ese interface para Firebase
 *
 */
public class AdaptadorUsuarioFirebaseSDK extends RecyclerView.Adapter<IAdaptadorUsuario.UsuarioHolder>
        implements ChildEventListener, IAdaptadorUsuario {

    private DatabaseReference usuariosRef; // referencia al nodo usuarios
    private ArrayList<String> keys; // contendrá las claves de cada uno de los elementos
    private ArrayList<DataSnapshot> items; // contendrá la lista de elementos (usuarios) en
    // formato DataSnapshot para guardar la referencia al
    // nodo Firebase

    private View.OnClickListener onClickListener;

    /** Necesitamos inyectar UsuariosFirebase para obtener la referencia al nodo
     *  "usuarios" de Firebase, puesto que hay que asignarle el ChildEventListener.
     *
     * @param usuariosFirebase implementación en Firebase de IUsuariosAsync
     */
    public AdaptadorUsuarioFirebaseSDK(UsuariosFirebase usuariosFirebase) {
        this.usuariosRef = usuariosFirebase.getNodo();
        keys = new ArrayList<String>();
        items = new ArrayList<DataSnapshot>();
    }


    @Override
    public IAdaptadorUsuario.UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent, false);
        return new IAdaptadorUsuario.UsuarioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {
        Usuario usuario = getItem(position);
        holder.bindUsuario(usuario);
        holder.itemView.setOnClickListener(onClickListener);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    /**
     * Método que devuelve el usuario almacenado en la posición pos del ArrayList
     *
     * @param pos pos que ocupa el usuario
     * @return el usuario indicado
     */
    public Usuario getItem(int pos) {
        return items.get(pos).getValue(Usuario.class);
    }


    /**
     * Método que devuelve la clave del usuario almacenado en la posición pos del ArrayList
     *
     * @param pos pos que ocupa el usuario
     * @return la clave de ese usuario
     */
    public String getKey(int pos) {
        return items.get(pos).getRef().getKey();
    }


    /**
     * Método que implementa el evento onItemClick
     *
     * @param onClick el onClickListener
     */
    public void setOnItemClickListener(View.OnClickListener onClick) {
        onClickListener = onClick;
    }


    /**
     * Método que activa el escuchador de eventos para los hijos
     */
    public void startListening() {
        keys = new ArrayList<>();
        items = new ArrayList<>();
        usuariosRef.addChildEventListener(this);
    }


    /**
     * Método que desactiva el escuchador de eventos para los hijos
     */
    public void stopListening() {
        usuariosRef.removeEventListener(this);
    }

    @Override
    public RecyclerView.Adapter toRecyclerAdapter() {
        return ((RecyclerView.Adapter) this);
    }


    ///////////// EVENTOS DE ACTUALIZACIÓN   ////////////////////
    /////////////////////////////////////////////////////////////
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        items.add(dataSnapshot);
        keys.add(dataSnapshot.getKey());
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        String key = dataSnapshot.getKey();
        int index = keys.indexOf(key);
        if (index != -1) {
            items.set(index, dataSnapshot);
            notifyItemChanged(index, dataSnapshot.getValue(Usuario.class));
        }
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        String key = dataSnapshot.getKey();
        int index = keys.indexOf(key);
        if (index != -1) {
            keys.remove(index);
            items.remove(index);
            notifyItemRemoved(index);
        }
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    }


}

