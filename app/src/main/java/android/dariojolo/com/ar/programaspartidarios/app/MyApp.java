package android.dariojolo.com.ar.programaspartidarios.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.dariojolo.com.ar.programaspartidarios.models.Programa;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by rodrigrl on 09/05/2017.
 */

public class MyApp extends Application {

    private Realm realm;
    public static AtomicInteger ProgramaID = new AtomicInteger();
    private SharedPreferences prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
//        prefs.edit().remove("firstTime").apply();
        initRealm();

}

    private boolean validarFirstTime() {
        return prefs.getBoolean("firstTime", false);
    }

    private void saveOnPreferences(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstTime", true);
        editor.apply();
    }
    private void initRealm(){
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("programas.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
        //removeAll();
        ProgramaID = setAtomicId(realm,Programa.class);

        //removeAll();
        if (!validarFirstTime()) {
            iniciarListaProgramas();
        }
        //removeAll();
        realm.close();
    }
    private <T extends RealmObject>  AtomicInteger setAtomicId(Realm realm, Class<T>anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size()>0)? new AtomicInteger(results.max("Id").intValue()): new AtomicInteger();
    }
    private void removeAll(){
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }
    private void iniciarListaProgramas(){
        realm.executeTransaction(new Realm.Transaction(){

            @Override
            public void execute(Realm realm) {
                Programa p1 = new Programa("A todo ciclón", R.drawable.atodociclon2, "Oscar Sacco", "AM810","saccofutbol@gmail.com","https://twitter.com/atodociclonsl",null,null,true,false,false,false,true,false,false,false,"19", "20","AM",false);
                Programa p2 = new Programa("A todo San Lorenzo", R.drawable.atodosanlorenzo, "Carlos García", "AM1490","coriafutbol@hotmail.com","https://twitter.com/atodosanlorenzo","https://www.facebook.com/ATodoSanLorenzoGama/","4218-5333/1951",true,false,false,true,false,false,false,true,"21","22","AM",true);
                Programa p3 = new Programa("Buenas y Santas", R.drawable.buenasysantas, "", "AM1090","buenasysantasok@yahoo.com","https://twitter.com/buenasysantasok",null,"4926-1623",false,true,false,false,false,false,false,false,"19","21","AM",true);
                Programa p4 = new Programa("El café del ciclón", R.drawable.elcafedelciclon, "Mariano Bongiorno", "AM1290","elcafedelciclon@yahoo.com.ar","https://twitter.com/elcafedelciclon",null,null,false,false,false,true,false,false,false,false,"17","18","FM",true);
                Programa p5 = new Programa("El Plateista", R.drawable.elplateista, "Carlos Canissa", "AM810","ccanissa@gmail.com","https://twitter.com/elplateista","https://www.facebook.com/ElPlateista",null,false,false,true,false,false,false,false,false,"21","22","AM",false);
                Programa p6 = new Programa("Equipo Desafío", R.drawable.equipodesafio, "Julio Axel / Maximiliano Berardo", "AM990","mensajes@equipodesafio.com","https://twitter.com/equipodesafio","https://www.facebook.com/Equipo-Desaf%C3%ADo-362011670569256/",null,false,true,false,false,false,false,false,true,"21","23","AM",false);
                Programa p7 = new Programa("Estación Boedo",R.drawable.estacionboedo2,"","FM105.9","estacionboedo6@gmail.com","https://twitter.com/EstacionBoedo","https://www.facebook.com/Estacion-Boedo-PQV-792475577446550/","4912-1059",true,false,false,false,false,false,false,false,"21","23","AM",false);
                Programa p8 = new Programa("Gente de San Lorenzo", R.drawable.gentedesanlorenzo,"Claudio Morrone","AM840",null,null,null,null,false,false,false,false,true,false,false,false,"20","21","FM",false);
                Programa p9 = new Programa("Hablemos de San Lorenzo", R.drawable.hablemosdesanlorenzo, "Cristian Pagliaro, Rodrigo Castellano y Nicolas Morandi", "AM770","cris_pagliaro05@yahoo.com.ar",null,null,null,false,false,false,false,false,false,false,false,"","","AM",false);
                // Programa p61 = new Programa("La hora del ciclón", R.drawable.lahoradelciclon, "Mario Massi", "AM890",null,null,null,null,false,false,false,false,false,false,false,false,"","","AM",false);
                // Programa p71 = new Programa("La cicloneta", R.drawable.lacicloneta, "Leandro Alves", "AM970",null,null,null,null,false,false,false,false,false,false,false,false,"","","AM",false);

                realm.copyToRealmOrUpdate(p1);
                realm.copyToRealmOrUpdate(p2);
                realm.copyToRealmOrUpdate(p3);
                realm.copyToRealmOrUpdate(p4);
                realm.copyToRealmOrUpdate(p5);
                realm.copyToRealmOrUpdate(p6);
                realm.copyToRealmOrUpdate(p7);
                realm.copyToRealmOrUpdate(p8);
                realm.copyToRealmOrUpdate(p9);

                saveOnPreferences();
            }
        });
    }
}

