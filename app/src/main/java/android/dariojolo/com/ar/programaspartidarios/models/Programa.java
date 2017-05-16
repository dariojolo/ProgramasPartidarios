package android.dariojolo.com.ar.programaspartidarios.models;

import android.dariojolo.com.ar.programaspartidarios.app.MyApp;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rodrigrl on 08/05/2017.
 */

public class Programa extends RealmObject implements Serializable {

    @PrimaryKey
    private int Id;
    private String nombre;
    private String conductores;
    private String emisora;
    private int imagen;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private boolean diaPartido;

    public Programa(){} //Solo para Realm

    public Programa(String nombre, int imagen, String conductores, String emisora,boolean lunes,boolean martes,boolean miercoles,
                    boolean jueves,boolean viernes,boolean sabado, boolean domingo, boolean diaPartido){
        Id = MyApp.ProgramaID.incrementAndGet();
        this.nombre = nombre;
        this.imagen = imagen;
        this.conductores = conductores;
        this.emisora = emisora;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
        this.diaPartido = diaPartido;
    }

    public int getId(){
        return Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getConductores() {
        return conductores;
    }

    public void setConductores(String conductores) {
        this.conductores = conductores;
    }

    public String getEmisora() {
        return emisora;
    }

    public void setEmisora(String emisora) {
        this.emisora = emisora;
    }

    public boolean isLunes() {
        return lunes;
    }

    public boolean isMartes() {
        return martes;
    }

    public boolean isMiercoles() {
        return miercoles;
    }

    public boolean isJueves() {
        return jueves;
    }

    public boolean isViernes() {
        return viernes;
    }

    public boolean isSabado() {
        return sabado;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public boolean isDiaPartido() {
        return diaPartido;
    }
}
