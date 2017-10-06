package v1.androidappsdhj.com.ar.programaspartidarios.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;
import v1.androidappsdhj.com.ar.programaspartidarios.R;
import v1.androidappsdhj.com.ar.programaspartidarios.activities.DetalleActivity;
import v1.androidappsdhj.com.ar.programaspartidarios.adapters.MyAdapterListView;
import v1.androidappsdhj.com.ar.programaspartidarios.models.Programa;

/**
 * A simple {@link Fragment} subclass.
 */
public class MananaFragment extends Fragment implements RealmChangeListener<RealmResults<Programa>> {

    private List<Programa> programas;
    // private RecyclerView recycler;
    // private RecyclerView.Adapter adapter;
    // private RecyclerView.LayoutManager layoutManager;
    private ListView listView;
    private MyAdapterListView myAdapter;
    private int contador = 0;

    private Realm realm;
    private RealmResults<Programa> programasR;

    private View view;

    public MananaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_am, container, false);

        SharedPreferences prefs;
        prefs = this.getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("fragment", 13);
        editor.apply();

        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //RelativeLayout fl = (RelativeLayout) inflater.inflate(R.layout.fragment_am, container, false);
        //recycler = (RecyclerView) fl.findViewById(R.id.recyclerView2);

        realm = Realm.getDefaultInstance();

        programasR = getAllProgramasR();
        programasR.addChangeListener(this);
        // programas = getAllProgramas();
     /*   recycler = (RecyclerView) view.findViewById(R.id.recyclerView2);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        adapter = new MyAdapter(programasR, R.layout.list_item_recycler, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Programa programa, int position) {
                //deletePrograma(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalleActivity.class);
                intent.putExtra("Programa", programa.getId());
                intent.putExtra("Fragment", 13);
                startActivity(intent);
            }
        });

        adapter.notifyDataSetChanged();

        //Este metodo se puede usar cuando sabemos que el layout del recycler no van a cambiar de tamaño
        recycler.setHasFixedSize(true);
        //Se le agrega una animacion por defecto
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter); */
        myAdapter = new MyAdapterListView(getContext(), R.layout.list_item_listview,programasR);
        myAdapter.notifyDataSetChanged();
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), DetalleActivity.class);
                intent.putExtra("Programa", programasR.get(position).getId());
                intent.putExtra("Fragment", 1);
                startActivity(intent);
            }
        });
        myAdapter.notifyDataSetChanged();
        return view;
    }

    private RealmResults<Programa> getAllProgramasR() {
        //return realm.where(Programa.class).findAll();
        return realm.where(Programa.class).equalTo("manana", true).findAllSorted("nombre", Sort.ASCENDING);
    }

    @Override
    public void onChange(RealmResults<Programa> element) {
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        realm.removeAllChangeListeners();
        realm.close();
        super.onDestroy();
    }
}