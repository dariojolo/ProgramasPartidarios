package android.dariojolo.com.ar.programaspartidarios.activities;

import android.dariojolo.com.ar.programaspartidarios.R;
import android.dariojolo.com.ar.programaspartidarios.models.Programa;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;


public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle bundle = getIntent().getExtras();

        int position = bundle.getInt("Posicion");

       // Toast.makeText(this, "En la pagina del detalle del programa "+ position, Toast.LENGTH_LONG).show();

        //actividadActual.getApplicationContext()).setLista(tuLista);
        Programa programaSeleccionado = (Programa) getIntent().getSerializableExtra("Programa");

        Toast.makeText(this, "Nombre seleccionado: " + programaSeleccionado.getNombre(), Toast.LENGTH_LONG).show();


    }
}
