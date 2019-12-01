package es.alejandrtf.ejemploserviciosreceptoresnotificaciones;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import es.alejandrtf.ejemploserviciosreceptoresnotificaciones.utilidades.NetworkUtilities;

public class ServicioLeerArchivoCSV extends IntentService {
    public static final String EXTRA_DATOS_CARGADOS = "datos_cargados";
    public static final String ACTION_FIN_CARGA_DATOS = "es.alejandra.ejemploserviciosreceptoresnotificaciones.action.FIN_CARGA_DATOS";
    public static final String ACTION_ERROR_URL="es.alejandra.ejemploserviciosreceptoresnotificaciones.action.ERROR_URL";
    public static final String ACTION_ERROR_IO="es.alejandra.ejemploserviciosreceptoresnotificaciones.action.ERROR_IO";

    // URL desde donde descarga
    private URL url;

    // String[] donde guarda los datos descargados
    private String[] notaAlumnoAsignatura;

    public ServicioLeerArchivoCSV() {
        super("ServicioLeerArchivoCSV");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent!=null){
            if(intent.hasExtra(MainActivity.EXTRA_URI_CONEXION)){
                //recogo la uri para descargar el archivo
                Uri miUri=Uri.parse(intent.getStringExtra(MainActivity.EXTRA_URI_CONEXION));
                //leo el fichero
                try{
                    url=new URL(miUri.toString());
                    notaAlumnoAsignatura= NetworkUtilities.getResponseFromHttpUrl(url);
                    avisarEventoOcurrido(ACTION_FIN_CARGA_DATOS);

                } catch (MalformedURLException e) {
                    avisarEventoOcurrido(ACTION_ERROR_URL);
                }catch (IOException e){
                    e.printStackTrace();
                    avisarEventoOcurrido(ACTION_ERROR_IO);
                }
            }
        }

    }



    /**
     * Método que lanza un aviso de un evento ocurrido.
     * El evento se le pasa por parámetro
     */
    private void avisarEventoOcurrido(String action) {
        Intent iAviso = new Intent(action);
        if (action == ACTION_FIN_CARGA_DATOS) {
            iAviso.putExtra(EXTRA_DATOS_CARGADOS, notaAlumnoAsignatura);

        }
        //EN LOS DEMÁS CASOS, NO LLEVA EXTRAS, POR ESO NO SE AÑADEN
        sendBroadcast(iAviso);

        Log.d("MIAPLI",action);
    }

}
