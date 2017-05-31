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
        //prefs.edit().remove("firstTime").apply();
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

        ProgramaID = setAtomicId(realm,Programa.class);

        //removeAll();
        if (!validarFirstTime()) {
            iniciarListaProgramas();
        }

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
                Programa p1 = new Programa("A todo ciclón", R.drawable.atodociclon2, "Oscar Sacco", "AM810","saccofutbol@gmail.com","No disponible","https://twitter.com/atodociclonsl","No disponible","No disponible",true,false,false,false,true,false,false,false,"Lunes y Viernes 19Hs", "","AM",false,false,true,false);
                Programa p2 = new Programa("A todo San Lorenzo", R.drawable.atodosanlorenzo, "Carlos García", "AM1490","coriafutbol@hotmail.com","No disponible","https://twitter.com/atodosanlorenzo","https://www.facebook.com/ATodoSanLorenzoGama/","4218-5333/1951",true,false,false,true,false,false,false,true,"Lunes y Jueves 21Hs","Días de partidos","AM",false,false,false,true);
                Programa p3 = new Programa("Buenas y Santas", R.drawable.buenasysantas, "", "AM1090","buenasysantasok@yahoo.com","No disponible","https://twitter.com/buenasysantasok","No disponible","4926-1623",false,true,false,false,false,false,false,false,"Martes 19Hs","","AM",false,false,true,false);
                Programa p4 = new Programa("Cuervos y basta", R.drawable.generico, "Jorge Galeazzo", "FM 105.1 San Nicolas","jorgegaleazzo@arnet.com.ar","No disponible","No disponible","No disponible","No disponible",true,false,false,false,false,false,false,false,"Lunes 19Hs","","FM",false,false,true,false);
                Programa p5 = new Programa("El café del ciclón", R.drawable.elcafedelciclon, "Mariano Bongiorno", "AM1290","elcafedelciclon@yahoo.com.ar","http://www.elcafedelciclon.com.ar/","https://twitter.com/elcafedelciclon","No disponible","No disponible",false,false,false,true,false,false,false,false,"Jueves 17hs","","FM",false,false,true,false);
                Programa p6 = new Programa("El Plateista", R.drawable.elplateista, "Carlos Canissa", "AM810","ccanissa@gmail.com","http://www.elplateista.com.ar/","https://twitter.com/elplateista","https://www.facebook.com/ElPlateista",null,false,false,true,false,false,false,false,false,"Miércoles 20Hs","","AM",false,false,false,true);
                Programa p7 = new Programa("Equipo Desafío", R.drawable.equipodesafio, "Julio Axel / Maximiliano Berardo", "AM990","mensajes@equipodesafio.com","http://www.equipodesafio.com/","https://twitter.com/equipodesafio","https://www.facebook.com/Equipo-Desaf%C3%ADo-362011670569256/","No disponible",false,true,false,false,false,false,false,true,"Martes 21Hs","Días de partidos","AM",false,false,false,true);
                Programa p8 = new Programa("Estación Boedo",R.drawable.estacionboedo2,"","FM105.9","estacionboedo6@gmail.com","No disponible","https://twitter.com/EstacionBoedo","https://www.facebook.com/Estacion-Boedo-PQV-792475577446550/","4912-1059",true,false,false,false,false,false,false,false,"Lunes 21hs","","AM",false,false,false,true);
                Programa p9 = new Programa("Gente de San Lorenzo", R.drawable.gentedesanlorenzo,"Claudio Morrone","AM840","No disponible","No disponible","No disponible","No disponible","No disponible",false,false,false,false,true,false,false,false,"Viernes 20Hs","","FM",false,false,false,true);
                Programa p10 = new Programa("Glorioso San Lorenzo",R.drawable.gloriososanlorenzo,"No disponible","FM88.1","marcosnext@hotmail.com"," No disponible","https://twitter.com/gloriosocasla"," No disponible"," No disponible",false,false,true,false,false,false,false,true,"Miércoles 21Hs","Días de partidos","FM",false,false,false,true);
                Programa p11 = new Programa("Hablemos de San Lorenzo", R.drawable.hablemosdesanlorenzo, "Cristian Pagliaro, Rodrigo Castellano y Nicolas Morandi", "AM770","cris_pagliaro05@yahoo.com.ar","http://www.desanlorenzo.com/","https://twitter.com/hablemosdesl","https://www.facebook.com/hablemosdesl/","5275-0770/71/72",true,true,true,true,true,false,true,true,"Lunes a Viernes 22Hs","Domingos 22Hs","AM",false,false,false,true);
                Programa p12 = new Programa("Opinión azulgrana",R.drawable.generico,"Carlos Perez Castex","AM1490","carlosperezcastex@hotmail.com.ar","No disponible","https://twitter.com/opazulgrana","No disponible","No disponible",false,true,true,true,false,false,true,false,"Domingo, Martes, Miércoles y Jueves 23Hs","","AM",false,false,false,true);
                Programa p13 = new Programa("La Cuervería",R.drawable.lacuerveria,"Cristian Paladino","AM840","lacuerveria840@yahoo.com.ar","http://www.semilleroazulgrana.com.ar/","https://twitter.com/lacuerveria","https://www.facebook.com/pg/LaCuerveriaOficial","No disponible",true,false,false,false,false,false,false,false,"Lunes 20Hs","","AM",false,false,false,true);
                Programa p14 = new Programa("La hora del Ciclón",R.drawable.lahoradelciclon,"Mario Massi","AM890","alejogarcia090380@gmail.com.ar","No disponible","https://twitter.com/lahoradelciclon","https://www.facebook.com/marioeduardo.massi","No disponible",true,true,true,false,true,false,false,false,"Lunes, martes, miércoles y viernes 19Hs","","AM",false,false,true,false);
                Programa p15 = new Programa("La cicloneta", R.drawable.lacicloneta, "Leandro Alves", "AM970","lacicloneta@gmail.com","No disponible","https://twitter.com/lacicloneta","https://www.facebook.com/LaCiclonetaRadio/","4912-0497/0906",false,false,true,false,true,false,true,false,"Miércoles y viernes 21hs","Domingos 22hs","AM",false,false,false,true);
                Programa p16 = new Programa("San Lorenzo ayer, hoy y siempre",R.drawable.sanlorenzoayerhoyysiempre,"Adolfo Res","AM970","sanlorenzoayerhoysiempre@yahoo.com.ar","http://volveavenidalaplata.com.ar/","https://twitter.com/caslahistoria","https://www.facebook.com/CASLAhistoria/","1156196775",false,false,false,false,false,false,true,false,"Domingos 13Hs","","AM",false,false,true,false);
                Programa p17 = new Programa("San Lorenzo de América",R.drawable.sanlorenzodeamerica,"Diego Arvilly","FM93.1","nicomormandi22@gmail.com","No disponible","https://twitter.com/SLDAradio","https://www.facebook.com/SLDAradio/","No disponible",false,true,false,false,false,false,false,false,"Martes 22Hs","","FM",false,false,false,true);
                Programa p18 = new Programa("San Lorenzo en acción",R.drawable.sanlorenzoenaccion,"No disponible","AM1590","sanlorenzoenaccion@hotmail.com","No disponible","https://twitter.com/slenaccion","No disponible","No disponible",false,false,false,true,false,false,false,true,"Jueves 20Hs","Días de partidos","AM",false,false,false,true);
                Programa p19 = new Programa("San Lorenzo eterno",R.drawable.sanlorenzoeterno2,"Marcelo Culotta","AM970","sanlorenzoeterno@gmail.com","No disponible","https://twitter.com/sleterno","https://www.facebook.com/SanLorenzoEterno/","4926-1622",false,true,false,false,false,false,false,false,"Martes 20Hs","","AM",false,false,false,true);
                Programa p20 = new Programa("San Lorenzo mi pasión",R.drawable.sanlorenzomipasion,"Víctor Federico","AM970","victorfederico970@gmail.com","No disponible","https://twitter.com/vueltaboedo","https://www.facebook.com/victor.federico.7","No disponible",false,false,false,false,false,true,false,false,"Sábados 12Hs","","AM",false,true,false,false);
                Programa p21 = new Programa("Sentimiento Azulgrana",R.drawable.generico,"Mario Massi","AM890 o AM810","alejogarcia10@hotmail.com","No disponible","No disponible","https://www.facebook.com/profile.php?id=100006642735130","No disponible",false,false,false,false,false,false,false,true,"Días de partidos","","AM",false,false,false,false);


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

