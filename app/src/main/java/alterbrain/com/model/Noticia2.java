package alterbrain.com.model;

import java.util.Date;

public class Noticia2 {

    private String titulo;
    private String descripcion;
    private String fecha;
    private String idn;

    public Noticia2(){

    }
    public Noticia2(String titulo, String descripcion, String fecha, String idn) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idn = idn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdn() {
        return idn;
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }
}
