package es.alejandrtf.constraintlayoutsolucion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.layout_type_here_option_menu:
                setContentView(R.layout.type_here_layout);
                return true;
            case R.id.layout_colores_centrados_option_menu:
                setContentView(R.layout.colores_centrados_layout);
                return true;
            case R.id.layout_colores_escalera_option_menu:
                setContentView(R.layout.colores_escalera_layout);
                return true;
            case R.id.layout_teclado_numeros_option_menu:
                setContentView(R.layout.teclado_numeros);
                return true;
            case R.id.layout_calculadora_option_menu:
                setContentView(R.layout.calculadora);
                return true;
            default:
                setContentView(R.layout.type_here_layout);
                return super.onOptionsItemSelected(item);
        }
    }
}
