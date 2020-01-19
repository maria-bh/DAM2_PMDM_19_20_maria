package es.alejandrtf.ejemplousofirebasedatabase.factories;

import es.alejandrtf.ejemplousofirebasedatabase.adapters.AdaptadorUsuarioFirebaseSDK;
import es.alejandrtf.ejemplousofirebasedatabase.adapters.AdaptadorUsuarioFirebaseUI;
import es.alejandrtf.ejemplousofirebasedatabase.adapters.IAdaptadorUsuario;
import es.alejandrtf.ejemplousofirebasedatabase.storage.implementaciones.UsuariosFirebase;
import es.alejandrtf.ejemplousofirebasedatabase.utilities.FirebaseUIHelper;

/**
 * IMPLEMENTACIÓN DEL PATRÓN FACTORÍA:
 * <p>
 * Factoría que permite construir objetos de tipo "IAdaptadorUsuario" que son adaptadores
 * que manejan el nodo "usuarios" de Firebase Realtime Database. Esta factoría permite
 * crear diferentes adaptadores según nuestras necesidades: uno para FirebaseUi, otro para
 * FirebaseSDK, otro para MongoDB,...
 * <p>
 * Además de usar el patrón factoría, estamos usando INYECCIÓN DE DEPENDENCIAS, pues inyectamos
 * una dependencia hacia la clase FirebaseUIHelper. Se hace en el constructor del objeto
 * AdaptadorUsuarioFirebaseUI. La clase FirebaseUIHelper contiene todos los objetos y métodos
 * para configurar FirebaseUI de forma correcta.
 */
public class FactoriaAdaptadorUsuario {
    public enum TipoAdaptador {FIREBASEUI,FIREBASE_SDK}

    //podría haber otro tipo MongoDb,...

    public static IAdaptadorUsuario getAdaptadorUsuario(TipoAdaptador tipo) {
        switch (tipo) {
            case FIREBASEUI:
                return new AdaptadorUsuarioFirebaseUI(new FirebaseUIHelper());
            case FIREBASE_SDK:
                return new AdaptadorUsuarioFirebaseSDK(new UsuariosFirebase());
             /* case MONGODB:
                return new AdaptadorUsuariosMongoDB();
            */
            default:
                return new AdaptadorUsuarioFirebaseUI(new FirebaseUIHelper());
        }

    }
}
