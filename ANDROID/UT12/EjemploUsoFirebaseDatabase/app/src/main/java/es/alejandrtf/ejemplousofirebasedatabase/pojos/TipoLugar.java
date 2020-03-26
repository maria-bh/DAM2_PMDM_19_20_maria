package es.alejandrtf.ejemplousofirebasedatabase.pojos;


import es.alejandrtf.ejemplousofirebasedatabase.R;

public enum TipoLugar {
    OTROS("Otros", R.drawable.ic_launcher_foreground),
    RESTAURANTE("Restaurante", R.drawable.ic_launcher_foreground),
    BAR("Bar", R.drawable.ic_launcher_foreground),
    COPAS("Copas", R.drawable.ic_launcher_foreground),
    ESPECTACULO("Espectáculo", R.drawable.ic_launcher_foreground),
    HOTEL("Hotel", R.drawable.ic_launcher_foreground),
    COMPRAS("Compras", R.drawable.ic_launcher_foreground),
    EDUCACION("Educación", R.drawable.ic_launcher_foreground),
    DEPORTE("Deporte", R.drawable.ic_launcher_foreground),
    NATURALEZA("Naturaleza", R.drawable.ic_launcher_foreground),
    GASOLINERA("Gasolinera", R.drawable.ic_launcher_foreground);

    private final String texto;
    private final int recurso;

    TipoLugar(String texto, int recurso) {
        this.texto = texto;
        this.recurso = recurso;
    }

    public String getTexto() {
        return texto;
    }

    public int getRecurso() {
        return recurso;
    }


    public static String[] getNombres() {
        String[] resultado = new String[TipoLugar.values().length];
        for (TipoLugar tipo : TipoLugar.values()) {
            resultado[tipo.ordinal()] = tipo.texto;
        }
        return resultado;
    }

}
