package alterbrain.com.ui;

public class Autentico2 {

    private String nombre;
    private String fecha;
    private String tipoVisitante;
    private String comentario;

    public Autentico2(String nombre, String fecha, String tipoVisitante, String comentario) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipoVisitante = tipoVisitante;
        this.comentario = comentario;
    }

    //constructor usado en ConsultaAutnActivity.java
    public Autentico2() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoVisitante() {
        return tipoVisitante;
    }

    public void setTipoVisitante(String tipoVisitante) {
        this.tipoVisitante = tipoVisitante;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
