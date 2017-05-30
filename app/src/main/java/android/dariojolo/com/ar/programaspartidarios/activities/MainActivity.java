package android.dariojolo.com.ar.programaspartidarios.activities;


import android.dariojolo.com.ar.programaspartidarios.Fragments.AmFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.FavoritosFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.FmFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.MainFragment;
import android.dariojolo.com.ar.programaspartidarios.Fragments.PartidosFragment;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navview);

        setToolbar();
        try {
            Bundle bundle = getIntent().getExtras();
            int _fragment = bundle.getInt("Fragment");
            Fragment frag;
            if (_fragment == 1){
                frag =  new MainFragment();
                changeFragment(frag, navigationView.getMenu().getItem(0));
            }else if (_fragment == 2){
                frag =  new AmFragment();
                changeFragment(frag, navigationView.getMenu().getItem(1));
            }else if (_fragment == 3){
                frag =  new FmFragment();
                changeFragment(frag, navigationView.getMenu().getItem(2));
            }else if (_fragment == 4){
                frag =  new PartidosFragment();
                changeFragment(frag, navigationView.getMenu().getItem(3));
            }else if (_fragment == 5){
                frag =  new FavoritosFragment();
                changeFragment(frag, navigationView.getMenu().getItem(4));
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
                    case R.id.todas:
                        fragment = new MainFragment();
                        fragmentTransition = true;
                        break;
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
                }
                if (fragmentTransition){
                    changeFragment(fragment,item);
                    drawer.closeDrawers();
                }
                return true;
            }
        });
    }

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
       changeFragment(new MainFragment(), navigationView.getMenu().getItem(0));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                //Logica de menu lateral
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //Inflamos el layout del menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    //Manejamos la funcionalidad del menu de opciones
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_item:
                //Añadimos nuevo nombre
                this.nombres.add("Agregado nº: " + (++contador ));
                //Notificamos al adaptador del cambio
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

}
