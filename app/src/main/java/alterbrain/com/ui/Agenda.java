package alterbrain.com.ui;

public class Agenda {
    private  int idEvento;
    private String tipo;
    private String asunto;
    private String responsable;
    private String fecha;
    private String imagen;

    public Agenda(int idEvento, String tipo, String asunto, String responsable, String fecha, String imagen) {
        this.idEvento = idEvento;
        this.tipo = tipo;
        this.asunto = asunto;
        this.responsable = responsable;
        this.fecha = fecha;
        this.imagen = imagen;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagen() { return imagen; }

    public void setImagen(String imagen) { this.imagen = imagen; }
}
