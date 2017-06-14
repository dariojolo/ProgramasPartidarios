package android.dariojolo.com.ar.programaspartidarios.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.dariojolo.com.ar.programaspartidarios.app.MyApp;
import android.dariojolo.com.ar.programaspartidarios.models.Programa;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import io.realm.Realm;



public class DetalleActivity extends AppCompatActivity {

    private ImageView imagen;
    private TextView txtNombre;
    private TextView txtEmisora;
    private TextView txtDias;
    private TextView txtDia1;
    private TextView txtDia2;
    private TextView txtConductores;
    private TextView txtWeb;
    private TextView txtTel;
    private TextView txtFace;
    private TextView txtTwi;
    private int _fragment;
    private WebView webview;
    private android.support.design.widget.FloatingActionButton fab;
    private android.support.design.widget.FloatingActionButton fabNotification;
    private android.support.design.widget.FloatingActionButton fabfavorite;
    private LinearLayout notificationLinear;
    private LinearLayout favouriteLinear;
    private boolean favorito;
    private final int INTERNET_CODE = 100;
    private final int PHONE_CODE = 101;
    private final int READ_EXTERNAL_STORAGE_CODE = 102;

    InterstitialAd interstitialAd; // Publicidad Pantalla Completa

    FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);



        if (MyApp.contadorPantallas%3 == 0) {

            interstitialAd = new InterstitialAd(this);
            interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            AdRequest adRequest1 = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest1);
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    interstitialAd.show();
                }
            });
        }
        MyApp.contadorPantallas++;
       /* fab = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fabDetalle);
        fabNotification = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fabNotificar);
        fabfavorite = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fabfavorite);
        notificationLinear = (LinearLayout) findViewById(R.id.notificationLayout);
        favouriteLinear = (LinearLayout) findViewById(R.id.favoriteLayout);
        notificationLinear.setVisibility(View.GONE);
        favouriteLinear.setVisibility(View.GONE);*/

        setToolbar();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notificationLinear.getVisibility() == View.VISIBLE &&
                        favouriteLinear.getVisibility() == View.VISIBLE){
                    notificationLinear.setVisibility(View.GONE);
                    favouriteLinear.setVisibility(View.GONE);
                }else{
                    notificationLinear.setVisibility(View.VISIBLE);
                    favouriteLinear.setVisibility(View.VISIBLE);
                }
            }
        });*/
        /*fab2 = (FloatingActionButton)findViewById(R.id.fab);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetalleActivity.this,"Se apreto boton",Toast.LENGTH_SHORT).show();
                MyFirebaseInstanceIDService myFID = new MyFirebaseInstanceIDService();
                myFID.onTokenRefresh();
            }
        });*/
        Bundle bundle = getIntent().getExtras();
        int _id = bundle.getInt("Programa");
        _fragment = bundle.getInt("Fragment");
        imagen = (ImageView) findViewById(R.id.imagenPrograma);
        txtNombre = (TextView) findViewById(R.id.txtNombre2);
        txtEmisora = (TextView) findViewById(R.id.txtEmisora);
        txtDia1 = (TextView) findViewById(R.id.txtDia1);
        txtDia2 = (TextView) findViewById(R.id.txtDia2);
        txtConductores = (TextView) findViewById(R.id.txtConductores);
        webview = (WebView) findViewById(R.id.webview);
        txtWeb = (TextView) findViewById(R.id.txtWeb);
        txtTel = (TextView) findViewById(R.id.txtTelefono);
        txtFace = (TextView) findViewById(R.id.txtFacebook);
        txtTwi = (TextView) findViewById(R.id.txtTwitter);


        final Realm realm = Realm.getDefaultInstance();

        final Programa programa = realm.where(Programa.class).equalTo("Id", _id).findFirst();


        this.setTitle(programa.getNombre());
        imagen.setImageResource(programa.getImagen());
        txtNombre.setText(programa.getNombre());
        txtEmisora.setText(programa.getEmisora());
        txtDia1.setText(programa.getDiaUno());
        txtDia2.setText(programa.getDiaDos());
        txtConductores.setText(programa.getConductores());
        txtTel.setText("   " + programa.getTelefono());
        txtWeb.setText("   " + programa.getWeb());
        txtFace.setText("   " + programa.getFacebook());
        txtTwi.setText("   " + programa.getTwitter());

        txtTwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtTwi.getText().toString() != "") {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(programa.getTwitter().toString()));
                    startActivity(i);
                }
            }
        });
        txtFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtFace.getText().toString() != "") {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(programa.getFacebook().toString()));
                    startActivity(i);
                }
            }
        });
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        //Comprobamos version de android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NewerVersion();
        } else {
            OlderVersion();
        }

      /*  if (programa.isFavorito()) {
            fabfavorite.setImageResource(R.drawable.ic_star_on);
        } else {
            fabfavorite.setImageResource(R.drawable.ic_star_off);
        }
        fabfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (programa.isFavorito()) {
                    Toast.makeText(DetalleActivity.this, programa.getNombre() + " fue eliminado de favoritos", Toast.LENGTH_SHORT).show();
                    fabfavorite.setImageResource(R.drawable.ic_star_off);
                    updateFavorito(programa, false, realm);
                } else {
                    Toast.makeText(DetalleActivity.this, programa.getNombre() + " fue agregado a favoritos", Toast.LENGTH_SHORT).show();
                    fabfavorite.setImageResource(R.drawable.ic_star_on);
                    updateFavorito(programa, true, realm);
                }
            }
        });*/
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                if (programa.isFavorito()) {
                    floatingActionButton1.setImageResource(R.drawable.ic_favorite); //on
                } else {
                    floatingActionButton1.setImageResource(R.drawable.ic_favorite); //off
                }
                floatingActionButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (programa.isFavorito()) {
                            Toast.makeText(DetalleActivity.this, programa.getNombre() + " fue eliminado de favoritos", Toast.LENGTH_SHORT).show();
                            floatingActionButton1.setImageResource(R.drawable.ic_favorite); //off
                            updateFavorito(programa, false, realm);
                        } else {
                            Toast.makeText(DetalleActivity.this, programa.getNombre() + " fue agregado a favoritos", Toast.LENGTH_SHORT).show();
                            floatingActionButton1.setImageResource(R.drawable.ic_favorite); //on
                            updateFavorito(programa, true, realm);
                        }
                    }
                });

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked

            }
        });

    }


    private void updateFavorito(Programa programa, boolean favorito, Realm realm) {
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
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("Fragment", _fragment);
                startActivity(i);
            }
        });
    }

    private boolean CheckPermissions(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void OlderVersion() {
        if (CheckPermissions(Manifest.permission.INTERNET)) {
            String VIDEO_URL = "http://tunein.com/embed/player/s248138/";
            String data_html = "<!DOCTYPE html><html> <head></head> <body> <iframe src=\"" + VIDEO_URL + "\" style=\"width:100%;height:100px;\" scrolling=\"no\" frameborder=\"no\"></iframe> </body> </html> ";
            webview.loadDataWithBaseURL("http://tunein.com/", data_html, "text/html", "UTF-8", null);
        } else {
            Toast.makeText(this, "No tiene permisos", Toast.LENGTH_SHORT).show();
        }
    }

    private void NewerVersion() {
        requestPermissions(new String[]{Manifest.permission.INTERNET}, INTERNET_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        String permission;
        int result;
        switch (requestCode) {
            case INTERNET_CODE:
                permission = permissions[0];
                result = grantResults[0];
                if (permission.equals(Manifest.permission.INTERNET)) {
                    //Comprobar si ha sido aceptado o denegado el permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //ha concedido el permiso
                        String VIDEO_URL = "http://tunein.com/embed/player/s248138/";
                        String data_html = "<!DOCTYPE html><html> <head></head> <body> <iframe src=\"" + VIDEO_URL + "\" style=\"width:100%;height:100px;\" scrolling=\"no\" frameborder=\"no\"></iframe> </body> </html> ";
                        webview.loadDataWithBaseURL("http://tunein.com/", data_html, "text/html", "UTF-8", null);
                    } else {
                        //No ha concedido el permiso
                        Toast.makeText(this, "No concedió permisos", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case PHONE_CODE:
                permission = permissions[0];
                result = grantResults[0];
                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    //Comprobar si ha sido aceptado o denegado el permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //ha concedido el permiso
                        //Meter codigo de llamada
                    } else {
                        //No ha concedido el permiso
                        Toast.makeText(this, "No concedió permisos", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }


    }

}
