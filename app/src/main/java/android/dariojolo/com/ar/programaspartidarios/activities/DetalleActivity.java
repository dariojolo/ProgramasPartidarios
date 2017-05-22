package android.dariojolo.com.ar.programaspartidarios.activities;

import android.dariojolo.com.ar.programaspartidarios.R;
import android.dariojolo.com.ar.programaspartidarios.models.Programa;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import io.realm.Realm;


public class DetalleActivity extends AppCompatActivity {

    private ImageView imagen;
    private TextView txtNombre;
    private TextView txtEmisora;
    private TextView txtDias;
    private TextView txtHoraInicio;
    private TextView txtHoraFin;
    private TextView txtConductores;
    private ToggleButton btnLunes;
    private ToggleButton btnMartes;
    private ToggleButton btnMiercoles;
    private ToggleButton btnJueves;
    private ToggleButton btnViernes;
    private ToggleButton btnSabados;
    private ToggleButton btnDomingos;
    private ToggleButton btnPartidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle bundle = getIntent().getExtras();
        int _id = bundle.getInt("Programa");
        imagen = (ImageView)findViewById(R.id.imagenPrograma);
        txtNombre = (TextView)findViewById(R.id.txtNombre2);
        txtEmisora = (TextView)findViewById(R.id.txtEmisora);
        txtDias = (TextView)findViewById(R.id.txtDias2);
        txtHoraInicio = (TextView)findViewById(R.id.txtHoraInicio);
        txtHoraFin = (TextView)findViewById(R.id.txtHoraFin);
        txtConductores = (TextView)findViewById(R.id.txtConductores);
        btnLunes = (ToggleButton)findViewById(R.id.lunes);
        btnMartes = (ToggleButton)findViewById(R.id.martes);
        btnMiercoles = (ToggleButton)findViewById(R.id.miercoles);
        btnJueves = (ToggleButton)findViewById(R.id.jueves);
        btnViernes = (ToggleButton)findViewById(R.id.viernes);
        btnSabados = (ToggleButton)findViewById(R.id.sabado);
        btnDomingos = (ToggleButton)findViewById(R.id.domingo);
        btnPartidos = (ToggleButton)findViewById(R.id.diaPartido);



       // Toast.makeText(this, "En la pagina del detalle del programa "+ position, Toast.LENGTH_LONG).show();

        //actividadActual.getApplicationContext()).setLista(tuLista);

        Realm realm = Realm.getDefaultInstance();

        Programa programa = realm.where(Programa.class).equalTo("Id",_id).findFirst();
        this.setTitle(programa.getNombre());
        imagen.setImageResource(programa.getImagen());
        txtNombre.setText(programa.getNombre());
        txtEmisora.setText(programa.getEmisora());
        txtHoraInicio.setText(programa.getHoraInicio());
        txtHoraFin.setText(programa.getHoraFin());
        txtConductores.setText(programa.getConductores());
        if ( programa.isLunes()){
            btnLunes.setBackgroundResource(R.drawable.circuloazul);
        }else{
            btnLunes.setBackgroundResource(R.drawable.circulorojo);
        }
        if ( programa.isMartes()){
            btnMartes.setBackgroundResource(R.drawable.circuloazul);
        }else{
            btnMartes.setBackgroundResource(R.drawable.circulorojo);
        }
        if ( programa.isMiercoles()){
            btnMiercoles.setBackgroundResource(R.drawable.circuloazul);
        }else{
            btnMiercoles.setBackgroundResource(R.drawable.circulorojo);
        }
        if ( programa.isJueves()){
            btnJueves.setBackgroundResource(R.drawable.circuloazul);
        }else{
            btnJueves.setBackgroundResource(R.drawable.circulorojo);
        }
        if ( programa.isViernes()){
            btnViernes.setBackgroundResource(R.drawable.circuloazul);
        }else{
            btnViernes.setBackgroundResource(R.drawable.circulorojo);
        }
        if ( programa.isSabado()){
            btnSabados.setBackgroundResource(R.drawable.circuloazul);
        }else{
            btnSabados.setBackgroundResource(R.drawable.circulorojo);
        }
        if ( programa.isDomingo()){
            btnDomingos.setBackgroundResource(R.drawable.circuloazul);
        }else{
            btnDomingos.setBackgroundResource(R.drawable.circulorojo);
        }
        if ( programa.isDiaPartido()){
            btnPartidos.setBackgroundResource(R.drawable.circuloazul);
        }else{
            btnPartidos.setBackgroundResource(R.drawable.circulorojo);
        }
        Toast.makeText(this, "Nombre seleccionado: " + programa.getNombre(), Toast.LENGTH_LONG).show();


    }
}
