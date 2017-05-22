package android.dariojolo.com.ar.programaspartidarios.app;

import android.app.Application;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.dariojolo.com.ar.programaspartidarios.models.Programa;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void onCreate() {
        super.onCreate();
        //removeAll();
        initRealm();
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
}

