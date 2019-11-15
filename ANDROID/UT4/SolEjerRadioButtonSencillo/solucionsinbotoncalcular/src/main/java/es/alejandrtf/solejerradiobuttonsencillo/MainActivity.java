package es.alejandrtf.solejerradiobuttonsencillo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etValorAConvertir;
    private TextView tvResultado;
    private RadioGroup rgOpcionesConversion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReferences();
        setListeners();
    }

    /**
     * Método que obtiene las referencias a las vistas XML
     */
    private void initReferences() {
        etValorAConvertir = findViewById(R.id.etTemperatura);
        rgOpcionesConversion = findViewById(R.id.rgOpcionesConversion);
        tvResultado = findViewById(R.id.tvResultadoConversion);

    }


    /** Método que asigna el listener al RadioGroup y si hubiese otras vistas con él
     *  también lo haría aquí.
     */
    private void setListeners(){
        rgOpcionesConversion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int idChecked) {
                convertir(idChecked);
            }
        });
    }

    /**
     * Método realiza o bien la conversión de ºC a ºF o viceversa en función
     * del parámetro que recibe que corresponde con el radioButton pulsado
     *
     * @param idChecked el id del RadioButton pulsado o elegido
     */
    public void convertir(int idChecked) {
        if (TextUtils.isEmpty(etValorAConvertir.getText())) {
            etValorAConvertir.setError(getString(R.string.error_no_hay_valor));
            return;
        }
        double resultado = (idChecked == R.id.rbCelsius)
                ? fahrenheitToCelsius(Double.parseDouble(etValorAConvertir.getText().toString()))
                : celsiusToFahrenheit(Double.parseDouble(etValorAConvertir.getText().toString()));
        tvResultado.setText(String.format("%.2f", resultado));
    }


    /**
     * Método convierte de grados Celsius a Fahrenheit
     *
     * @param valor a convertir (ºC)
     * @return un double que son ºF
     */
    public double celsiusToFahrenheit(double valor) {
        if (valor == 0) return 32;
        return ((valor * 9 / 5) + 32);
    }


    /**
     * Método convierte de grados Fahrenheit a  Celsius
     *
     * @param valor a convertir (ºF)
     * @return un double que son ºC
     */
    public double fahrenheitToCelsius(double valor) {
        if (valor == 32) return 0;
        return ((valor - 32) * 5 / 9);
    }

    /** Fórmula conversión:
     *
     *  (x °C × 9 / 5) + 32 = y °F
     */
}
