package android.dariojolo.com.ar.programaspartidarios.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.dariojolo.com.ar.programaspartidarios.Fragments.AmFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.DomingoFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.FavoritosFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.FmFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.JuevesFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.LunesFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.MainFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.MananaFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.MartesFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.MiercolesFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.NocheFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.PartidosFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.SabadoFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.TardeFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.TvFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.ViernesFragment;
import android.dariojolo.com.ar.programaspartidarios.R;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //FirebaseMessaging.getInstance().subscribeToTopic("mundoAzulGrana");


        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navview);

        setToolbar();
        try {
            Bundle bundle = getIntent().getExtras();
            int _fragment = bundle.getInt("Fragment");
            Fragment frag;

            if (_fragment == -1){
                frag =  new MainFragment();
                changeFragment(frag, navigationView.getMenu().getItem(0));
            }else if (_fragment == 1){
                frag =  new AmFragment();
                changeFragment(frag, navigationView.getMenu().getItem(0));
            }else if (_fragment == 2){
                frag =  new FmFragment();
                changeFragment(frag, navigationView.getMenu().getItem(1));
            }else if (_fragment == 3){
                frag =  new PartidosFragment();
                changeFragment(frag, navigationView.getMenu().getItem(2));
            }else if (_fragment == 4){
                frag =  new FavoritosFragment();
                changeFragment(frag, navigationView.getMenu().getItem(4));
            }else if (_fragment == 5) {
                frag = new TvFragment();
                changeFragment(frag, navigationView.getMenu().getItem(3));
            }else if (_fragment == 6) {
                frag = new LunesFragment();
                changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(0));
            }else if (_fragment == 7) {
                frag = new MartesFragment();
                changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(1));
            }else if (_fragment == 8) {
                frag = new MiercolesFragment();
                changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(2));
            }else if (_fragment == 9) {
                frag = new JuevesFragment();
                changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(3));
            }else if (_fragment == 10) {
                frag = new ViernesFragment();
                changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(4));
            }else if (_fragment == 11) {
                frag = new SabadoFragment();
                changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(5));
            }else if (_fragment == 12) {
                frag = new DomingoFragment();
                changeFragment(frag, navigationView.getMenu().getItem(5).getSubMenu().getItem(6));
            }else if (_fragment == 13) {
                frag = new MananaFragment();
                changeFragment(frag, navigationView.getMenu().getItem(6).getSubMenu().getItem(0));
            }else if (_fragment == 14) {
                frag = new TardeFragment();
                changeFragment(frag, navigationView.getMenu().getItem(6).getSubMenu().getItem(1));
            }else if (_fragment == 15) {
                frag = new NocheFragment();
                changeFragment(frag, navigationView.getMenu().getItem(6).getSubMenu().getItem(2));
            }

        }catch (Exception ex){
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

                switch (item.getItemId()){
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
                if (fragmentTransition){
                    changeFragment(fragment,item);
                    drawer.closeDrawers();
                }
                return true;
            }
        });
    }

    //Probando si este metodo funciona, intentar recuperar la ultima pantalla visitada
 /*   @Override
    protected void onResume() {
        super.onResume();
        setFragmentByDefault();
    }*/

    private void setToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void changeFragment(Fragment fragment, MenuItem item){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame,fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    private void setFragmentByDefault(){
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
        switch(item.getItemId()){
            case android.R.id.home:
                //Logica de menu lateral
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.sugerencia:
                //Llamamos a la ventana de envio de correo electronico
                showAlertParaContactar("Contáctenos","");
                return true;
            case R.id.sobreNosotros:
                //Llamamos a la ventana del disclaimer
                showAlertParaDisclaimer("Sobre nosotros","");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAlertParaContactar(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (titulo!= null){
            builder.setTitle(titulo);
        }
        if (mensaje != null){
            builder.setMessage(mensaje);
        }
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.layout_contactenos, null);
        builder.setView(viewInflated);
        //final ToggleButton btnLunes = (ToggleButton)viewInflated.findViewById(R.id.btnLunes);
        final EditText txtSubject = (EditText)viewInflated.findViewById(R.id.txtSubject);
        final EditText txtTexto = (EditText)viewInflated.findViewById(R.id.txtTexto);

        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent2 = new Intent(Intent.ACTION_SENDTO);
                //intent2.setType("*/*");
                intent2.setData(Uri.parse("mailto:dariojolo@gmail.com")); // only email apps should handle this
                intent2.putExtra(Intent.EXTRA_EMAIL, "dariojolo@gmail.com");
                intent2.putExtra(Intent.EXTRA_SUBJECT, txtSubject.getText().toString());
                intent2.putExtra(Intent.EXTRA_TEXT, txtTexto.getText().toString());
                if (intent2.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent2);
                }
                Toast.makeText(getApplicationContext(),"Gracias por ponerse en contacto con nosotros", Toast.LENGTH_SHORT).show();
            }
        });
         builder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Ventana para agregar un nuevo Programa
        private void showAlertParaDisclaimer(String titulo, String mensaje){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if (titulo!= null){
                builder.setTitle(titulo);
            }
            if (mensaje != null){
                builder.setMessage(mensaje);
            }
            View viewInflated = LayoutInflater.from(this).inflate(R.layout.layout_disclaimer, null);
            builder.setView(viewInflated);
            //final ToggleButton btnLunes = (ToggleButton)viewInflated.findViewById(R.id.btnLunes);
            final TextView txtDisclaimer = (TextView)viewInflated.findViewById(R.id.txtDisclaimer);

            txtDisclaimer.setText("Aplicación con información sobre los programas partidarios que cubren y transmiten al Club Atlético San Lorenzo de Almagro"   +
                    " \nTodos los datos incluidos en esta aplicación fueron extraídos de la página oficial de San Lorenzo de Almagro y de las redes sociales de cada programa." +
                    " \nSi alguna información mostrada en esta aplicación infringe alguna restricción de copyright, por favor contáctenos y eliminaremos inmediatamente dicha información de la aplicación." +
                    " \nSi algún dato es erróneo o cambió, por favor notifíquenos del mismo así podremos corregirlo. " +
                    " \nEl logo fue creado con un Fondo de vector creado por Macrovector - Freepik.com (http://www.freepik.es/fotos-vectores-gratis/fondo)");


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
        //Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this,"onPause",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(this,"onPause",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show();
    }

}

