package v1.androidappsdhj.com.ar.programaspartidarios.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import v1.androidappsdhj.com.ar.programaspartidarios.R;
import v1.androidappsdhj.com.ar.programaspartidarios.activities.DetalleActivity;
import v1.androidappsdhj.com.ar.programaspartidarios.adapters.MyAdapterListView;
import v1.androidappsdhj.com.ar.programaspartidarios.models.Programa;
import v1.androidappsdhj.com.ar.programaspartidarios.models.ProgramaSQLiteHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmFragment extends Fragment implements RealmChangeListener<RealmResults<Programa>> {

    private List<Programa> programas;
    //private RecyclerView recycler;
    private ListView listView;
    //private RecyclerView.Adapter adapter;
    private MyAdapterListView myAdapter;
   // private RecyclerView.LayoutManager layoutManager;
    private int contador = 0;

    //private Realm realm;
    //private RealmResults<Programa> programasR;
    private List<Programa>listadoProg;

    private View view;

    private ProgramaSQLiteHelper programaHelper;
    private SQLiteDatabase db;

    public AmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_am, container, false);
        //RelativeLayout fl = (RelativeLayout) inflater.inflate(R.layout.fragment_am, container, false);
        //recycler = (RecyclerView) fl.findViewById(R.id.recyclerView2);

        SharedPreferences prefs;
        prefs = this.getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("fragment", 1);
        editor.apply();

        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        MobileAds.initialize(getActivity().getApplicationContext(), getResources().getString(R.string.app_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //realm = Realm.getDefaultInstance();

      /*  programasR.clear();
        adapter.notifyDataSetChanged();*/


        //programasR = getAllProgramasR();
        //programasR.addChangeListener(this);

        listadoProg = new ArrayList<Programa>();
        listadoProg.clear();
        listadoProg = getAllProgramasSQL();

        // programas = getAllProgramas();
        listView = (ListView)view.findViewById(R.id.listView);
       // recycler = (RecyclerView) view.findViewById(R.id.recyclerView2);
       // layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

     /*   adapter = new MyAdapter(programasR, R.layout.list_item_recycler, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Programa programa, int position) {
                //deletePrograma(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalleActivity.class);
                intent.putExtra("Programa", programa.getId());
                intent.putExtra("Fragment", 1);
                startActivity(intent);
            }
        });*/

        //recycler.getRecycledViewPool().clear();
        //Este metodo se puede usar cuando sabemos que el layout del recycler no van a cambiar de tamaño
        //recycler.setHasFixedSize(true);
        //Se le agrega una animacion por defecto
        //recycler.setItemAnimator(new DefaultItemAnimator());
        //recycler.setLayoutManager(layoutManager);
        //recycler.setAda   pter(adapter);




        myAdapter = new MyAdapterListView(getContext(), R.layout.list_item_listview,listadoProg);
        myAdapter.notifyDataSetChanged();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalleActivity.class);
                intent.putExtra("Programa", listadoProg.get(position).getId());
                intent.putExtra("Fragment", 1);
                startActivity(intent);
            }
        });
<<<<<<< HEAD
<<<<<<< HEAD
=======
        //adapter.notifyItemRangeChanged(26,27);
        myAdapter.notifyDataSetChanged();
        update();
>>>>>>> parent of cf4596a... Cambiando a ListView IV no se ven los listados

=======
        //adapter.notifyItemRangeChanged(26,27);
>>>>>>> parent of 027a0f0... Migrando a SQLite III
        myAdapter.notifyDataSetChanged();
        //update();

        return view;
    }


  /*  private RealmResults<Programa> getAllProgramasR() {
        //return realm.where(Programa.class).findAll();
        return realm.where(Programa.class).equalTo("medio", "AM").findAllSorted("nombre", Sort.ASCENDING);
    }*/
    private List<Programa>getAllProgramasSQL(){

        programaHelper = new ProgramaSQLiteHelper(getContext(),"Programas1",null,1);
        db = programaHelper.getReadableDatabase();
        //Seleccionamos los registros a mostrar en la lista
        Cursor cursor = db.rawQuery("select * from programas where _medio = 'AM'",null);
        List<Programa> lista = new ArrayList<Programa>();

        if (cursor.moveToFirst()){
            //iteramos sobre el cursor de resultados
            //Llenamos el array que vamos a devolver
            while(cursor.isAfterLast() == false){
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                String _nombre = cursor.getString(cursor.getColumnIndex("_nombre"));
                //int _imagen = cursor.getInt(cursor.getColumnIndex("_imagen"));
                String _conductores = cursor.getString(cursor.getColumnIndex("_conductores"));
                String _emisora = cursor.getString(cursor.getColumnIndex("_emisora"));
                //String _email = cursor.getString(cursor.getColumnIndex("_eMail"));
                //String _web = cursor.getString(cursor.getColumnIndex("_web"));
                //String _twitter = cursor.getString(cursor.getColumnIndex("_twitter"));
                //String _facebook = cursor.getString(cursor.getColumnIndex("_facebook"));
                //String _telefono = cursor.getString(cursor.getColumnIndex("_telefono"));
                //boolean _lunes = cursor.getInt(8) > 0;
                //boolean _martes = cursor.getInt(9) > 0;
                //boolean _miercoles = cursor.getInt(10) > 0;
                //boolean _jueves = cursor.getInt(11) > 0;
                //boolean _viernes = cursor.getInt(12) > 0;
                //boolean _sabado = cursor.getInt(13) > 0;
                //boolean _domingo = cursor.getInt(14) > 0;
                //boolean _diaPartido = cursor.getInt(15) > 0;
                //String _diaUno = cursor.getString(cursor.getColumnIndex("_diaUno"));
                //String _diaDos = cursor.getString(cursor.getColumnIndex("_diaDos"));
                //String _medio = cursor.getString(cursor.getColumnIndex("_medio"));
                //String _link = cursor.getString(cursor.getColumnIndex("_link"));
                //boolean _favorito = cursor.getInt(16) > 0;
                //boolean _notificar = cursor.getInt(17) > 0;
                //String _topicNotification = cursor.getString(cursor.getColumnIndex("_topicNotificacion"));
                //boolean _manana = cursor.getInt(18) > 0;
                //boolean _tarde = cursor.getInt(19) > 0;
                //boolean _noche = cursor.getInt(20) > 0;

                //lista.add(new Programa(_nombre,_imagen,_conductores,_emisora,_email,_web,_twitter,_facebook,_telefono,_lunes,_martes,_miercoles,_jueves,_viernes,_sabado,_domingo,_diaPartido,_diaUno,_diaDos,_medio,_link,_favorito,_notificar,_topicNotification,_manana,_tarde,_noche));
                cursor.moveToNext();
            }
        }
        return lista;
    }

    private void update(){
        //Borramos todos los elementos
        programas.clear();
        //Cargamos todos los elementos
        programas.addAll(getAllProgramasSQL());
        //Refrescamos el adaptador
        myAdapter.notifyDataSetChanged();
    }
    @Override
    public void onChange(RealmResults<Programa> element) {
        myAdapter.notifyDataSetChanged();
    }

    private void removeAll(){ db.delete("programas","",null);}
    @Override
    public void onDestroy() {
        db.close();
        //realm.removeAllChangeListeners();
        //realm.close();
        super.onDestroy();
    }
}
