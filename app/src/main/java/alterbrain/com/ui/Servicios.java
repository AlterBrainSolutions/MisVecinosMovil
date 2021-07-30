package alterbrain.com.ui;

public class Servicios {
    private int id;
    private String casa;
    private String fecha;
    private String descripcion;
    private String imagen;

    public Servicios(int id, String casa, String fecha, String descripcion, String imagen) {
        this.id = id;
        this.casa = casa;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Servicios() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
