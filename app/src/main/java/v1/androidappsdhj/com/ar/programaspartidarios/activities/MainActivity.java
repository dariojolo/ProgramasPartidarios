package v1.androidappsdhj.com.ar.programaspartidarios.activities;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import io.realm.Realm;
import io.realm.RealmResults;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.AmFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.DomingoFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.FavoritosFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.FmFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.JuevesFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.LunesFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.MananaFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.MartesFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.MiercolesFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.NocheFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.PartidosFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.SabadoFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.TardeFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.TvFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.Fragments.ViernesFragment;
import v1.androidappsdhj.com.ar.programaspartidarios.R;
import v1.androidappsdhj.com.ar.programaspartidarios.models.Programa;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Bundle bundle;
    private SharedPreferences prefs;
    private RealmResults<Programa> programas;

    private int id;
    private Bundle extras;

    private int fragment_recuperado;
    private Realm realm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        extras = getIntent().getExtras();

        if (extras != null){
            try {
                Log.d("Algo llego", "TAG");
                id = Integer.parseInt(extras.getString("ID"));
                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("Programa", id);
                intent.putExtra("Fragment", 1);
                startActivity(intent);
            }catch (Exception ex){

            }
        }

        //Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
//        FirebaseMessaging.getInstance().subscribeToTopic("mundoAzulGrana");

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);

        //BORRAR ESTE CODIGOOOOOOOOOOOOOO
        //Realm realm;
        //realm = Realm.getDefaultInstance();
        //RealmResults<Programa> listado = realm.where(Programa.class).findAll();
        //for (Programa programa : listado){
        //    FirebaseMessaging.getInstance().subscribeToTopic(programa.getTopicNotificacion());
        //}
        //BORRAR ESTE CODIGOOOOOOOOOOOOOO HASTA ACCAAAAAAAAAAAAAAAAAAAAA

        setToolbar();
        try {
            bundle = getIntent().getExtras();
            int _fragment = bundle.getInt("Fragment");
            //Toast.makeText(this, "Fragment: " + _fragment, Toast.LENGTH_LONG).show();
            Fragment frag;

            if (_fragment == 0) {
                //frag = new MainFragment();
                //changeFragment(frag, navigationView.getMenu().getItem(0));
                prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                fragment_recuperado = prefs.getInt("fragment", -1);
                //Toast.makeText(this, "Fragment recuperado: " + fragment_recuperado, Toast.LENGTH_LONG).show();
                if (fragment_recuperado == -1){
                    setFragmentByDefault();
                }else{
                    verFragment(fragment_recuperado);
                }
            }else{
               // prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
               // fragment_recuperado = prefs.getInt("fragment", -1);
                verFragment(_fragment);
            }

        } catch (Exception ex) {
            setFragmentByDefault();
        }

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //       Toast.makeText(MainActivity.this,"OPEN", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //      Toast.makeText(MainActivity.this,"CLOSE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransition = false;
                Fragment fragment = null;

                switch (item.getItemId()) {
               /*     case R.id.todas:
                        fragment = new MainFragment();
                        fragmentTransition = true;
                        break; */
                    case R.id.radioam:
                        fragment = new AmFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.radiofm:
                        fragment = new FmFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.radiopartidos:
                        fragment = new PartidosFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.radioFavoritos:
                        fragment = new FavoritosFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.television:
                        fragment = new TvFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_lunes:
                        fragment = new LunesFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_martes:
                        fragment = new MartesFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_miercoles:
                        fragment = new MiercolesFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_jueves:
                        fragment = new JuevesFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_viernes:
                        fragment = new ViernesFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_sabado:
                        fragment = new SabadoFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_domingo:
                        fragment = new DomingoFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_manana:
                        fragment = new MananaFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_tarde:
                        fragment = new TardeFragment();
                        fragmentTransition = true;
                        break;
                    case R.id.menu_noche:
                        fragment = new NocheFragment();
                        fragmentTransition = true;
                        break;
                }
                if (fragmentTransition) {
                    changeFragment(fragment, item);
                    drawer.closeDrawers();
                }
                return true;
            }
        });
    }

    private void verFragment(int fragment_recuperado) {
        Fragment frag;
        if (fragment_recuperado == 1) {
            frag = new AmFragment();
            changeFragment(frag, navigationView.getMenu().getItem(0));
        } else if (fragment_recuperado == 2) {
            frag = new FmFragment();
            changeFragment(frag, navigationView.getMenu().getItem(1));
        } else if (fragment_recuperado == 3) {
            frag = new PartidosFragment();
            changeFragment(frag, navigationView.getMenu().getItem(2));
        } else if (fragment_recuperado == 4) {
            frag = new FavoritosFragment();
            changeFragment(frag, navigationView.getMenu().getItem(4));
        } else if (fragment_recuperado == 5) {
            frag = new TvFragment();
            changeFragment(frag, navigationView.getMenu().getItem(3));
        } else if (fragment_recuperado == 6) {
            frag = new LunesFragment();
            changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(0));
        } else if (fragment_recuperado == 7) {
            frag = new MartesFragment();
            changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(1));
        } else if (fragment_recuperado == 8) {
            frag = new MiercolesFragment();
            changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(2));
        } else if (fragment_recuperado == 9) {
            frag = new JuevesFragment();
            changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(3));
        } else if (fragment_recuperado == 10) {
            frag = new ViernesFragment();
            changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(4));
        } else if (fragment_recuperado == 11) {
            frag = new SabadoFragment();
            changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(5));
        } else if (fragment_recuperado == 12) {
            frag = new DomingoFragment();
            changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(6));
        } else if (fragment_recuperado == 13) {
            frag = new MananaFragment();
            changeFragment(frag, navigationView.getMenu().getItem(6).getSubMenu().getItem(0));
        } else if (fragment_recuperado == 14) {
            frag = new TardeFragment();
            changeFragment(frag, navigationView.getMenu().getItem(6).getSubMenu().getItem(1));
        } else if (fragment_recuperado == 15) {
            frag = new NocheFragment();
            changeFragment(frag, navigationView.getMenu().getItem(6).getSubMenu().getItem(2));
        }
    }

    //Probando si este metodo funciona, intentar recuperar la ultima pantalla visitada

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void changeFragment(Fragment fragment, MenuItem item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    private void setFragmentByDefault() {
        //changeFragment(new MainFragment(), navigationView.getMenu().getItem(0));
        changeFragment(new AmFragment(), navigationView.getMenu().getItem(0));
    }

    /*  @Override
      public boolean onOptionsItemSelected(MenuItem item) {
          switch(item.getItemId()){
              case android.R.id.home:
                  //Logica de menu lateral
                  drawer.openDrawer(GravityCompat.START);
                  return true;
          }

          return super.onOptionsItemSelected(item);
      }*/
    //Inflamos el layout del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    //Manejamos la funcionalidad del menu de opciones
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Logica de menu lateral
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.sugerencia:
                //Llamamos a la ventana de envio de correo electronico
                showAlertParaContactar("Contáctenos", "");
                return true;
            case R.id.sobreNosotros:
                //Llamamos a la ventana del disclaimer
                showAlertParaDisclaimer("Sobre nosotros", "");
                return true;
            case R.id.notificarTodos:
                agregarTodasLasNotificaciones();
                return true;
            case R.id.notificarNinguno:
                eliminarTodasLasNotificaciones();
                return true;
            case R.id.privacyPolicy:
                verPolitica();
                return true;

                default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void verPolitica() {
        Uri webpage = Uri.parse("https://programaspartidarios.firebaseapp.com/");
        Intent intent2 = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent2.resolveActivity(getPackageManager()) != null) {
            startActivity(intent2);
        }
    }

    private void eliminarTodasLasNotificaciones() {
        realm = Realm.getDefaultInstance();
        programas = realm.where(Programa.class).findAll();
        for (Programa programa : programas){
            FirebaseMessaging.getInstance().unsubscribeFromTopic(programa.getTopicNotificacion());
            realm.beginTransaction();
            programa.setNotificar(false);
            realm.copyToRealmOrUpdate(programa);
            realm.commitTransaction();
        }
        Toast.makeText(this,"Se han eliminado las notificaciones de los programas",Toast.LENGTH_SHORT).show();
    }

    private void agregarTodasLasNotificaciones() {
        realm = Realm.getDefaultInstance();
        programas = realm.where(Programa.class).findAll();
        for (Programa programa : programas){
            FirebaseMessaging.getInstance().subscribeToTopic(programa.getTopicNotificacion());
            realm.beginTransaction();
            programa.setNotificar(true);
            realm.copyToRealmOrUpdate(programa);
            realm.commitTransaction();
        }
        Toast.makeText(this,"Se ha suscripto a las notificaciones de todos los programas",Toast.LENGTH_SHORT).show();
    }

    private void showAlertParaContactar(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (titulo != null) {
            builder.setTitle(titulo);
        }
        if (mensaje != null) {
            builder.setMessage(mensaje);
        }
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.layout_contactenos, null);
        builder.setView(viewInflated);
        //final ToggleButton btnLunes = (ToggleButton)viewInflated.findViewById(R.id.btnLunes);
        final EditText txtSubject = (EditText) viewInflated.findViewById(R.id.txtSubject);
        final EditText txtTexto = (EditText) viewInflated.findViewById(R.id.txtTexto);

        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent2 = new Intent(Intent.ACTION_SENDTO);
                //intent2.setType("*/*");
                intent2.setData(Uri.parse("mailto:androidappsdj@gmail.com")); // only email apps should handle this
                intent2.putExtra(Intent.EXTRA_EMAIL, "androidappsdj@gmail.com");
                intent2.putExtra(Intent.EXTRA_SUBJECT, txtSubject.getText().toString());
                intent2.putExtra(Intent.EXTRA_TEXT, txtTexto.getText().toString());
                if (intent2.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent2);
                }
                Toast.makeText(getApplicationContext(), "Gracias por ponerse en contacto con nosotros", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Ventana para agregar un nuevo Programa
    private void showAlertParaDisclaimer(String titulo, String mensaje) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (titulo != null) {
            builder.setTitle(titulo);
        }
        if (mensaje != null) {
            builder.setMessage(mensaje);
        }
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.layout_disclaimer, null);
        builder.setView(viewInflated);
        //final ToggleButton btnLunes = (ToggleButton)viewInflated.findViewById(R.id.btnLunes);
        final TextView txtDisclaimer = (TextView) viewInflated.findViewById(R.id.txtDisclaimer);

        txtDisclaimer.setText("Aplicación con información sobre los programas partidarios que cubren y transmiten al Club Atlético San Lorenzo de Almagro" +
                " \nTodos los datos incluidos en esta aplicación fueron extraídos de la página oficial de San Lorenzo de Almagro y de las redes sociales de cada programa." +
                " \nSi alguna información mostrada en esta aplicación infringe alguna restricción de copyright, por favor contáctenos y eliminaremos inmediatamente dicha información de la aplicación." +
                " \nSi algún dato es erróneo o cambió, por favor notifíquenos del mismo así podremos corregirlo. " +
                " \nEl día y horario de los programas es responsabilidad de los mismos, así como la transmisión online es responsabilidad de cada radio");


        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(),"OK", Toast.LENGTH_SHORT).show();
            }
        });
        // builder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //setFragmentByDefault();
       // Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
   //     try {
            //bundle = getIntent().getExtras();
            //int _fragment = bundle.getInt("Fragment");

            //Toast.makeText(this, "Fragment guardado: " + _fragment, Toast.LENGTH_LONG).show();
     //   } catch (Exception ex) {
     //       Toast.makeText(this, "Exception: " + ex.getMessage().toString(), Toast.LENGTH_LONG).show();
     //   }
    }

    @Override
    protected void onStop() {
        super.onStop();
       // Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(this, "onRestart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        try {
            prefs.edit().remove("fragment").apply();
        }catch (Exception ex){

        }
    }
}

