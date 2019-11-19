package es.alejandrtf.ejemplosencillofragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MostrarTextoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MostrarTextoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MostrarTextoFragment extends Fragment {
    private TextView tvTexto;

    public MostrarTextoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MostrarTextoFragment.
     */
    public static MostrarTextoFragment newInstance() {
        MostrarTextoFragment fragment = new MostrarTextoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mostrar_texto, container, false);
        tvTexto = v.findViewById(R.id.tvTextoMostrado);
        return v;
    }


    public void mostrarTextoEnRojo(String texto) {
        tvTexto.setText(texto);
        tvTexto.setTextColor(Color.RED);
    }
}
