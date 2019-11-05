package es.alejandrtf.ejer3activitydevolverdatosfactorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class Factorial extends AppCompatActivity {
    protected static final String KEY_FACTORIAL="key_factorial";
    private EditText etNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial);

        initReferences();
    }


    /**
     * Método que obtiene las referencias a las vistas XML
     */
    private void initReferences() {
        etNumero=findViewById(R.id.etNumero);
    }


    /** Método que se ejecuta al pulsar el botón CALCULAR FACTORIAL
     *
     * @param view
     */
        public void calcularFactorial(View view) {
            if(TextUtils.isEmpty(etNumero.getText())){
                etNumero.setError(getString(R.string.error_no_numero));
                return;
            }
            devolverAMainActivity(factorial(Integer.valueOf(etNumero.getText().toString())));

    }

    /** Método que calcula el factorial de un número N que se le pasa
     *
     * @param n número cuyo factorial se quiere calcular
     * @return el factorial de n de tipo long
     */
    public long factorial(int n){
            if(n==0 || n==1)
                return 1;
            return n*factorial(n-1);
    }


    /** Método que devuelve a la Activity que llamó a esta el número "numero" que se pasa
     *  por parámetro.
     * @param numero el número a enviar a la otra Activity
     */
    private void devolverAMainActivity(long numero){
        Intent iDevolver=new Intent();
        iDevolver.putExtra(KEY_FACTORIAL,numero);
        setResult(RESULT_OK,iDevolver);
        finish();
    }
}
