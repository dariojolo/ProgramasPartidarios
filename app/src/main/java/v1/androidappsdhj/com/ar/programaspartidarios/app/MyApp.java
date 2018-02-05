package v1.androidappsdhj.com.ar.programaspartidarios.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import v1.androidappsdhj.com.ar.programaspartidarios.R;
import v1.androidappsdhj.com.ar.programaspartidarios.models.Programa;


/**
 * Created by rodrigrl on 09/05/2017.
 */

public class MyApp extends Application {

    private Realm realm;
    public static AtomicInteger ProgramaID = new AtomicInteger();
    private SharedPreferences prefs;
    public static int contadorPantallas = 1;


    @Override
    public void onCreate() {
        super.onCreate();
        //SystemClock.sleep(3000);
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        initRealm();
    }

    private boolean validarFirstTime() {
        return prefs.getBoolean("firstTime", false);
    }

    private void saveOnPreferences(String clave) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(clave, true);
        editor.apply();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("programas.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();

        ProgramaID = setAtomicId(realm, Programa.class);

        if (!validarFirstTime()) {
            iniciarListaProgramas();
        }
        if (!validarHayUpdate()) {
            actualizarListaProgramas();
        }
        if (!validarHayUpdate2()) {
            actualizarListaProgramas2();
        }
        if (!validarHayUpdate3()) {
            actualizarListaProgramas3();
        }
        realm.close();
    }

