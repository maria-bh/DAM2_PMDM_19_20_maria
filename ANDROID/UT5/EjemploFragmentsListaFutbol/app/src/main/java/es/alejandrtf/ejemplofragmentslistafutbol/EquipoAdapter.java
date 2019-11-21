package es.alejandrtf.ejemplofragmentslistafutbol;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import es.alejandrtf.ejemplofragmentslistafutbol.ListaEquipoFragment.OnItemClickListener;
import es.alejandrtf.ejemplofragmentslistafutbol.datos.Equipos.Equipo;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Equipo} and makes a call to the
 * specified {@link OnItemClickListener}.
 */
public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.ViewHolder> {

    private final List<Equipo> listaEquipos;
    private final OnItemClickListener mListener;

    public EquipoAdapter(List<Equipo> listaEquipos, ListaEquipoFragment.OnItemClickListener listener) {
        this.listaEquipos = listaEquipos;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_fragment_equipo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Equipo equipo = listaEquipos.get(position);
        holder.bindEquipo(equipo);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(equipo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvNombre,tvPuntosEquipo;
        public final ImageView ivEscudo;
        public final View mView;


        public ViewHolder(View view) {
            super(view);
            mView=view;

            tvNombre =  view.findViewById(R.id.tvNombreEquipoItem);
            tvPuntosEquipo=view.findViewById(R.id.tvPuntosItem);
            ivEscudo =  view.findViewById(R.id.ivEscudo);

        }

        public void bindEquipo(Equipo equipo) {
            tvNombre.setText(equipo.getNombreEquipo());
            tvPuntosEquipo.setText(String.valueOf(equipo.getPuntos()));
            ivEscudo.setImageDrawable(equipo.getImagenEscudo());

        }
    }
}
