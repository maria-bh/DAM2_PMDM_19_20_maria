package es.alejandrtf.ejemploserviciosreceptoresnotificaciones;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_URI_CONEXION = "uri_descarga";
    public static final String TAG_APLI = MainActivity.class.getSimpleName();

    // UI
    private EditText etUrlDescarga;
    private Button btDescargar;

    //RECEPTOR AVISOS SERVICIO
    private ReceptorAvisosServicio receptorAvisosServicio;

    //GESTION DATOS FICHERO
    String[] datosAlumnosNotas;


    //region  MÉTODOS CICLO VIDA ACTIVITY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //Registro el receptor de Broadcast (BroadcastReceiver) para que la activity escuche los eventos que le envía
        // nuestro servicio
        receptorAvisosServicio = new ReceptorAvisosServicio();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ServicioLeerArchivoCSV.ACTION_FIN_CARGA_DATOS);
        intentFilter.addAction(ServicioLeerArchivoCSV.ACTION_ERROR_IO);
        intentFilter.addAction(ServicioLeerArchivoCSV.ACTION_ERROR_URL);
        registerReceiver(receptorAvisosServicio, intentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //desregistro el BroadcastReceiver
        unregisterReceiver(receptorAvisosServicio);
    }

    //endregion


    //region MÉTODOS PROPIOS

    /**
     * Método que inicializa la UI
     */
    private void initUI() {
        etUrlDescarga = findViewById(R.id.etUrlFichero);
        btDescargar = findViewById(R.id.btDescargar);
        btDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etUrlDescarga.getText())) {
                    lanzarServicio(etUrlDescarga.getText().toString());
                }
            }
        });
    }


    /**
     * Método que lanza el servicio que descarga el CSV de la url que se le pasa
     *
     * @param urlDescarga url en formato string donde se encuentra el fichero a descargar
     */

    private void lanzarServicio(String urlDescarga) {
        //arranco el servicio, pasándole la url de descarga
        Intent svc = new Intent(this, ServicioLeerArchivoCSV.class);
        svc.putExtra(EXTRA_URI_CONEXION, urlDescarga);
        startService(svc);
    }


    /**
     * Método que muestra en el LogCat los datos que se le pasan *
     *
     * @param datosAlumnosNotas String[] a mostrar en el log
     */
    private void mostrar_en_log(String[] datosAlumnosNotas) {
        for (String asignaturaAlumno : datosAlumnosNotas) {
            Log.d(TAG_APLI, asignaturaAlumno);
        }
    }
    //endregion

    //region RECEPTOR ANUNCIOS CLASE

    /////////////////////////////////////// ////////////////////////
    ///// RECEPTOR AVISOS SERVICIO /////////////////////////////////
    //////////// ///////////////////////////////////////////////////

    public class ReceptorAvisosServicio extends BroadcastReceiver {
        private NotificationManager notificationManager;
        protected static final String CANAL_ID = "mi_canal";
        protected static final int NOTIFICACION_ID = 1;
        protected static final int COD_REQUEST_PENDING_INTENT = 0;
        public static final String EXTRA_KEY_DATOS_FICHERO = "extra_key_fichero";


        public ReceptorAvisosServicio() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case ServicioLeerArchivoCSV.ACTION_FIN_CARGA_DATOS:
                    datosAlumnosNotas = intent.getStringArrayExtra(ServicioLeerArchivoCSV.EXTRA_DATOS_CARGADOS);
                    mostrar_en_log(datosAlumnosNotas);
                    Toast.makeText(context, "FIN DESCARGA", Toast.LENGTH_SHORT).show();
                    //lanzo la notificación
                    lanzarNotificacion(datosAlumnosNotas);
                    return;
                case ServicioLeerArchivoCSV.ACTION_ERROR_URL:
                    Toast.makeText(context, "URL INCORRECTA", Toast.LENGTH_SHORT).show();
                    return;
                case ServicioLeerArchivoCSV.ACTION_ERROR_IO:
                    Toast.makeText(context, "ERROR DE LECTURA EN EL FICHERO", Toast.LENGTH_SHORT).show();
                    return;
            }


        }


        private void lanzarNotificacion(String[] datos) {

            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Oreo
                //creo un canal de notificaciones
                NotificationChannel notificationChannel = new NotificationChannel(CANAL_ID, "Mis notificaciones",
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.setDescription("Es el canal empleado para notificar el servicio de descarga");
                notificationManager.createNotificationChannel(notificationChannel);
            }
            //creo el PendingIntent que lanzará la segunda activity al pulsar sobre la notificación
            Intent iMostrarFichero = new Intent(getApplicationContext(), MostrarFicheroActivity.class);
            iMostrarFichero.putExtra(EXTRA_KEY_DATOS_FICHERO, datos);

            PendingIntent intentPendiente = PendingIntent.getActivity(MainActivity.this,
                    COD_REQUEST_PENDING_INTENT, iMostrarFichero, 0);  //flags=0 es sin flags

            //creo la notificación
            NotificationCompat.Builder notificacion =
                    new NotificationCompat.Builder(getApplicationContext(), CANAL_ID)
                            .setSmallIcon(android.R.drawable.stat_sys_download_done)
                            .setContentTitle("DESCARGA FINALIZADA!!")
                            .setWhen(Calendar.getInstance().getTimeInMillis())
                            .setAutoCancel(true)
                            .setContentText("La descarga del fichero " + etUrlDescarga.getText().toString() + " ha finalizado. Pulse para verlo.")
                            .setContentIntent(intentPendiente);

            notificationManager.notify(NOTIFICACION_ID, notificacion.build());
        }
    }
}
//endregion