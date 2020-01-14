package es.alejandrtf.ejemplousofirebasedatabase.storage.interfaces;

import com.google.firebase.database.DatabaseException;

import es.alejandrtf.ejemplousofirebasedatabase.pojos.Usuario;

/** Métodos para trabajar con el nodo Usuarios en Firebase
 *  1) actualizarInicioSesion(...) : actualiza el inicio de sesión del usuario
 *                               con el uid que se le pasa.
 *  2) leerInicioSesion(...): lee el inicio de sesión del usuario con el uid que
 *                              se le pasa
 *  3) leerEmail(...): lee el email del usuario con el uid que se le pasa
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


    public void actualizarInicioSesion(String uidUsuario, long inicioSesion);
    public void leerInicioSesion(String uidUsuario,EscuchadorLeerInicioSesion escuchador);
    public void leerEmail(String uidUsuario,EscuchadorLeerEmail escuchador);
    public void guardarUsuario(String uid,String nombre, String email);
    public void leerUsuario(String uidUsuario, EscuchadorLeerUsuario escuchador);
}
