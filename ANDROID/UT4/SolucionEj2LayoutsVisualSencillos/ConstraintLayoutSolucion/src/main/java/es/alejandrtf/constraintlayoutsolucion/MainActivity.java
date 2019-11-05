package es.alejandrtf.constraintlayoutsolucion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private int idLayout;
    private static String ID_LAYOUT="idLayout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idLayout=R.layout.inicial;

        if(savedInstanceState!=null)
        {
            idLayout=savedInstanceState.getInt(ID_LAYOUT);
        }
        setContentView(idLayout);
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
                idLayout=R.layout.type_here_layout; //lo guardo para luego restaurar el estado
                setContentView(R.layout.type_here_layout);
                return true;
            case R.id.layout_colores_centrados_option_menu:
                idLayout=R.layout.colores_centrados_layout; //lo guardo para luego restaurar el estado
                setContentView(R.layout.colores_centrados_layout);
                return true;
            case R.id.layout_colores_escalera_option_menu:
                idLayout=R.layout.colores_escalera_layout; //lo guardo para luego restaurar el estado
                setContentView(R.layout.colores_escalera_layout);
                return true;
            case R.id.layout_teclado_numeros_option_menu:
                idLayout=R.layout.teclado_numeros; //lo guardo para luego restaurar el estado
                setContentView(R.layout.teclado_numeros);
                return true;
            case R.id.layout_calculadora_option_menu:
                idLayout=R.layout.calculadora; //lo guardo para luego restaurar el estado
                setContentView(R.layout.calculadora);
                return true;
            default:
                setContentView(R.layout.type_here_layout);
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ID_LAYOUT,idLayout);

    }
}
