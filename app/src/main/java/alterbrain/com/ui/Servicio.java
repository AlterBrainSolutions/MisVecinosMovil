package alterbrain.com.ui;

public class Servicio {
    private int id;
    private String casa;
    private String tipo;
    private String fecha;
    private String descripcion;
    private String imagen;
    private String presupuesto;

    public Servicio(int id, String casa, String tipo, String fecha, String descripcion, String imagen, String presupuesto) {
        this.id = id;
        this.casa = casa;
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.presupuesto = presupuesto;
    }

    public Servicio() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }
}
