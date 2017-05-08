package android.dariojolo.com.ar.programaspartidarios.models;

/**
 * Created by rodrigrl on 08/05/2017.
 */

public class Programa {
    private String nombre;
    private int imagen;

    public Programa(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
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
}
