package es.alejandrtf.ejemplousofirebasedatabase.storage.implementaciones;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.alejandrtf.ejemplousofirebasedatabase.storage.interfaces.IUsuariosAsync;
import es.alejandrtf.ejemplousofirebasedatabase.pojos.Usuario;

/**
 * Clase que gestiona todas las operaciones sobre el nodo Usuarios de una BD
 * Firebase Realtime Database
 */
public class UsuariosFirebase implements IUsuariosAsync {
    public final static String NODO_USUARIOS = "usuarios";
    private DatabaseReference nodo;
    private FirebaseDatabase database;

    public UsuariosFirebase() {
        database = FirebaseDatabase.getInstance();
        nodo = database.getReference().child(NODO_USUARIOS);
        /* También se puede poner, sin usar "child()" así:

            nodo=database.getReference(NODO_USUARIOS);

           En este caso, la ruta va dentro de getReference, como parámetro
         */
    }

    // GETTERS


    public DatabaseReference getNodo() {
        return nodo;
    }

    @Override
    public void actualizarInicioSesion(final String uidUsuario, final long inicioSesion) {
        //FORMA 1 DE HACERLO: usando child() para lograr la ruta de acceso
        // Si usamos esta forma, habría que crear en el interface IUsuariosAsync un escuchador
        // igual que lo hicimos para leer y llamarlo en addOnSuccessListener para
        // no escribir aquí, no se debe hacer.
        nodo.child(uidUsuario).child("inicioSesion").setValue(inicioSesion)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("FIREBASE", "actualizado " + uidUsuario + ": " + inicioSesion);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FIREBASE", "actualizando error " + e.getLocalizedMessage());
                    }
                });
        /* NO ES NECESARIO AÑADIR EL ESCUCHADOR, sólo si lo queremos controlar.
           Si no se pone, quedaría:

           nodo.child(uidUsuario).child("inicioSesion").setValue(inicioSesion);

         */

        //FORMA 2 DE HACERLO: sin usar child para la ruta de acceso
        /*
            database.getReference(NODO_USUARIOS+"/"+uidUsuario+"/inicioSesion")
                    .setValue(inicioSesion);
        */
    }


    @Override
    public void leerInicioSesion(String uidUsuario, final EscuchadorLeerInicioSesion escuchador) {
        nodo.child(uidUsuario).child("inicioSesion")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Long value = dataSnapshot.getValue(Long.class);
                        escuchador.onRespuesta(value);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        escuchador.onError(error.toException());
                    }
                });
    }


    @Override
    public void leerEmail(String uidUsuario, final EscuchadorLeerEmail escuchador) {
        nodo.child(uidUsuario).child("correo")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String email = dataSnapshot.getValue(String.class);
                        escuchador.onRespuesta(email);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        escuchador.onError(error.toException());
                    }
                });
    }


    @Override
    public void guardarUsuario(String uid, String nombre, String email) {
        Usuario usuario = new Usuario(nombre, email);
        nodo.child(uid).setValue(usuario);

        //FORMA 2 DE HACERLO: sin usar child para la ruta de acceso
        /*
            database.getReference(NODO_USUARIOS + "/" + uid)
                    .setValue(usuario);
        */
    }


    @Override
    public void leerUsuario(String uidUsuario, final EscuchadorLeerUsuario escuchador) {
        nodo.child(uidUsuario)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Usuario usuario = dataSnapshot.getValue(Usuario.class);
                        escuchador.onRespuesta(usuario);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        escuchador.onError(error.toException());
                    }
                });
    }


    //////////////////////////////////////////////////////////////////////
    ////////////////  OPERACIONES CON LISTAS FIREBASE /////////////////////
    //////////////////////////////////////////////////////////////////////
    @Override
    public void aniade(Usuario usuario) {
        nodo.push().setValue(usuario);
    }

    @Override
    public String nuevo() {
        return nodo.push().getKey();
    }

    @Override
    public void borrar(String uidUsuario) {
        nodo.child(uidUsuario).setValue(null);
    }

    @Override
    public void actualiza(String uidUsuario, Usuario usuario) {
        nodo.child(uidUsuario).setValue(usuario);
    }

    @Override
    public void getSize(final EscuchadorSizeUsuarios escuchador) {
        nodo.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                escuchador.onRespuesta(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                escuchador.onError(error.toException());
            }
        });
    }
}
