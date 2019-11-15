package es.alejandrtf.tutoria2app2recyclerviewconinterface;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.EquiposViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int posicion);//el parámetro será la posición del elemento de la lista
    }

    private String[] listaEquipos;
    private OnItemClickListener mListener;

    public AdaptadorEquipos(String[] listaEquipos) {
        this.listaEquipos = listaEquipos;
    }


    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    @Override
    public EquiposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        EquiposViewHolder equiposViewHolder = new EquiposViewHolder(itemView, mListener);
        return equiposViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EquiposViewHolder holder, int position) {
        String equipo = listaEquipos[position];
        holder.bindEquipo(equipo);
    }

    @Override
    public int getItemCount() {
        return listaEquipos.length;
    }


    /**
     * VIEWHOLDER
     */
    public static class EquiposViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre;


        public EquiposViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            //asigno listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

            tvNombre = (TextView) itemView; //porque este layout solo es un TextView


        }

        public void bindEquipo(String equipo) {
            tvNombre.setText(equipo);

        }
    }

}


