package android.dariojolo.com.ar.programaspartidarios.Fragments;


import android.content.Intent;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.dariojolo.com.ar.programaspartidarios.activities.DetalleActivity;
import android.dariojolo.com.ar.programaspartidarios.adapters.MyAdapter;
import android.dariojolo.com.ar.programaspartidarios.models.Programa;
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

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment implements RealmChangeListener<RealmResults<Programa>> {

    private List<Programa> programas;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int contador = 0;

    private Realm realm;
    private RealmResults<Programa> programasR;

    private View view;

    public FavoritosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        //RelativeLayout fl = (RelativeLayout) inflater.inflate(R.layout.fragment_am, container, false);
        //recycler = (RecyclerView) fl.findViewById(R.id.recyclerView2);

        AdView mAdView = (AdView) view.findViewById(R.id.adView);
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
                intent.putExtra("Fragment", 4);
                startActivity(intent);
            }
        });

        //Este metodo se puede usar cuando sabemos que el layout del recycler no van a cambiar de tamaño
        recycler.setHasFixedSize(true);
        //Se le agrega una animacion por defecto
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        return view;
    }

    private RealmResults<Programa> getAllProgramasR() {
        //return realm.where(Programa.class).findAll();
        return realm.where(Programa.class).equalTo("favorito", true).findAll();
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

