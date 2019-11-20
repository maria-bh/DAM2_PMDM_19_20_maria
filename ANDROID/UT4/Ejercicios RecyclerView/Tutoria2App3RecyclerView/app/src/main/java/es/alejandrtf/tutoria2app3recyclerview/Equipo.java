package es.alejandrtf.tutoria2app3recyclerview;

import android.graphics.drawable.Drawable;

public class Equipo {
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
