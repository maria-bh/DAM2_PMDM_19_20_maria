package es.alejandrtf.ejemplousofirebasedatabase.storage.interfaces;

import com.google.firebase.database.DatabaseException;

import es.alejandrtf.ejemplousofirebasedatabase.pojos.Usuario;

/** Métodos para trabajar con el nodo Usuarios en Firebase
 *
 *  // SIN USAR LISTAS FIREBASE
 *  1) actualizarInicioSesion(...) : actualiza el inicio de sesión del usuario
 *                               con el uid que se le pasa.
 *  2) leerInicioSesion(...): lee el inicio de sesión del usuario con el uid que
 *                              se le pasa
 *  3) leerEmail(...): lee el email del usuario con el uid que se le pasa
 *  4) guardarUsuario(...): guarda un usuario con el uid y los datos que se le pasan
 *  5) leerUsuario(...): lee un usuario cuyo uid se le pasa y cuando tiene la información de ese
 *                       usuario el escuchador devuelve esa información.
 *
 *  // USANDO LISTAS FIREBASE
 *  6) void aniade(Usuario usuario): añade un nuevo usuario
 *  7) String nuevo(): añade un elemento en blanco y devuelve su id
 *  8) void borrar(String uidUsuario): borra el usuario cuyo uid se pasa
 *  9) void actualiza(String uid, Usuario usuario): actualiza los datos del usuario cuyo
 *                                                  uid se pasa con el usuario indicado
 *  10) void getSize(EscuchadorSizeUsuarios escuchador): devuelve el nº de elementos (usuarios)
 */
public interface IUsuariosAsync {
    interface EscuchadorLeerInicioSesion{
        void onRespuesta(Long inicioSesion);
        void onError(DatabaseException e);
    }

    interface EscuchadorLeerEmail{
        void onRespuesta(String email);
        void onError(DatabaseException e);
    }

    interface EscuchadorLeerUsuario{
        void onRespuesta(Usuario usuario);
        void onError(DatabaseException e);
    }

    interface EscuchadorSizeUsuarios{
        void onRespuesta(long size);
        void onError (DatabaseException e);
    }


    // SIN USAR LISTAS
    void actualizarInicioSesion(String uidUsuario, long inicioSesion);
    void leerInicioSesion(String uidUsuario, EscuchadorLeerInicioSesion escuchador);
    void leerEmail(String uidUsuario, EscuchadorLeerEmail escuchador);
    void guardarUsuario(String uid, String nombre, String email);
    void leerUsuario(String uidUsuario, EscuchadorLeerUsuario escuchador);

    // USANDO LISTAS
    void aniade(Usuario usuario); //añade un nuevo usuario
    String nuevo(); // añade un elemento en blanco y devuelve su id
    void borrar(String uidUsuario); // borra el usuario cuyo uid se pasa
    void actualiza(String uidUsuario, Usuario usuario); // actualiza los datos del usuario cuyo
                                                // uid se pasa con el usuario indicado
    void getSize(EscuchadorSizeUsuarios escuchador); // devuelve el nº de elementos (usuarios)
}
