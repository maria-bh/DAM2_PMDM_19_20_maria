package es.alejandrtf.ejemplofragmentslistafutbol;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import es.alejandrtf.ejemplofragmentslistafutbol.datos.Equipos;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link VerEquipoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerEquipoFragment extends Fragment {
    private TextView tvNombreEquipo,tvPuntosEquipo;
    private ImageView ivEscudo;
    private Equipos.Equipo equipoMostrado;

    public VerEquipoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment VerEquipoFragment.
     */
    public static VerEquipoFragment newInstance() {
        VerEquipoFragment fragment = new VerEquipoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        equipoMostrado=null;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_ver_equipo, container, false);
        ivEscudo=v.findViewById(R.id.ivEscudoVerEquipo);
        tvNombreEquipo=v.findViewById(R.id.tvNombreEquipoVerEquipo);
        tvPuntosEquipo=v.findViewById(R.id.tvPuntosVerEquipo);
        return v;
    }


    public void mostrarEquipo(Equipos.Equipo item) {
        if(item !=null){
            equipoMostrado=item;
            tvNombreEquipo.setText(item.getNombreEquipo());
            tvPuntosEquipo.setText(String.valueOf(item.getPuntos()));
            ivEscudo.setImageDrawable(item.getImagenEscudo());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("equipoMostrado",equipoMostrado);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         if(savedInstanceState!=null){
            //ya se había ejecutado antes el fragment, lo restauro
            if(savedInstanceState.containsKey("equipoMostrado")){
                mostrarEquipo((Equipos.Equipo) savedInstanceState.get("equipoMostrado"));
            }
        }
    }
}
