package android.dariojolo.com.ar.programaspartidarios.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.dariojolo.com.ar.programaspartidarios.activities.DetalleActivity;
import android.dariojolo.com.ar.programaspartidarios.adapters.MyAdapter;
import android.dariojolo.com.ar.programaspartidarios.models.Programa;
import android.dariojolo.com.ar.programaspartidarios.servicies.MyFirebaseInstanceIDService;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class MainFragment extends Fragment implements RealmChangeListener<RealmResults<Programa>> {

    private List<Programa> programas;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int contador = 0;
    private Boolean isLunes = false;
    private Boolean isMartes = false;
    private Boolean isMiercoles = false;
    private Boolean isJueves = false;
    private Boolean isViernes = false;
    private Boolean isSabado = false;
    private Boolean isDomingo = false;

    private Realm realm;
    private RealmResults<Programa> programasR;

    private View view;

    private FloatingActionButton fab;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        RelativeLayout fl = (RelativeLayout) inflater.inflate(R.layout.fragment_main, container, false);
        recycler = (RecyclerView) fl.findViewById(R.id.recyclerView);

        fab = (FloatingActionButton)fl.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Se apreto boton",Toast.LENGTH_SHORT).show();
                MyFirebaseInstanceIDService myFID = new MyFirebaseInstanceIDService();
                myFID.onTokenRefresh();
            }
        });

        realm = Realm.getDefaultInstance();

        programasR = getAllProgramasR();
        programasR.addChangeListener(this);
        // programas = getAllProgramas();
        recycler = (RecyclerView) view.findViewById(R.id.recyclerView);
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

        //Este metodo se puede usar cuando sabemos que el layout del recycler no van a cambiar de tamaño
        recycler.setHasFixedSize(true);
        //Se le agrega una animacion por defecto
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        return view;
    }

    /* Para el menu de la toolbar, ahora no lo estoy usando
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_programa:
                this.addPrograma(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    } */

    private RealmResults<Programa> getAllProgramasR() {
        return realm.where(Programa.class).findAll();
    }

    //Ventana para agregar un nuevo Programa
    private void showAlertParaNuevoPrograma(String titulo, String mensaje) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());
        if (titulo != null) {
            builder.setTitle(titulo);
        }
        if (mensaje != null) {
            builder.setMessage(mensaje);
        }
        View viewInflated = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.layout_nuevo_programa, null);
        builder.setView(viewInflated);
        final ToggleButton btnLunes = (ToggleButton) viewInflated.findViewById(R.id.btnLunes);
        final ToggleButton btnMartes = (ToggleButton) viewInflated.findViewById(R.id.btnMartes);
        final ToggleButton btnMiercoles = (ToggleButton) viewInflated.findViewById(R.id.btnMiercoles);
        final ToggleButton btnJueves = (ToggleButton) viewInflated.findViewById(R.id.btnJueves);
        final ToggleButton btnViernes = (ToggleButton) viewInflated.findViewById(R.id.btnViernes);
        final EditText nombreIngresado = (EditText) viewInflated.findViewById(R.id.edtNombre);
        final EditText horaInicio = (EditText) viewInflated.findViewById(R.id.edtHoraInicio);
        btnLunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLunes) {
                    btnLunes.setBackgroundResource(R.drawable.circuloazul);
                    isLunes = true;
                } else if (isLunes) {
                    btnLunes.setBackgroundResource(R.drawable.circulorojo);
                    isLunes = false;
                }
            }
        });
        btnMartes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isMartes) {
                    btnMartes.setBackgroundResource(R.drawable.circuloazul);
                    isMartes = true;
                } else if (isMartes) {
                    btnMartes.setBackgroundResource(R.drawable.circulorojo);
                    isMartes = false;
                }
            }
        });
        btnMiercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isMiercoles) {
                    btnMiercoles.setBackgroundResource(R.drawable.circuloazul);
                    isMiercoles = true;
                } else if (isMiercoles) {
                    btnMiercoles.setBackgroundResource(R.drawable.circulorojo);
                    isMiercoles = false;
                }
            }
        });
        btnJueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isJueves) {
                    btnJueves.setBackgroundResource(R.drawable.circuloazul);
                    isJueves = true;
                } else if (isJueves) {
                    btnJueves.setBackgroundResource(R.drawable.circulorojo);
                    isJueves = false;
                }
            }
        });
        btnViernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isViernes) {
                    btnViernes.setBackgroundResource(R.drawable.circuloazul);
                    isViernes = true;
                } else if (isViernes) {
                    btnViernes.setBackgroundResource(R.drawable.circulorojo);
                    isViernes = false;
                }
            }
        });

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombreProg = nombreIngresado.getText().toString().trim();
                if (nombreProg.length() > 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Nombre de programa: " + nombreProg + " Hora inicio: " + horaInicio.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "El nombre no puede ser vacio", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
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
