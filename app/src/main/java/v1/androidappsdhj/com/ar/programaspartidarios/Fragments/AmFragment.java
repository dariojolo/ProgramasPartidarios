package v1.androidappsdhj.com.ar.programaspartidarios.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import v1.androidappsdhj.com.ar.programaspartidarios.R;
import v1.androidappsdhj.com.ar.programaspartidarios.activities.DetalleActivity;
import v1.androidappsdhj.com.ar.programaspartidarios.adapters.MyAdapter;
import v1.androidappsdhj.com.ar.programaspartidarios.models.Programa;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmFragment extends Fragment implements RealmChangeListener<RealmResults<Programa>> {

    private List<Programa> programas;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int contador = 0;

    private Realm realm;
    private RealmResults<Programa> programasR;

    private View view;

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

        realm = Realm.getDefaultInstance();

        programasR = getAllProgramasR();
        programasR.addChangeListener(this);
        // programas = getAllProgramas();
        recycler = (RecyclerView) view.findViewById(R.id.recyclerView2);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        adapter = new MyAdapter(programasR, R.layout.list_item_recycler, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Programa programa, int position) {
                //deletePrograma(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalleActivity.class);
                intent.putExtra("Programa", programa.getId());
                intent.putExtra("Fragment", 1);
                startActivity(intent);
            }
        });

        recycler.getRecycledViewPool().clear();
        //Este metodo se puede usar cuando sabemos que el layout del recycler no van a cambiar de tamaño
        recycler.setHasFixedSize(true);
        //Se le agrega una animacion por defecto
<<<<<<< HEAD
        //recycler.setItemAnimator(new DefaultItemAnimator());
        //recycler.setLayoutManager(layoutManager);
<<<<<<< HEAD
        //recycler.setAdapter(adapter);
=======
        //recycler.setAda   pter(adapter);
        myAdapter = new MyAdapterListView(getContext(), R.layout.list_item_listview,programasR);
        myAdapter.notifyDataSetChanged();
>>>>>>> parent of 64e82a2... Migrando a SQLite I
        listView.setAdapter(myAdapter);
<<<<<<< HEAD
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalleActivity.class);
                intent.putExtra("Programa", programasR.get(position).getId());
                intent.putExtra("Fragment", 1);
                startActivity(intent);
            }
        });
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 16d3144... Cambiando a ListView II
        //adapter.notifyItemRangeChanged(26,27);
        myAdapter.notifyDataSetChanged();
<<<<<<< HEAD
<<<<<<< HEAD
        update();
>>>>>>> parent of cf4596a... Cambiando a ListView IV no se ven los listados

=======
        //adapter.notifyItemRangeChanged(26,27);
>>>>>>> parent of 027a0f0... Migrando a SQLite III
        myAdapter.notifyDataSetChanged();
        //update();

=======
>>>>>>> parent of 64e82a2... Migrando a SQLite I
=======
>>>>>>> parent of 64e82a2... Migrando a SQLite I
=======
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
<<<<<<< HEAD
<<<<<<< HEAD
        //adapter.notifyItemRangeChanged(26,27);
        adapter.notifyDataSetChanged();
>>>>>>> parent of ab89806... Cambiando a ListView I
=======
        adapter.notifyItemRangeChanged(26,27);
>>>>>>> parent of 42874ea... Intentando refrescar el recyclerview V
=======
        adapter.notifyDataSetChanged();
>>>>>>> parent of bbd35c1... Intentando refrescar el recyclerview IIII
        return view;
    }

    private RealmResults<Programa> getAllProgramasR() {
        //return realm.where(Programa.class).findAll();
        return realm.where(Programa.class).equalTo("medio", "AM").findAllSorted("nombre", Sort.ASCENDING);
    }

    @Override
    public void onChange(RealmResults<Programa> element) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        realm.removeAllChangeListeners();
        realm.close();
        super.onDestroy();
    }
}
