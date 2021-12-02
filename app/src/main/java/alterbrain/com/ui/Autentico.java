package alterbrain.com.ui;

public class Autentico {

    private int idAcceso;
    private String nombre;
    private String fecha;
    private String tipoVisitante;
    private String comentario;
    private String codigo;

    public Autentico() {
    }

    public Autentico(int idAcceso, String nombre) {
        this.idAcceso = idAcceso;
        this.nombre = nombre;
    }

    public Autentico(String nombre, String fecha, String tipoVisitante, String comentario, String codigo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipoVisitante = tipoVisitante;
        this.comentario = comentario;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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



    public int getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
