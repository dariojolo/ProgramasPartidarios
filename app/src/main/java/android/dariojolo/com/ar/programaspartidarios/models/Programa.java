package android.dariojolo.com.ar.programaspartidarios.models;

import java.io.Serializable;

/**
 * Created by rodrigrl on 08/05/2017.
 */

public class Programa implements Serializable {
    private String nombre;
    private String conductores;
    private String emisora;
    private int imagen;

    public Programa(String nombre, int imagen, String conductores, String emisora ){
        this.nombre = nombre;
        this.imagen = imagen;
        this.conductores = conductores;
        this.emisora = emisora;
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
}
