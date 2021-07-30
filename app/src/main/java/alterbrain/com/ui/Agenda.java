package alterbrain.com.ui;

public class Agenda {
    private String tipo;
    private String asunto;
    private String responsable;
    private String fecha;

    public Agenda(String tipo, String asunto, String responsable, String fecha) {
        this.tipo = tipo;
        this.asunto = asunto;
        this.responsable = responsable;
        this.fecha = fecha;
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
}
