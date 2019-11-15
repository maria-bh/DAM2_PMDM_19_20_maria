package es.alejandrtf.tutoria2app2recyclerview;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.EquiposViewHolder> {

    private static  String[] listaEquipos;

    public AdaptadorEquipos(String[] listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    @Override
    public EquiposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        EquiposViewHolder equiposViewHolder = new EquiposViewHolder(itemView);
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


        public EquiposViewHolder(View itemView) {
            super(itemView);

            //asigno listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //getAdapterPosition() me da la posición de la vista sobre la que se hizo click
                   lanzarActivityVerEquipo(getAdapterPosition());
                }
            });

            tvNombre = (TextView) itemView; //porque este layout solo es un TextView


        }

        public void bindEquipo(String equipo) {
            tvNombre.setText(equipo);

        }


        /**
         * Método que lanza la activity que muestra los datos del equipo de fútbol cuya posición se indica
         *
         * @param pos posición de ese equipo de fútbol en la lista
         */
        private void lanzarActivityVerEquipo(int pos) {
            //this aquí hace referencia a la clase EquiposViewHolder
            Intent i = new Intent(this.itemView.getContext(), VerEquipoActivity.class);
            i.putExtra("NombreEquipo",AdaptadorEquipos.listaEquipos[pos] );
            this.itemView.getContext().startActivity(i);
        }

    }
}


