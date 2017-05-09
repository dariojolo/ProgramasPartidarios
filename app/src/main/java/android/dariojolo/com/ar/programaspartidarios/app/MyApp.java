package android.dariojolo.com.ar.programaspartidarios.app;

import android.app.Application;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.dariojolo.com.ar.programaspartidarios.models.Programa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigrl on 09/05/2017.
 */

public class MyApp extends Application {

    public List<Programa>lista = new ArrayList<>();
    public MyApp(){
                lista.add(new Programa("Programa partidario 1", R.drawable.programa1));
                lista.add(new Programa("Programa partidario 2", R.drawable.programa2));
                lista.add(new Programa("Programa partidario 3", R.drawable.programa3));
                lista.add(new Programa("Programa partidario 4", R.drawable.programa4));
        }
    }

