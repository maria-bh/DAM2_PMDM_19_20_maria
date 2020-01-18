package es.alejandrtf.ejemplousofirebasedatabase.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.alejandrtf.ejemplousofirebasedatabase.R;
import es.alejandrtf.ejemplousofirebasedatabase.pojos.Usuario;

public interface IAdaptadorUsuario {
    String getKey(int pos); //lo usa Firebase
    Usuario getItem(int pos); //lo usa Firebase
    void setOnItemClickListener(View.OnClickListener onClick); //asigna escuchador itemClick
    void startListening(); //lo usa Firebase
    void stopListening();  //lo usa Firebase
    RecyclerView.Adapter toRecyclerAdapter();

    class UsuarioHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvEmail;
        TextView tvInicioSesion;

        UsuarioHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvInicioSesion = itemView.findViewById(R.id.tvInicioSesion);
        }

        void bindUsuario(Usuario usuario) {
            tvNombre.setText(usuario.getNombre());
            tvEmail.setText(usuario.getCorreo());
            tvInicioSesion.setText(String.valueOf(usuario.getInicioSesion()));
        }
    }
}
