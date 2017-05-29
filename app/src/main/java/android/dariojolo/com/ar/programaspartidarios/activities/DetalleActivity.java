package android.dariojolo.com.ar.programaspartidarios.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.dariojolo.com.ar.programaspartidarios.models.Programa;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;
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
    private TextView txtDia1;
    private TextView txtDia2;
    private TextView txtConductores;
    private ToggleButton btnLunes;
    private ToggleButton btnMartes;
    private ToggleButton btnMiercoles;
    private ToggleButton btnJueves;
    private ToggleButton btnViernes;
    private ToggleButton btnSabados;
    private ToggleButton btnDomingos;
    private ToggleButton btnPartidos;

    private int _fragment;

    private WebView webview;
    private FloatingActionButton fab;
    private boolean favorito;
    private final int INTERNET_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        fab = (FloatingActionButton)findViewById(R.id.fabDetalle);
        setToolbar();

        Bundle bundle = getIntent().getExtras();
        int _id = bundle.getInt("Programa");
        _fragment = bundle.getInt("Fragment");
        imagen = (ImageView) findViewById(R.id.imagenPrograma);
        txtNombre = (TextView) findViewById(R.id.txtNombre2);
        txtEmisora = (TextView) findViewById(R.id.txtEmisora);
        txtDia1 = (TextView) findViewById(R.id.txtDia1);
        txtDia2 = (TextView) findViewById(R.id.txtDia2);
        txtConductores = (TextView) findViewById(R.id.txtConductores);
        webview = (WebView)findViewById(R.id.webview);
/*        btnLunes = (ToggleButton) findViewById(R.id.lunes);
        btnMartes = (ToggleButton) findViewById(R.id.martes);
        btnMiercoles = (ToggleButton) findViewById(R.id.miercoles);
        btnJueves = (ToggleButton) findViewById(R.id.jueves);
        btnViernes = (ToggleButton) findViewById(R.id.viernes);
        btnSabados = (ToggleButton) findViewById(R.id.sabado);
        btnDomingos = (ToggleButton) findViewById(R.id.domingo);
        btnPartidos = (ToggleButton) findViewById(R.id.diaPartido); */


        // Toast.makeText(this, "En la pagina del detalle del programa "+ position, Toast.LENGTH_LONG).show();


        final Realm realm = Realm.getDefaultInstance();

        final Programa programa = realm.where(Programa.class).equalTo("Id", _id).findFirst();


        this.setTitle(programa.getNombre());
        imagen.setImageResource(programa.getImagen());
        txtNombre.setText(programa.getNombre());
        txtEmisora.setText(programa.getEmisora());
        txtDia1.setText(programa.getDiaUno());
        txtDia2.setText(programa.getDiaDos());
        txtConductores.setText(programa.getConductores());
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        //Comprobamos version de android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            NewerVersion();
        }else{
            OlderVersion();
        }

        /*if (programa.isLunes()) {
            btnLunes.setBackgroundResource(R.drawable.circuloazul);
        } else {
            btnLunes.setBackgroundResource(R.drawable.circulorojo);
        }
        if (programa.isMartes()) {
            btnMartes.setBackgroundResource(R.drawable.circuloazul);
        } else {
            btnMartes.setBackgroundResource(R.drawable.circulorojo);
        }
        if (programa.isMiercoles()) {
            btnMiercoles.setBackgroundResource(R.drawable.circuloazul);
        } else {
            btnMiercoles.setBackgroundResource(R.drawable.circulorojo);
        }
        if (programa.isJueves()) {
            btnJueves.setBackgroundResource(R.drawable.circuloazul);
        } else {
            btnJueves.setBackgroundResource(R.drawable.circulorojo);
        }
        if (programa.isViernes()) {
            btnViernes.setBackgroundResource(R.drawable.circuloazul);
        } else {
            btnViernes.setBackgroundResource(R.drawable.circulorojo);
        }
        if (programa.isSabado()) {
            btnSabados.setBackgroundResource(R.drawable.circuloazul);
        } else {
            btnSabados.setBackgroundResource(R.drawable.circulorojo);
        }
        if (programa.isDomingo()) {
            btnDomingos.setBackgroundResource(R.drawable.circuloazul);
        } else {
            btnDomingos.setBackgroundResource(R.drawable.circulorojo);
        }
        if (programa.isDiaPartido()) {
            btnPartidos.setBackgroundResource(R.drawable.circuloazul);
        } else {
            btnPartidos.setBackgroundResource(R.drawable.circulorojo);
        }*/
    if (programa.isFavorito()){
         fab.setImageResource(R.drawable.ic_star_on);
    }else{
        fab.setImageResource(R.drawable.ic_star_off);
    }
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (programa.isFavorito()){
                Toast.makeText(DetalleActivity.this,programa.getNombre() + " fue eliminado de favoritos",Toast.LENGTH_SHORT).show();
                fab.setImageResource(R.drawable.ic_star_off);
                updateFavorito(programa,false,realm);
            }else{
                Toast.makeText(DetalleActivity.this,programa.getNombre() + " fue agregado a favoritos",Toast.LENGTH_SHORT).show();
                fab.setImageResource(R.drawable.ic_star_on);
                updateFavorito(programa,true,realm);
            }
        }
    });
    }

    private void updateFavorito(Programa programa, boolean favorito,Realm realm) {
        realm.beginTransaction();
        programa.setFavorito(favorito);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_flechita);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetalleActivity.this, MainActivity.class);
                i.putExtra("Fragment", _fragment);
                startActivity(i);
            }
        });
    }
    private boolean CheckPermissions(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return  result == PackageManager.PERMISSION_GRANTED;
    }
    private void OlderVersion(){
        if (CheckPermissions(Manifest.permission.INTERNET)){
            String VIDEO_URL="http://tunein.com/embed/player/s248138/";
            String data_html = "<!DOCTYPE html><html> <head></head> <body> <iframe src=\""+VIDEO_URL+"\" style=\"width:100%;height:100px;\" scrolling=\"no\" frameborder=\"no\"></iframe> </body> </html> ";
            webview.loadDataWithBaseURL("http://tunein.com/", data_html, "text/html","UTF-8",null);
        }else{
            Toast.makeText(this,"No tiene permisos",Toast.LENGTH_SHORT).show();
        }
    }
    private void NewerVersion(){
        requestPermissions(new String[]{Manifest.permission.INTERNET}, INTERNET_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case INTERNET_CODE:
                String permission = permissions[0];
                int result = grantResults[0];
                if (permission.equals(Manifest.permission.INTERNET)){
                    //Comprobar si ha sido aceptado o denegado el permiso
                    if (result == PackageManager.PERMISSION_GRANTED){
                        //ha concedido el permiso
                        String VIDEO_URL="http://tunein.com/embed/player/s248138/";
                        String data_html = "<!DOCTYPE html><html> <head></head> <body> <iframe src=\""+VIDEO_URL+"\" style=\"width:100%;height:100px;\" scrolling=\"no\" frameborder=\"no\"></iframe> </body> </html> ";
                        webview.loadDataWithBaseURL("http://tunein.com/", data_html, "text/html","UTF-8",null);
                    }else{
                        //No ha concedido el permiso
                        Toast.makeText(this,"No concedió permisos",Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }


    }
}
