package es.alejandrtf.ejemplofragmentslistafutbol.datos;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class Equipos {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Equipo> ITEMS = new ArrayList<Equipo>();

    public static void addEquipo(Equipo item) {
        ITEMS.add(item);
    }


    /**
     * A dummy item representing a piece of content.
     */
    public static class Equipo implements Serializable {
        private String nombreEquipo;
        private Drawable imagenEscudo;
        private int puntos;

        //CONSTRUCTOR
        public Equipo(String nombreEquipo, Drawable imagenEscudo, int puntos) {
            this.nombreEquipo = nombreEquipo;
            this.imagenEscudo = imagenEscudo;
            this.puntos = puntos;
        }

        //GETTERS AND SETTERS
        public String getNombreEquipo() {
            return nombreEquipo;
        }

        public void setNombreEquipo(String nombreEquipo) {
            this.nombreEquipo = nombreEquipo;
        }

        public Drawable getImagenEscudo() {
            return imagenEscudo;
        }

        public void setImagenEscudo(Drawable imagenEscudo) {
            this.imagenEscudo = imagenEscudo;
        }

        public int getPuntos() {
            return puntos;
        }

        public void setPuntos(int puntos) {
            this.puntos = puntos;
        }
    }

}
