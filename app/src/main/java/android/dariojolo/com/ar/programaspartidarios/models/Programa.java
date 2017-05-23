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
    private String eMail;
    private String twitter;
    private String facebook;
    private String telefono;
    private int imagen;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private boolean diaPartido;
    private String horaInicio;
    private String horaFin;
    private String medio;

    public Programa(){} //Solo para Realm

    public Programa(String nombre, int imagen, String conductores, String emisora,String eMail, String twitter, String facebook,String telefono, boolean lunes,boolean martes,boolean miercoles,
                    boolean jueves,boolean viernes,boolean sabado, boolean domingo, boolean diaPartido, String horaInicio, String horaFin, String medio){
        Id = MyApp.ProgramaID.incrementAndGet();
        this.nombre = nombre;
        this.imagen = imagen;
        this.conductores = conductores;
        this.emisora = emisora;
        this.eMail = eMail;
        this.twitter = twitter;
        this.facebook = facebook;
        this.telefono = telefono;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
        this.diaPartido = diaPartido;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.medio = medio;
    }

    public int getId(){
        return Id;
    }

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