    private void actualizarListaProgramas() {
        //Actualizo programas existentes con errores
        //Actualizo A todo ciclon
        Programa programa = realm.where(Programa.class).equalTo("nombre", "A Todo Ciclón").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzatodociclon);
        programa.setLink("https://tunein.com/radio/radio-federal-am-810-s293827/");
        programa.setFacebook("https://www.facebook.com/Atodociclonsl-791938394307854/");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        //Actualizo A todo san lorenzo
        programa = realm.where(Programa.class).equalTo("nombre", "A Todo San Lorenzo").findFirst();
        realm.beginTransaction();
        programa.setTwitter("https://twitter.com/ATodoSanLorenzo");
        programa.setImagen(R.drawable.zzzatodosanlorenzo);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Buenos y Santas
        programa = realm.where(Programa.class).equalTo("nombre", "Buenas y Santas").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzbuenasysantas);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Cuervos Prohibidos
        programa = realm.where(Programa.class).equalTo("nombre", "Cuervos Prohibidos").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzcuervosprohibidos);
        programa.setDiaUno("Lunes 22Hs");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Cuervos y basta
        programa = realm.where(Programa.class).equalTo("nombre", "Cuervos y basta").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzcuervosybasta);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo El café del ciclón
        programa = realm.where(Programa.class).equalTo("nombre", "El café del ciclón").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzelcafedelciclon);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        //Actualizo El plateista
        programa = realm.where(Programa.class).equalTo("nombre", "El Plateista").findFirst();
        realm.beginTransaction();
        programa.setLink("https://tunein.com/radio/radio-federal-am-810-s293827/");
        programa.setImagen(R.drawable.zzzelplateista);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Equipo Desafío
        programa = realm.where(Programa.class).equalTo("nombre", "Equipo Desafío").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzequipodesafio);
        programa.setEmisora("AM1050");
        programa.setLink("http://tunein.com/radio/Radio-General-G%C3%BCemes-1050-s135700/");
        programa.setDiaUno("Días de partidos");
        programa.setDiaDos("");
        programa.setMartes(false);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Estación Boedo
        programa = realm.where(Programa.class).equalTo("nombre", "Estación Boedo").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzestacionboedo);
        programa.setDiaUno("Lunes 21Hs");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Frenesi Azulgrana
        programa = realm.where(Programa.class).equalTo("nombre", "Frenesi Azulgrana").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzfrenesiazulgrana);
        programa.setDiaUno("Lunes a Viernes 19Hs");
        programa.setLunes(true);
        programa.setMartes(true);
        programa.setMiercoles(true);
        programa.setJueves(true);
        programa.setViernes(true);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();
        // Actualizo Gente de San Lorenzo
        //programa = realm.where(Programa.class).equalTo("nombre","Gente de San Lorenzo").findFirst();
        //realm.beginTransaction();
        //programa.setImagen(R.drawable.zzzgentedesanlorenzo);
        //realm.copyToRealmOrUpdate(programa);
        //realm.commitTransaction();
        // Actualizo Glorioso San Lorenzo
        programa = realm.where(Programa.class).equalTo("nombre", "Glorioso San Lorenzo").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzgloriososanlorenzo);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();
        // Actualizo Hablemos de San Lorenzo
        //programa = realm.where(Programa.class).equalTo("nombre","Hablemos de San Lorenzo").findFirst();
        //realm.beginTransaction();
        //programa.setImagen(R.drawable.zzzhablemosdesanlorenzo);
        //realm.copyToRealmOrUpdate(programa);
        //realm.commitTransaction();

        // Actualizo La Cicloneta
        programa = realm.where(Programa.class).equalTo("nombre", "La Cicloneta").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzlacicloneta);
        programa.setDiaUno("Lunes a Viernes 22Hs");
        programa.setLunes(true);
        programa.setMartes(true);
        programa.setMiercoles(true);
        programa.setJueves(true);
        programa.setViernes(true);
        programa.setDomingo(false);
        programa.setDiaDos("");
        programa.setEmisora("AM770");
        programa.setTelefono("5275-0770");
        programa.setLink("http://tunein.com/radio/Radio-Cooperativa-770-s101604/");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo La Cuervería
        programa = realm.where(Programa.class).equalTo("nombre", "La Cuervería").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzlacuerveria);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();
        // Actualizo La hora del Ciclón
        programa = realm.where(Programa.class).equalTo("nombre", "La hora del Ciclón").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzlahoradelciclon);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        //Actualizo Mundo Azulgrana Radio
        programa = realm.where(Programa.class).equalTo("nombre", "Mundo Azulgrana radio").findFirst();
        realm.beginTransaction();
        programa.setLink("https://tunein.com/radio/radio-federal-am-810-s293827/");
        programa.setImagen(R.drawable.zzzmundoazulgrana);
        programa.setDiaUno("Lunes a Viernes 16Hs");
        programa.setLunes(true);
        programa.setMartes(true);
        programa.setMiercoles(true);
        programa.setJueves(true);
        programa.setViernes(true);
        programa.setConductores("Rubén Becerra - Aldo Gaibuz");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Opinión azulgrana
        programa = realm.where(Programa.class).equalTo("nombre", "Opinión azulgrana").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzopinionazulgrana);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo San Lorenzo ayer, hoy y siempre
        programa = realm.where(Programa.class).equalTo("nombre", "San Lorenzo ayer, hoy y siempre").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzsanlorenzoayerhoyysiempre);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo San Lorenzo de América
        programa = realm.where(Programa.class).equalTo("nombre", "San Lorenzo de América").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzsanlorenzodeamerica);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo San Lorenzo en acción
        programa = realm.where(Programa.class).equalTo("nombre", "San Lorenzo en acción").findFirst();
        realm.beginTransaction();
        programa.setFacebook("NO DISPONIBLE");
        programa.setImagen(R.drawable.zzzsanlorenzoenaccion);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo San Lorenzo eterno
        programa = realm.where(Programa.class).equalTo("nombre", "San Lorenzo eterno").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzsanlorenzoeternonew);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo San Lorenzo mi pasión
        programa = realm.where(Programa.class).equalTo("nombre", "San Lorenzo mi pasión").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzsanlorenzomipasionnew);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Sentimiento Azulgrana
        programa = realm.where(Programa.class).equalTo("nombre", "Sentimiento Azulgrana").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzsentimientoazulgrana);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

    /*    // Actualizo Simplemente San Lorenzo
        programa = realm.where(Programa.class).equalTo("nombre","Simplemente San Lorenzo").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzsentimientoazulgrana);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction(); */

        // Actualizo Soy San Lorenzo
        programa = realm.where(Programa.class).equalTo("nombre", "Soy San Lorenzo").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzsoysanlorenzo);
        programa.setLink("https://tunein.com/radio/radio-federal-am-810-s293827/");
        programa.setEmisora("AM810");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Equipo Desafío TV
        programa = realm.where(Programa.class).equalTo("nombre", "Equipo Desafío TV").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzequipodesafio);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();


        /*
        //Agrego los programas nuevos
        Programa pr = new Programa("La Botica de Boedo", R.drawable.zlaboticadeboedo, "", "AM970","NO DISPONIBLE","NO DISPONIBLE","https://twitter.com/BoticaBoedo","NO DISPONIBLE","NO DISPONIBLE",false,false,false,false,true,false,false,false,"Viernes 22Hs","","AM","http://tunein.com/radio/Radio-G%C3%A9nesis-970-s228340/",false,false,"LaBoticaDeBoedo",false,false,true);
        Programa p30 = new Programa("Los cuervos del sur", R.drawable.zzzzloscuervosdelsur, "", "FM 91.7","cuervosdelsur@sanlorenzo.org.ar","www.loscuervosdelsur.com.ar","https://twitter.com/LosCuervosdlSur","https://www.facebook.com/LosCuervosdelSur/","NO DISPONIBLE",true,false,false,false,false,false,false,false,"Lunes 20Hs","","FM","https://tunein.com/embed/player/s103248/",false,false,"LosCuervosDelSur",false,false,true);
        Programa p31 = new Programa("Periodismo Santo", R.drawable.zzzzperiodismocuervo, "Júlio Axel - Hernán O'Donnell - León Espósito - Adolfo Res", "América Sports (Canal 115 Cablevisión)","NO DISPONIBLE","NO DISPONIBLE","https://twitter.com/PeriodismoSanto","NO DISPONIBLE","NO DISPONIBLE",false,true,true,false,false,false,false,false,"Martes 20:30Hs","Miercoles 0:30","TV","NO DISPONIBLE",false,false,"PeriodismoSanto",false,false,true);
        Programa pr2 = new Programa("Pintalo de cuervo", R.drawable.zzpintalodecuervo, "Ariel Petrosino - Leonardo Álvarez - Gustavo Lavalle", "www.larz.com.ar","NO DISPONIBLE","NO DISPONIBLE","https://twitter.com/pdcuervo2017","https://www.facebook.com/1982pdc/","NO DISPONIBLE",true,false,false,false,false,false,false,false,"Lunes 22Hs","","","http://www.larz.com.ar",false,false,"PintalodeCuervo",false,false,true);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(pr);
        realm.copyToRealmOrUpdate(pr2);
        realm.copyToRealmOrUpdate(p30);
        realm.copyToRealmOrUpdate(p31);
        realm.commitTransaction();
*/

        saveOnPreferences("hayUpdate");
        //      saveOnPreferences("hayUpdate2");
        //      saveOnPreferences("hayUpdate3");

    }

    private void actualizarListaProgramas2() {
        //Actualizo programas existentes con errores
        Programa programa;

        // Actualizo Frenesi Azulgrana
        programa = realm.where(Programa.class).equalTo("nombre", "Frenesi Azulgrana").findFirst();
        realm.beginTransaction();
        programa.setDiaUno("Lunes a Viernes 19Hs");
        programa.setLunes(true);
        programa.setMartes(true);
        programa.setMiercoles(true);
        programa.setJueves(true);
        programa.setViernes(true);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        //Actualizo Soy San Lorenzo
        programa = realm.where(Programa.class).equalTo("nombre", "Soy San Lorenzo").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzsoysanlorenzo);
        programa.setLink("https://tunein.com/radio/radio-federal-am-810-s293827/");
        programa.setEmisora("AM810");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo La Cicloneta
        programa = realm.where(Programa.class).equalTo("nombre", "La Cicloneta").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzlacicloneta);
        programa.setDiaUno("Lunes a Viernes 22Hs");
        programa.setLunes(true);
        programa.setMartes(true);
        programa.setMiercoles(true);
        programa.setJueves(true);
        programa.setViernes(true);
        programa.setDomingo(false);
        programa.setDiaDos("");
        programa.setEmisora("AM770");
        programa.setTelefono("5275-0770");
        programa.setLink("http://tunein.com/radio/Radio-Cooperativa-770-s101604/");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();


        //Actualizo Mundo Azulgrana Radio
        programa = realm.where(Programa.class).equalTo("nombre", "Mundo Azulgrana radio").findFirst();
        realm.beginTransaction();
        programa.setLink("https://tunein.com/radio/radio-federal-am-810-s293827/");
        programa.setImagen(R.drawable.zzzmundoazulgrana);
        programa.setDiaUno("Lunes a Viernes 16Hs");
        programa.setLunes(true);
        programa.setMartes(true);
        programa.setMiercoles(true);
        programa.setJueves(true);
        programa.setViernes(true);
        programa.setConductores("Rubén Becerra - Aldo Gaibuz");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Equipo Desafío
        programa = realm.where(Programa.class).equalTo("nombre", "Equipo Desafío").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzequipodesafio);
        programa.setEmisora("AM1050");
        programa.setLink("http://tunein.com/radio/Radio-General-G%C3%BCemes-1050-s135700/");
        programa.setDiaUno("Días de partidos");
        programa.setDiaDos("");
        programa.setMartes(false);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Cuervos Prohibidos
        programa = realm.where(Programa.class).equalTo("nombre", "Cuervos Prohibidos").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzcuervosprohibidos);
        programa.setDiaUno("Lunes 22Hs");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();
        // Actualizo Estación Boedo
        programa = realm.where(Programa.class).equalTo("nombre", "Estación Boedo").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzestacionboedo);
        programa.setDiaUno("Lunes 21Hs");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();


        //Agrego los programas nuevos
        programa = realm.where(Programa.class).equalTo("topicNotificacion", "LaBoticaDeBoedo").findFirst();
        if (programa == null){
            Programa pr = new Programa("La Botica de Boedo", R.drawable.zlaboticadeboedo, "", "AM970", "NO DISPONIBLE", "NO DISPONIBLE", "https://twitter.com/BoticaBoedo", "NO DISPONIBLE", "NO DISPONIBLE", false, false, false, false, true, false, false, false, "Viernes 21Hs", "", "AM", "http://tunein.com/radio/Radio-G%C3%A9nesis-970-s228340/", false, false, "LaBoticaDeBoedo", false, false, true);
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(pr);
            realm.commitTransaction();
        }

        programa = realm.where(Programa.class).equalTo("topicNotificacion", "LaBoticaDeBoedo").findFirst();
        if (programa == null){
            Programa pr2 = new Programa("Pintalo de cuervo", R.drawable.zzpintalodecuervo, "Ariel Petrosino - Leonardo Álvarez - Gustavo Lavalle", "www.larz.com.ar", "NO DISPONIBLE", "NO DISPONIBLE", "https://twitter.com/pdcuervo2017", "https://www.facebook.com/1982pdc/", "NO DISPONIBLE", true, false, false, false, false, false, false, false, "Lunes 22Hs", "", "", "http://www.larz.com.ar", false, false, "PintalodeCuervo", false, false, true);
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(pr2);
            realm.commitTransaction();
        }

        saveOnPreferences("hayUpdate2");
    }

    private void actualizarListaProgramas3() {
        //Actualizo programas 2018
        // Actualizo Soy San Lorenzo
        Programa programa;
        programa = realm.where(Programa.class).equalTo("nombre", "Soy San Lorenzo").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzsoysanlorenzo);
        programa.setLink("https://tunein.com/radio/radio-federal-am-810-s293827/");
        programa.setEmisora("AM810");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo La botica de boedo
        programa = realm.where(Programa.class).equalTo("nombre", "La Botica de Boedo").findFirst();
        realm.beginTransaction();
        programa.setConductores("");
        programa.setDiaUno("Viernes 21Hs");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Frenesi Azulgrana
        programa = realm.where(Programa.class).equalTo("nombre", "Frenesi Azulgrana").findFirst();
        realm.beginTransaction();
        programa.setDiaUno("Lunes a Viernes 19Hs");
        programa.setLunes(true);
        programa.setMartes(true);
        programa.setMiercoles(true);
        programa.setJueves(true);
        programa.setViernes(true);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo La Cicloneta
        programa = realm.where(Programa.class).equalTo("nombre", "La Cicloneta").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzlacicloneta);
        programa.setDiaUno("Lunes a Viernes 22Hs");
        programa.setLunes(true);
        programa.setMartes(true);
        programa.setMiercoles(true);
        programa.setJueves(true);
        programa.setViernes(true);
        programa.setDomingo(false);
        programa.setDiaDos("");
        programa.setEmisora("AM770");
        programa.setTelefono("5275-0770");
        programa.setLink("http://tunein.com/radio/Radio-Cooperativa-770-s101604/");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Cuervos Prohibidos
        programa = realm.where(Programa.class).equalTo("nombre", "Cuervos Prohibidos").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzcuervosprohibidos);
        programa.setDiaUno("Lunes 22Hs");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();
        // Actualizo Estación Boedo
        programa = realm.where(Programa.class).equalTo("nombre", "Estación Boedo").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzestacionboedo);
        programa.setDiaUno("Lunes 21Hs");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();


        //Actualizo Mundo Azulgrana Radio
        programa = realm.where(Programa.class).equalTo("nombre", "Mundo Azulgrana radio").findFirst();
        realm.beginTransaction();
        programa.setLink("https://tunein.com/radio/radio-federal-am-810-s293827/");
        programa.setImagen(R.drawable.zzzmundoazulgrana);
        programa.setDiaUno("Lunes a Viernes 16Hs");
        programa.setLunes(true);
        programa.setMartes(true);
        programa.setMiercoles(true);
        programa.setJueves(true);
        programa.setViernes(true);
        programa.setConductores("Rubén Becerra - Aldo Gaibuz");
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo Equipo Desafío
        programa = realm.where(Programa.class).equalTo("nombre", "Equipo Desafío").findFirst();
        realm.beginTransaction();
        programa.setImagen(R.drawable.zzzequipodesafio);
        programa.setEmisora("AM1050");
        programa.setLink("http://tunein.com/radio/Radio-General-G%C3%BCemes-1050-s135700/");
        programa.setDiaUno("Días de partidos");
        programa.setDiaDos("");
        programa.setMartes(false);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        // Actualizo San Lorenzo en acción
        programa = realm.where(Programa.class).equalTo("nombre", "San Lorenzo en acción").findFirst();
        realm.beginTransaction();
        programa.setFacebook("NO DISPONIBLE");
        programa.setImagen(R.drawable.zzzsanlorenzoenaccion);
        programa.setJueves(false);
        programa.setDiaUno("Días de partidos");
        programa.setDiaDos("");
        programa.setNoche(false);
        realm.copyToRealmOrUpdate(programa);
        realm.commitTransaction();

        //Agrego los programas nuevos
        programa = realm.where(Programa.class).equalTo("topicNotificacion", "LosCuervosDelSur").findFirst();
        if (programa == null){
            Programa p30 = new Programa("Los cuervos del sur", R.drawable.zzzzloscuervosdelsur, "", "FM 91.7", "cuervosdelsur@sanlorenzo.org.ar", "www.loscuervosdelsur.com.ar", "https://twitter.com/LosCuervosdlSur", "https://www.facebook.com/LosCuervosdelSur/", "NO DISPONIBLE", true, false, false, false, false, false, false, false, "Lunes 20Hs", "", "FM", "https://tunein.com/embed/player/s103248/", false, false, "LosCuervosDelSur", false, false, true);
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(p30);
            realm.commitTransaction();
        }
        programa = realm.where(Programa.class).equalTo("topicNotificacion", "PeriodismoSanto").findFirst();
        if (programa == null){
            Programa p31 = new Programa("Periodismo Santo", R.drawable.zzzzperiodismocuervo, "Júlio Axel - Hernán O'Donnell - León Espósito - Adolfo Res", "América Sports (Canal 115)", "NO DISPONIBLE", "NO DISPONIBLE", "https://twitter.com/PeriodismoSanto", "NO DISPONIBLE", "NO DISPONIBLE", false, true, true, false, false, false, false, false, "Martes 20:30Hs", "Miercoles 0:30", "TV", "NO DISPONIBLE", false, false, "PeriodismoSanto", false, false, true);
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(p31);
            realm.commitTransaction();
        }




        //Borro los programas que no van mas
        try {
            programa = realm.where(Programa.class).equalTo("nombre", "Gente de San Lorenzo").findFirst();
            realm.beginTransaction();
            programa.deleteFromRealm();
            realm.commitTransaction();
        } catch (Exception e) {
        }
        try {
            programa = realm.where(Programa.class).equalTo("nombre", "Hablemos de San Lorenzo").findFirst();
            realm.beginTransaction();
            programa.deleteFromRealm();
            realm.commitTransaction();
        } catch (Exception e) {
        }
        programa = realm.where(Programa.class).equalTo("nombre", "Simplemente San Lorenzo").findFirst();
        try {
            realm.beginTransaction();
            programa.deleteFromRealm();
            realm.commitTransaction();
        } catch (Exception e) {
        }

        saveOnPreferences("hayUpdate3");
    }

    private boolean validarHayUpdate() {
        return prefs.getBoolean("hayUpdate", false);
    }

    private boolean validarHayUpdate2() {
        return prefs.getBoolean("hayUpdate2", false);
    }

    private boolean validarHayUpdate3() {
        return prefs.getBoolean("hayUpdate3", false);
    }

    private <T extends RealmObject> AtomicInteger setAtomicId(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("Id").intValue()) : new AtomicInteger();
    }

    private void removeAll() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    private void iniciarListaProgramas() {
        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                Programa p1 = new Programa("A Todo Ciclón", R.drawable.zzzatodociclon, "Oscar Sacco", "AM810", "saccofutbol@gmail.com", "NO DISPONIBLE", "https://twitter.com/atodociclonsl", "https://www.facebook.com/Atodociclonsl-791938394307854/", "NO DISPONIBLE", true, false, false, false, true, false, false, false, "Lunes y Viernes 19Hs", "", "AM", "https://tunein.com/radio/radio-federal-am-810-s293827/", false, false, "ATodoCiclon", false, true, false);
                Programa p2 = new Programa("A Todo San Lorenzo", R.drawable.zzzatodosanlorenzo, "Carlos García - Fernando Coria", "AM1490", "coriafutbol@hotmail.com", "NO DISPONIBLE", "https://twitter.com/ATodoSanLorenzo", "https://www.facebook.com/ATodoSanLorenzoGama/", "4218-5333 / 15-4565-2090", true, false, false, true, false, false, false, true, "Lunes y Jueves 21Hs", "Días de partidos", "AM", "http://tunein.com/radio/Radio-Gama-1490-s86865/", false, false, "ATodoSanLorenzo", false, false, true);
                Programa p3 = new Programa("Buenas y Santas", R.drawable.zzzbuenasysantas, "", "AM1090", "buenasysantasok@yahoo.com", "NO DISPONIBLE", "https://twitter.com/buenasysantasok", "NO DISPONIBLE", "4926-1623", false, true, false, false, false, false, false, false, "Martes 19Hs", "", "AM", "http://www.radio1090.com.ar/streaming/radioonline.html", false, false, "BuenasYSantas", false, true, false);
                Programa p4 = new Programa("Cuervos Prohibidos", R.drawable.zzzcuervosprohibidos, "D. Stasevich", "FM 92.5", "prohibidoscuervos@gmail.com", "NO DISPONIBLE", "https://twitter.com/cuervoprohibido", "https://www.facebook.com/CuervosProhibidos", "NO DISPONIBLE", true, false, false, false, false, false, false, false, "Lunes 22Hs", "", "FM", "http://tunein.com/radio/Signos-FM-925-s84810/", false, false, "CuervosProhibidos", false, false, true);
                Programa p5 = new Programa("Cuervos y basta", R.drawable.zzzcuervosybasta, "Jorge Galeazzo y Franco Landajuela", "FM 105.1 San Nicolas", "radio105sn@gmail.com", "NO DISPONIBLE", "NO DISPONIBLE", "NO DISPONIBLE", "(0336) 4439254 / whatsapp (336) 4574447", true, false, false, false, false, false, false, false, "Lunes 19Hs", "", "FM", "http://tunein.com/radio/La-Cinco--de-San-Nicol%C3%A1s-1051-s109221/", false, false, "CuervosYBasta", false, true, false);
                Programa p6 = new Programa("El café del ciclón", R.drawable.zzzelcafedelciclon, "Mariano Bongiorno", "AM1290", "elcafedelciclon@yahoo.com.ar", "http://www.elcafedelciclon.com.ar/", "https://twitter.com/elcafedelciclon", "NO DISPONIBLE", "NO DISPONIBLE", false, false, false, true, false, false, false, false, "Jueves 17hs", "", "AM", "http://us7.maindigitalstream.com/2259/", false, false, "ElCafeDelCiclon", false, true, false);
                Programa p7 = new Programa("El Plateista", R.drawable.zzzelplateista, "Carlos Canissa", "AM810", "ccanissa@gmail.com", "http://www.elplateista.com.ar/", "https://twitter.com/elplateista", "https://www.facebook.com/ElPlateista", "", false, false, true, false, false, false, false, false, "Miércoles 21Hs", "", "AM", "https://tunein.com/radio/radio-federal-am-810-s293827/", false, false, "ElPlateista", false, false, true);
                Programa p8 = new Programa("Equipo Desafío", R.drawable.zzzequipodesafio, "Julio Axel / Maximiliano Berardo", "AM1050", "mensajes@equipodesafio.com", "http://www.equipodesafio.com/", "https://twitter.com/equipodesafio", "https://www.facebook.com/Equipo-Desaf%C3%ADo-362011670569256/", "NO DISPONIBLE", false, false, false, false, false, false, false, true, "Días de partidos", "", "AM", "http://tunein.com/radio/Radio-General-G%C3%BCemes-1050-s135700/", false, false, "EquipoDesafio", false, false, false);
                Programa p9 = new Programa("Estación Boedo", R.drawable.zzzestacionboedo, "Antonella González - Juan Cruz De Rosa - Matías Ávila - Claudio Pandelo", "FM105.9", "estacionboedo6@gmail.com", "NO DISPONIBLE", "https://twitter.com/estacionboedo", "https://www.facebook.com/Estacion-Boedo-PQV-792475577446550/", "4912-1059 / 15-4079-2402 (WhatsApp)", true, false, false, false, false, false, false, false, "Lunes 21Hs", "", "FM", "", false, false, "EstacionBoedo", false, false, true);
                Programa p10 = new Programa("Frenesi Azulgrana", R.drawable.zzzfrenesiazulgrana, "Alejandro Marrero , Ale Romero y Pepe Vázquez", "AM1050", "frenesiazulgranaradio@outlook.com", "http://www.frenesiazulgrana.com/", "https://twitter.com/FrenesiAzulgran", "https://www.facebook.com/frenesiazulgrana/", "4381-1672 / 4381 - 2569", true, true, true, true, true, false, false, false, "Lunes a Viernes 19Hs", "", "AM", "http://tunein.com/radio/Radio-General-G%C3%BCemes-1050-s135700/", false, false, "FrenesiAzulgrana", false, true, false);
                // Programa p11 = new Programa("Gente de San Lorenzo", R.drawable.zzzgentedesanlorenzo, "Claudio Morrone", "AM840", "NO DISPONIBLE", "NO DISPONIBLE", "NO DISPONIBLE", "NO DISPONIBLE", "NO DISPONIBLE", false, false, false, false, true, false, false, false, "Viernes 20Hs", "", "AM", "http://tunein.com/radio/Radio-General-Belgrano-840-s84325/", false, false, "GenteDeSanLorenzo", false, false, true);
                Programa p12 = new Programa("Glorioso San Lorenzo", R.drawable.zzzgloriososanlorenzo, "NO DISPONIBLE", "FM88.1", "marcosnext@hotmail.com", " No disponible", "https://twitter.com/gloriosocasla", " No disponible", " No disponible", false, false, true, false, false, false, false, true, "Miércoles 21Hs", "Días de partidos", "FM", "", false, false, "GloriosoSanLorenzo", false, false, true);
                // Programa p13 = new Programa("Hablemos de San Lorenzo", R.drawable.zzzhablemosdesanlorenzo, "Cristian Pagliaro, Rodrigo Castellano y Nicolas Morandi", "AM770", "cris_pagliaro05@yahoo.com.ar", "http://www.desanlorenzo.com/", "https://twitter.com/hablemosdesl", "https://www.facebook.com/hablemosdesl/", "5275-0770/71/72", true, true, true, true, true, false, true, false, "Lunes a Viernes 22Hs", "Domingos 22Hs", "AM", "http://tunein.com/radio/Radio-Cooperativa-770-s101604/", false, false, "HablemosDeSanLorenzo", false, false, true);
                Programa p14 = new Programa("La Cicloneta", R.drawable.zzzlacicloneta, "Leandro Alves", "AM770", "lacicloneta@gmail.com", "NO DISPONIBLE", "https://twitter.com/lacicloneta", "https://www.facebook.com/LaCiclonetaRadio/", "5275-0770", true, true, true, true, true, false, false, false, "Lunes a Viernes 22Hs", "", "AM", "http://tunein.com/radio/Radio-Cooperativa-770-s101604/", false, false, "LaCicloneta", false, false, true);
                Programa p15 = new Programa("La Cuervería", R.drawable.zzzlacuerveria, "Cristian Paladino", "AM840", "lacuerveria840@yahoo.com.ar", "http://www.semilleroazulgrana.com.ar/", "https://twitter.com/lacuerveria", "https://www.facebook.com/pg/LaCuerveriaOficial", "NO DISPONIBLE", true, false, false, false, false, false, false, false, "Lunes 20Hs", "", "AM", "http://tunein.com/radio/Radio-General-Belgrano-840-s84325/", false, false, "LaCuerveria", false, false, true);
                Programa p16 = new Programa("La hora del Ciclón", R.drawable.zzzlahoradelciclon, "Mario Massi", "AM890", "alejogarcia090380@gmail.com.ar", "NO DISPONIBLE", "https://twitter.com/lahoradelciclon", "https://www.facebook.com/marioeduardo.massi", "NO DISPONIBLE", true, true, true, false, true, false, false, false, "Lunes, martes, miércoles y viernes 19Hs", "", "AM", "http://tunein.com/radio/Radio-Libre-890-s137009/", false, false, "LaHoraDelCiclon", false, true, false);
                Programa p17 = new Programa("Mundo Azulgrana radio", R.drawable.zzzmundoazulgrana, "Rubén Becerra - Aldo Gaibuz", "AM810", "NO DISPONIBLE", "http://www.mundoazulgrana.com.ar", "https://twitter.com/mundoazulgrana", "https://www.facebook.com/mundoazulgrana/", "4641-3920 /4600-3298", true, true, true, true, true, false, false, false, "Lunes a Viernes 16Hs", "", "AM", "https://tunein.com/radio/radio-federal-am-810-s293827/", false, false, "MundoAzulgranaRadio", true, false, true);
                Programa p18 = new Programa("Opinión azulgrana", R.drawable.zzzopinionazulgrana, "Carlos Perez Castex", "AM1490", "carlosperezcastex@hotmail.com.ar", "NO DISPONIBLE", "https://twitter.com/opazulgrana", "NO DISPONIBLE", "NO DISPONIBLE", false, true, true, true, false, false, true, false, "Domingo, Martes, Miércoles y Jueves 23Hs", "", "AM", "http://www.ustream.tv/channel/22120664", false, false, "OpinionAzulGrana", false, false, true);
                Programa p19 = new Programa("San Lorenzo ayer, hoy y siempre", R.drawable.zzzsanlorenzoayerhoyysiempre, "Adolfo Res y Diego Resnik", "AM970", "sanlorenzoayerhoysiempre@yahoo.com.ar", "http://volveavenidalaplata.com.ar/", "https://twitter.com/caslahistoria", "https://www.facebook.com/CASLAhistoria/", "4926-1894/4926-0041", false, false, false, false, false, false, true, false, "Domingos 13Hs", "", "AM", "http://tunein.com/radio/Radio-G%C3%A9nesis-970-s228340/", false, false, "SanLorenzoAyerHoyYSiempre", false, true, false);
                Programa p20 = new Programa("San Lorenzo de América", R.drawable.zzzsanlorenzodeamerica, "Diego Arvilly", "FM93.1", "nicomormandi22@gmail.com", "NO DISPONIBLE", "https://twitter.com/SLDAradio", "https://www.facebook.com/SLDAradio/", "NO DISPONIBLE", false, true, false, false, false, false, false, false, "Martes 22Hs", "", "FM", "http://tunein.com/radio/Late-931-FM-s184735/", false, false, "SanLorenzoDeAmerica", false, false, true);
                Programa p21 = new Programa("San Lorenzo en acción", R.drawable.zzzsanlorenzoenaccion, "Sebastian Cambas - Lucas Celada", "AM1590", "sanlorenzoenaccion@hotmail.com", "NO DISPONIBLE", "https://twitter.com/slenaccion", "NO DISPONIBLE", "4382-4293", false, false, false, false, false, false, false, true, "Días de partidos", "", "AM", "http://www.am1590.com.ar/", false, false, "SanLorenzoEnAccion", false, false, false);
                Programa p22 = new Programa("San Lorenzo eterno", R.drawable.zzzsanlorenzoeternonew, "Marcelo Culotta", "AM970", "sanlorenzoeterno@gmail.com", "NO DISPONIBLE", "https://twitter.com/sleterno", "https://www.facebook.com/SanLorenzoEterno/", "4926-1622", false, true, false, false, false, false, false, false, "Martes 20Hs", "", "AM", "http://tunein.com/radio/Radio-G%C3%A9nesis-970-s228340/", false, false, "SanLorenzoEterno", false, false, true);
                Programa p23 = new Programa("San Lorenzo mi pasión", R.drawable.zzzsanlorenzomipasionnew, "Víctor Federico", "AM970", "victorfederico970@gmail.com", "NO DISPONIBLE", "https://twitter.com/vueltaboedo", "https://www.facebook.com/victor.federico.7", "NO DISPONIBLE", false, false, false, false, false, true, false, false, "Sábados 12Hs", "", "AM", "http://tunein.com/radio/Radio-G%C3%A9nesis-970-s228340/", false, false, "SanLorenzoMiPasion", true, false, false);
                Programa p24 = new Programa("Sentimiento Azulgrana", R.drawable.zzzsentimientoazulgrana, "Mario Massi", "AM890/810", "alejogarcia10@hotmail.com", "NO DISPONIBLE", "NO DISPONIBLE", "https://www.facebook.com/profile.php?id=100006642735130", "NO DISPONIBLE", false, false, false, false, false, false, false, true, "Días de partidos", "", "AM", "http://tunein.com/radio/Radio-Libre-890-s137009/", false, false, "SentimientoAzulGrana", false, false, false);
                //Programa p25 = new Programa("Simplemente San Lorenzo", R.drawable.zzzsimplementesanlorenzo,"Gustavo Bennasar y Adrián Disabato", "AM610", "simplementesanlorenzo@hotmail.com","http://simplementesanlorenzoweb.blogspot.com.ar/","NO DISPONIBLE","https://www.facebook.com/SimplementeSanLorenzo/","4542-6500",false,false,false,false,true,false,false,false,"Viernes 14hs","","AM","http://tunein.com/radio/AM610-Radio-General-San-Martin-s253609/",false,false,"SimplementeSanLorenzo",false,true,true);
                Programa p26 = new Programa("Soy San Lorenzo", R.drawable.zzzsoysanlorenzo, "Mario Andrés Benigni", "AM810", "soysanlorenzo@gmail.com", "http://www.soysanlorenzo.com.ar/", "https://twitter.com/cirujanomb", "https://www.facebook.com/soysan.lorenzo.3", "46425533 / 46425315 / 15-5335-0310", true, true, true, true, true, false, false, false, "Lunes a Viernes 23Hs", "", "AM", "https://tunein.com/radio/radio-federal-am-810-s293827/", false, false, "SoySanLorenzo", false, false, true);
                Programa p27 = new Programa("Equipo Desafío TV", R.drawable.zzzequipodesafio, "Julio Axel yPablo Sassone", "Canal 360TV", "mensajes@equipodesafio.com", "http://www.equipodesafio.com/", "https://twitter.com/equipodesafio", "https://www.facebook.com/Equipo-Desaf%C3%ADo-362011670569256/", "NO DISPONIBLE", false, true, true, false, false, false, false, false, "Martes 20Hs", "Miércoles 13Hs", "TV", "", false, false, "EquipoDesafioTV", false, true, true);
                Programa p28 = new Programa("La Botica de Boedo", R.drawable.zlaboticadeboedo, "", "AM970", "NO DISPONIBLE", "NO DISPONIBLE", "https://twitter.com/BoticaBoedo", "NO DISPONIBLE", "NO DISPONIBLE", false, false, false, false, true, false, false, false, "Viernes 21Hs", "", "AM", "http://tunein.com/radio/Radio-G%C3%A9nesis-970-s228340/", false, false, "LaBoticaDeBoedo", false, false, true);
                Programa p29 = new Programa("Pintalo de cuervo", R.drawable.zzpintalodecuervo, "Ariel Petrosino - Leonardo Álvarez - Gustavo Lavalle", "www.larz.com.ar", "NO DISPONIBLE", "NO DISPONIBLE", "https://twitter.com/pdcuervo2017", "https://www.facebook.com/1982pdc/", "NO DISPONIBLE", true, false, false, false, false, false, false, false, "Lunes 22Hs", "", "", "http://www.larz.com.ar", false, false, "PintalodeCuervo", false, false, true);
                Programa p30 = new Programa("Los cuervos del sur", R.drawable.zzzzloscuervosdelsur, "", "FM 91.7", "cuervosdelsur@sanlorenzo.org.ar", "www.loscuervosdelsur.com.ar", "https://twitter.com/LosCuervosdlSur", "https://www.facebook.com/LosCuervosdelSur/", "NO DISPONIBLE", true, false, false, false, false, false, false, false, "Lunes 20Hs", "", "FM", "https://tunein.com/embed/player/s103248/", false, false, "LosCuervosDelSur", false, false, true);
                Programa p31 = new Programa("Periodismo Santo", R.drawable.zzzzperiodismocuervo, "Júlio Axel - Hernán O'Donnell - León Espósito - Adolfo Res", "América Sports (Canal 115)", "NO DISPONIBLE", "NO DISPONIBLE", "https://twitter.com/PeriodismoSanto", "NO DISPONIBLE", "NO DISPONIBLE", false, true, true, false, false, false, false, false, "Martes 20:30Hs", "Miercoles 0:30", "TV", "NO DISPONIBLE", false, false, "PeriodismoSanto", false, false, true);


                realm.copyToRealmOrUpdate(p1);
                realm.copyToRealmOrUpdate(p2);
                realm.copyToRealmOrUpdate(p3);
                realm.copyToRealmOrUpdate(p4);
                realm.copyToRealmOrUpdate(p5);
                realm.copyToRealmOrUpdate(p6);
                realm.copyToRealmOrUpdate(p7);
                realm.copyToRealmOrUpdate(p8);
                realm.copyToRealmOrUpdate(p9);
                realm.copyToRealmOrUpdate(p10);
                //realm.copyToRealmOrUpdate(p11);
                realm.copyToRealmOrUpdate(p12);
                //realm.copyToRealmOrUpdate(p13);
                realm.copyToRealmOrUpdate(p14);
                realm.copyToRealmOrUpdate(p15);
                realm.copyToRealmOrUpdate(p16);
                realm.copyToRealmOrUpdate(p17);
                realm.copyToRealmOrUpdate(p18);
                realm.copyToRealmOrUpdate(p19);
                realm.copyToRealmOrUpdate(p20);
                realm.copyToRealmOrUpdate(p21);
                realm.copyToRealmOrUpdate(p22);
                realm.copyToRealmOrUpdate(p23);
                realm.copyToRealmOrUpdate(p24);
                //realm.copyToRealmOrUpdate(p25);
                realm.copyToRealmOrUpdate(p26);
                realm.copyToRealmOrUpdate(p27);
                realm.copyToRealmOrUpdate(p28);
                realm.copyToRealmOrUpdate(p29);
                realm.copyToRealmOrUpdate(p30);
                realm.copyToRealmOrUpdate(p31);


                saveOnPreferences("firstTime");
                saveOnPreferences("hayUpdate");
                saveOnPreferences("hayUpdate2");
                saveOnPreferences("hayUpdate3");
            }
        });
    }


    /*  Para enviar mensajes a Whatsapp
    public void onClickWhatsApp(View view) {

    PackageManager pm=getPackageManager();
    try {

        Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("text/plain");
        String text = "YOUR TEXT HERE";

        PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
        //Check if package exists or not. If not then code
        //in catch block will be called
        waIntent.setPackage("com.whatsapp");

        waIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(waIntent, "Share with"));

   } catch (NameNotFoundException e) {
        Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                .show();
   }

}
     */
}

