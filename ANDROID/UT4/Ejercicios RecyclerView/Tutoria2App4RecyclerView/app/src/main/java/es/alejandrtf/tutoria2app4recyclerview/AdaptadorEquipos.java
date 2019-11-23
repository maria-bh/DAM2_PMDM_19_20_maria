package es.alejandrtf.tutoria2app4recyclerview;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.EquiposViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int posicion);//el parámetro será la posición del elemento de la lista
    }

    private List<Equipo> listaEquipos;
    private OnItemClickListener mListener;

    public AdaptadorEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public EquiposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_equipos, parent, false);
        EquiposViewHolder equiposViewHolder = new EquiposViewHolder(itemView, mListener);
        return equiposViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EquiposViewHolder holder, int position) {
        Equipo equipo = listaEquipos.get(position);
        holder.bindEquipo(equipo);
    }

    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }


    /**
     * VIEWHOLDER
     */
    public static class EquiposViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvPuntosEquipo;
        ImageView ivEscudo;


        public EquiposViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            //asigno listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

            tvNombre = itemView.findViewById(R.id.tvNombreEquipoItem);
            tvPuntosEquipo = itemView.findViewById(R.id.tvPuntosItem);
            ivEscudo = itemView.findViewById(R.id.ivEscudo);

        }

        public void bindEquipo(Equipo equipo) {
            tvNombre.setText(equipo.getNombreEquipo());
            tvPuntosEquipo.setText(String.valueOf(equipo.getPuntos()));
            ivEscudo.setImageDrawable(equipo.getImagenEscudo());

        }
    }

}


