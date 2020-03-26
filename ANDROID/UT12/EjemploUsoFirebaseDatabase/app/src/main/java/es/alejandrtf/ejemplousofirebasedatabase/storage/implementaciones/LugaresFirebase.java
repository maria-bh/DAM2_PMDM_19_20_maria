package es.alejandrtf.ejemplousofirebasedatabase.storage.implementaciones;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.alejandrtf.ejemplousofirebasedatabase.pojos.Lugar;
import es.alejandrtf.ejemplousofirebasedatabase.storage.interfaces.ILugaresAsync;
import es.alejandrtf.ejemplousofirebasedatabase.utilities.FirebaseUIHelper;

public class LugaresFirebase implements ILugaresAsync {
    public final static String NODO_LUGARES = "lugares";
    private DatabaseReference nodo;
    private FirebaseDatabase database;
    private FirebaseUIHelper fUIHelper;

    public LugaresFirebase(FirebaseUIHelper firebaseUIHelper) {
        database = FirebaseDatabase.getInstance();
        nodo = database.getReference().child(NODO_LUGARES);
        fUIHelper = firebaseUIHelper;
    }

    @Override
    public void getPeoresLugares(final EscuchadorGetPeoresLugares escuchador) {
        final ArrayList<Lugar> lugares = new ArrayList<>();

        Query query = fUIHelper.getQueryLugaresMenosValoracion3();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot lugarDataSnapshot :
                        dataSnapshot.getChildren()) {
                    Lugar lugar = lugarDataSnapshot.getValue(Lugar.class);
                    lugares.add(lugar);
                }
                escuchador.onRespuesta(lugares);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                escuchador.onError(error.toException());
            }
        });
    }
}
