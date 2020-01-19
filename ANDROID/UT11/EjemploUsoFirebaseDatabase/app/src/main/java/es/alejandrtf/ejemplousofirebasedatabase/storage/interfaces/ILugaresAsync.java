package es.alejandrtf.ejemplousofirebasedatabase.storage.interfaces;

import com.google.firebase.database.DatabaseException;

import java.util.List;

import es.alejandrtf.ejemplousofirebasedatabase.pojos.Lugar;

public interface ILugaresAsync {
    interface EscuchadorGetPeoresLugares {
        void onRespuesta(List<Lugar> lugares);

        void onError(DatabaseException e);
    }

    //lugares con menos de 3 de valoración media
    void getPeoresLugares(EscuchadorGetPeoresLugares escuchador);
}
