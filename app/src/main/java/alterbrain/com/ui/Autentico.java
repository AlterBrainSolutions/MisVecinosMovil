package alterbrain.com.ui;

public class Autentico {

    private int idAcceso;
    private String tiempo;
    private String nombre;
    private String fechaVis;
    private String fechaReg;
    private String tipoVisitante;
    private String comentario;
    private String codigo;

    public Autentico() {
    }

    public Autentico(int idAcceso, String nombre) {
        this.idAcceso = idAcceso;
        this.nombre = nombre;
    }

    public Autentico(int idAcceso, String nombre, String fechaVis, String comentario) {
        this.idAcceso = idAcceso;
        this.nombre = nombre;
        this.fechaVis = fechaVis;
        this.comentario = comentario;
    }

    public Autentico(String nombre, String fechaVis, String tipoVisitante, String comentario, String codigo) {
        this.nombre = nombre;
        this.fechaVis = fechaVis;
        this.tipoVisitante = tipoVisitante;
        this.comentario = comentario;
        this.codigo = codigo;
    }

    public int getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(String fechaReg) {
        this.fechaReg = fechaReg;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaVis() {
        return fechaVis;
    }

    public void setFechaVis(String fechaVis) {
        this.fechaVis = fechaVis;
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
