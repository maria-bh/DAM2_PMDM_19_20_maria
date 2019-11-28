package es.alejandrtf.ejemplointentsimplicitos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btAbrirWeb, btVerEnMapa, btLlamar, btMarcarSinLLamar, btEnviarEmail;
    private EditText etUrl, etLatitud, etLongitud, etPhoneNumber,
            etEmailDestino, etAsuntoEmail, etTextoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReferences();
        setListenersBotones();

    }

    /**
     * Método obtiene referencias a objetos XML
     */
    private void initReferences() {
        etUrl = findViewById(R.id.etUrl);
        btAbrirWeb = findViewById(R.id.btVerWeb);
        etLatitud = findViewById(R.id.etLatitud);
        etLongitud = findViewById(R.id.etLongitud);
        btVerEnMapa = findViewById(R.id.btVerEnMapa);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btLlamar = findViewById(R.id.btLlamar);
        btMarcarSinLLamar = findViewById(R.id.btMarcadorLlamada);
        etEmailDestino = findViewById(R.id.etDestinatario);
        etAsuntoEmail = findViewById(R.id.etAsunto);
        etTextoEmail = findViewById(R.id.etTexto);
        btEnviarEmail = findViewById(R.id.btEnviarEmail);
    }


    /**
     * Método asigna listener botones
     */
    private void setListenersBotones() {
        btAbrirWeb.setOnClickListener(this);
        btVerEnMapa.setOnClickListener(this);
        btLlamar.setOnClickListener(this);
        btMarcarSinLLamar.setOnClickListener(this);
        btEnviarEmail.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btVerWeb:
                abrirWeb(etUrl.getText().toString());
                break;
            case R.id.btVerEnMapa:
                if (TextUtils.isEmpty(etLatitud.getText())) {
                    etLatitud.setError("Introduce una latitud");
                } else if (TextUtils.isEmpty(etLongitud.getText())) {
                    etLongitud.setError("Introduce una longitud");
                } else {
                    verEnMapa(etLatitud.getText().toString(),
                            etLongitud.getText().toString());
                }
                break;
            case R.id.btLlamar:
                if (TextUtils.isEmpty(etPhoneNumber.getText())) {
                    etPhoneNumber.setError("Introduce un número de teléfono");
                } else
                    llamar(etPhoneNumber.getText().toString());
                break;
            case R.id.btMarcadorLlamada:
                if (TextUtils.isEmpty(etPhoneNumber.getText())) {
                    etPhoneNumber.setError("Introduce un número de teléfono");
                } else
                    marcarSinLlamar(etPhoneNumber.getText().toString());
                break;
            case R.id.btEnviarEmail:
                if (TextUtils.isEmpty(etEmailDestino.getText())) {
                    etEmailDestino.setError("Introduce un email a quien enviarlo");
                } else
                    enviarEmail(etEmailDestino.getText().toString(),
                            etAsuntoEmail.getText().toString(),
                            etTextoEmail.getText().toString());
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.camara)
            lanzarActivityCamara();
        else super.onOptionsItemSelected(item);
        return true;
    }


    //region MÉTODOS PROPIOS

    /**
     * Abre una aplicación para ver una web
     *
     * @param urlString
     */
    private void abrirWeb(String urlString) {
        if (!TextUtils.isEmpty(urlString)) {

            Intent abrirNavegadorIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(urlString));
            if (abrirNavegadorIntent.resolveActivity(getPackageManager()) != null)
                startActivity(abrirNavegadorIntent);
            else
                Toast.makeText(this, "No hay ninguna aplicación para abrir esa url", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Abre un mapa de Google en las coordenadas indicadas
     *
     * @param lat
     * @param lon
     */
    private void verEnMapa(String lat, String lon) {
        Intent verMapaIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:" + lat + "," + lon + "?q=" + lat + "," + lon));
        if (verMapaIntent.resolveActivity(getPackageManager()) != null)
            startActivity(verMapaIntent);
        else
            Toast.makeText(this, "No hay ninguna aplicación para abrir el mapa", Toast.LENGTH_SHORT).show();
    }


    /**
     * llama por teléfono sin preguntar al número que se indica
     *
     * @param numero
     */
    private void llamar(String numero) {
        Intent llamarIntent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:" + numero));
        if (llamarIntent.resolveActivity(getPackageManager()) != null)
            startActivity(llamarIntent);
        else
            Toast.makeText(this, "No hay ninguna aplicación para llamar", Toast.LENGTH_SHORT).show();

    }


    /**
     * marca el número de teléfono sin llegar a marcar.
     * Espera confirmación del usuario
     *
     * @param numero
     */
    private void marcarSinLlamar(String numero) {
        Intent marcarIntent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + numero));
        if (marcarIntent.resolveActivity(getPackageManager()) != null)
            startActivity(marcarIntent);
        else
            Toast.makeText(this, "No hay ninguna aplicación para llamar", Toast.LENGTH_SHORT).show();

    }


    /**
     * envía un email a ese destino con ese asunto y texto
     *
     * @param email
     * @param asunto
     * @param texto
     */
    private void enviarEmail(String email, String asunto, String texto) {
        Intent enviarEmailIntent = new Intent(Intent.ACTION_SEND);
        enviarEmailIntent.setType("text/plain");
        enviarEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        enviarEmailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        enviarEmailIntent.putExtra(Intent.EXTRA_TEXT, texto);
        if (enviarEmailIntent.resolveActivity(getPackageManager()) != null)
            startActivity(enviarEmailIntent);
        else
            Toast.makeText(this, "No hay ninguna aplicación para enviar emails", Toast.LENGTH_SHORT).show();

    }


    /** lanza la activity con ejemplos de cámara y galería
     *
     */
    private void lanzarActivityCamara(){
        Intent i=new Intent(this,CamaraActivity.class);
        startActivity(i);
    }

    //endregion
}
