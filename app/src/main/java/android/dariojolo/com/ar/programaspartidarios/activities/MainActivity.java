package android.dariojolo.com.ar.programaspartidarios.activities;

import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Programa> programas;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int contador = 0;


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
                deletePrograma(position);
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
            add(new Programa("Programa partidario 1", R.drawable.programa1));
            add(new Programa("Programa partidario 2", R.drawable.programa2));
            add(new Programa("Programa partidario 3", R.drawable.programa3));
            add(new Programa("Programa partidario 4", R.drawable.programa4));
        }};
    }
    private void addPrograma(int position){
      //  programas.add(position, new Programa("Pelicula: " + ++contador, R.drawable.programanuevo));
      //  adapter.notifyItemInserted(position);
      //  layoutManager.scrollToPosition(0);
        showAlertParaNuevoPrograma("Nuevo programa partidario", "Ingrese nombre del programa");

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

        final EditText nombreIngresado = (EditText)viewInflated.findViewById(R.id.edtNombre);

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombreProg = nombreIngresado.getText().toString().trim();
                if (nombreProg.length()>0){
                    Toast.makeText(getApplicationContext(),"Nombre de programa: " + nombreProg,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"El nombre no puede ser vacio",Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();



    }
}
