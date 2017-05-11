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

public class MainActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        programas = getAllProgramas();
        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(programas, R.layout.list_item_recycler, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Programa programa, int position) {
                //deletePrograma(position);
                Intent intent = new Intent(MainActivity.this,DetalleActivity.class);
                Toast.makeText(getApplicationContext(), "Posicion antes de enviarse "+ position, Toast.LENGTH_LONG).show();
                intent.putExtra("Posicion", position);
                intent.putExtra("Programa", (Serializable) programa);
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

    private List<Programa> getAllProgramas(){
        return new ArrayList<Programa>() {{
            add(new Programa("A todo ciclón", R.drawable.atodociclon2, "Oscar Sacco", "AM810"));
            add(new Programa("Buenas y Santas", R.drawable.buenasysantas, "No proporcionado", "AM1090"));
            add(new Programa("El café del ciclón", R.drawable.elcafedelciclon, "Mariano Bongiorno", "AM1290"));
            add(new Programa("El Plateista", R.drawable.elplateista, "Carlos Canissa", "AM810"));
            add(new Programa("Hablemos de San Lorenzo", R.drawable.hablemosdesanlorenzo, "Cristian Pagliaro, Rodrigo Castellano y Nicolas Morandi", "AM770"));
            add(new Programa("La hora del ciclón", R.drawable.lahoradelciclon, "Mario Massi", "AM890"));
            add(new Programa("La cicloneta", R.drawable.lacicloneta, "Leandro Alves", "AM970"));
        }};
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
}
