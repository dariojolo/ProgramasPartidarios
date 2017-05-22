package android.dariojolo.com.ar.programaspartidarios.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.dariojolo.com.ar.programaspartidarios.adapters.MyAdapter;
import android.dariojolo.com.ar.programaspartidarios.models.Programa;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Programa>>{

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();


        iniciarListaProgramas();

        programasR = getAllProgramasR();
        programasR.addChangeListener(this);
       // programas = getAllProgramas();
        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(programasR, R.layout.list_item_recycler, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Programa programa, int position) {
                //deletePrograma(position);
                Intent intent = new Intent(MainActivity.this,DetalleActivity.class);
                Toast.makeText(getApplicationContext(), "Posicion antes de enviarse "+ position, Toast.LENGTH_LONG).show();
                intent.putExtra("Programa", programa.getId());
                startActivity(intent);
            }
        });

        //Este metodo se puede usar cuando sabemos que el layout del recycler no van a cambiar de tamaño
        recycler.setHasFixedSize(true);
        //Se le agrega una animacion por defecto
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);

       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_programa:
                this.addPrograma(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private RealmResults<Programa>getAllProgramasR(){
        return realm.where(Programa.class).findAll();
    }

    private void iniciarListaProgramas(){
        realm.executeTransaction(new Realm.Transaction(){

            @Override
            public void execute(Realm realm) {
                Programa p1 = new Programa("A todo ciclón", R.drawable.atodociclon2, "Oscar Sacco", "AM810","saccofutbol@gmail.com","https://twitter.com/atodociclonsl",null,null,true,false,false,false,true,false,false,false,"19", "20");
                Programa p2 = new Programa("A todo San Lorenzo", R.drawable.atodosanlorenzo, "Carlos García", "AM1490","coriafutbol@hotmail.com","https://twitter.com/atodosanlorenzo","https://www.facebook.com/ATodoSanLorenzoGama/","4218-5333/1951",true,false,false,true,false,false,false,true,"21","22");
                Programa p3 = new Programa("Buenas y Santas", R.drawable.buenasysantas, "", "AM1090","buenasysantasok@yahoo.com","https://twitter.com/buenasysantasok",null,"4926-1623",false,true,false,false,false,false,false,false,"19","21");
                Programa p4 = new Programa("El café del ciclón", R.drawable.elcafedelciclon, "Mariano Bongiorno", "AM1290","elcafedelciclon@yahoo.com.ar","https://twitter.com/elcafedelciclon",null,null,false,false,false,true,false,false,false,false,"17","18");
                Programa p5 = new Programa("El Plateista", R.drawable.elplateista, "Carlos Canissa", "AM810","ccanissa@gmail.com","https://twitter.com/elplateista","https://www.facebook.com/ElPlateista",null,false,false,true,false,false,false,false,false,"21","22");
                Programa p6 = new Programa("Equipo Desafío", R.drawable.equipodesafio, "Julio Axel / Maximiliano Berardo", "AM990","mensajes@equipodesafio.com","https://twitter.com/equipodesafio","https://www.facebook.com/Equipo-Desaf%C3%ADo-362011670569256/",null,false,true,false,false,false,false,false,true,"21","23");
                Programa p7 = new Programa("Estación Boedo",R.drawable.estacionboedo2,"","FM105.9","estacionboedo6@gmail.com","https://twitter.com/EstacionBoedo","https://www.facebook.com/Estacion-Boedo-PQV-792475577446550/","4912-1059",true,false,false,false,false,false,false,false,"21","23");
                Programa p8 = new Programa("Gente de San Lorenzo",R.drawable.gentedesanlorenzo,"Claudio Morrone","AM840",null,null,null,null,false,false,false,false,true,false,false,false,"20","21");
                Programa p9 = new Programa("Hablemos de San Lorenzo", R.drawable.hablemosdesanlorenzo, "Cristian Pagliaro, Rodrigo Castellano y Nicolas Morandi", "AM770","cris_pagliaro05@yahoo.com.ar",null,null,null,false,false,false,false,false,false,false,false,"","");
               // Programa p61 = new Programa("La hora del ciclón", R.drawable.lahoradelciclon, "Mario Massi", "AM890",null,null,null,null,false,false,false,false,false,false,false,false,"","");
               // Programa p71 = new Programa("La cicloneta", R.drawable.lacicloneta, "Leandro Alves", "AM970",null,null,null,null,false,false,false,false,false,false,false,false,"","");

                realm.copyToRealmOrUpdate(p1);
                realm.copyToRealmOrUpdate(p2);
                realm.copyToRealmOrUpdate(p3);
                realm.copyToRealmOrUpdate(p4);
                realm.copyToRealmOrUpdate(p5);
                realm.copyToRealmOrUpdate(p6);
                realm.copyToRealmOrUpdate(p7);
                realm.copyToRealmOrUpdate(p8);
                realm.copyToRealmOrUpdate(p9);

                programasR = getAllProgramasR();
            }
        });
    }
    private void addPrograma(int position){
      //  programas.add(position, new Programa("Pelicula: " + ++contador, R.drawable.programanuevo));
      //  adapter.notifyItemInserted(position);
      //  layoutManager.scrollToPosition(0);
        showAlertParaNuevoPrograma("Nuevo programa partidario", "");

    }


    private void deletePrograma(int position){
        programas.remove(position);
        adapter.notifyItemRemoved(position);
    }
    //Ventana para agregar un nuevo Programa
    private void showAlertParaNuevoPrograma(String titulo, String mensaje){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (titulo!= null){
            builder.setTitle(titulo);
        }
        if (mensaje != null){
            builder.setMessage(mensaje);
        }
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.layout_nuevo_programa, null);
        builder.setView(viewInflated);
        final ToggleButton btnLunes = (ToggleButton)viewInflated.findViewById(R.id.btnLunes);
        final ToggleButton btnMartes = (ToggleButton)viewInflated.findViewById(R.id.btnMartes);
        final ToggleButton btnMiercoles = (ToggleButton)viewInflated.findViewById(R.id.btnMiercoles);
        final ToggleButton btnJueves = (ToggleButton)viewInflated.findViewById(R.id.btnJueves);
        final ToggleButton btnViernes = (ToggleButton)viewInflated.findViewById(R.id.btnViernes);
        final EditText nombreIngresado = (EditText)viewInflated.findViewById(R.id.edtNombre);
        final EditText horaInicio  = (EditText)viewInflated.findViewById(R.id.edtHoraInicio);
        btnLunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLunes) {
                    btnLunes.setBackgroundResource(R.drawable.circuloazul);
                    isLunes = true;
                }else if (isLunes){
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
                }else if (isMartes){
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
                }else if (isMiercoles){
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
                }else if (isJueves){
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
                }else if (isViernes){
                    btnViernes.setBackgroundResource(R.drawable.circulorojo);
                    isViernes = false;
                }
            }
        });

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombreProg = nombreIngresado.getText().toString().trim();
                if (nombreProg.length()>0){
                    Toast.makeText(getApplicationContext(),"Nombre de programa: " + nombreProg + " Hora inicio: " + horaInicio.getText(),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"El nombre no puede ser vacio",Toast.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        realm.removeAllChangeListeners();
        realm.close();
        super.onDestroy();
    }
}
