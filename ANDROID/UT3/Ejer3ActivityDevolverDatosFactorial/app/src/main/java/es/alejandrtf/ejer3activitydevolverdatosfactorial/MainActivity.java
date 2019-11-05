package es.alejandrtf.ejer3activitydevolverdatosfactorial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected final int COD_REQUEST_FACTORIAL=1;
    private long factorial=-1;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReferences();
    }


    /**
     * Método que obtiene las referencias a las vistas XML
     */
    private void initReferences() {
        tvResultado=findViewById(R.id.tvResultado);
    }

    /** Método que se ejecuta al pulsar el botón INTRODUCIR UN Nº
     *  y que lanza la activity Factoria y espera por el resultado que ésta devuelva.
     *
     * @param view
     */
    public void lanzarActivityFactorial(View view) {
        Intent iFactorial=new Intent(this,Factorial.class);
        startActivityForResult(iFactorial,COD_REQUEST_FACTORIAL);
    }


    /** Método que se ejecuta cuando se vuelve de la Activity Factorial
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==COD_REQUEST_FACTORIAL && resultCode==RESULT_OK){
            // Recojo los datos que me vienen en data
            factorial=data.getLongExtra(Factorial.KEY_FACTORIAL,-1);
            if(factorial!=-1){
                tvResultado.setText(String.valueOf(factorial));
            }else{
                tvResultado.setError(getString(R.string.error_resultado_factorial));
            }

        }
    }
}
