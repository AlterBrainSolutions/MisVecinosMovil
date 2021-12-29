package alterbrain.com.ui;

public class Acceso {
    private int idAcceso;
    private int idAccesoUsuario;
    private String tiempo;
    private String nombre;
    private String fechaRegs;
    private String fechaVisit;
    private String horaEntrada;
    private String horaSalida;
    private String tipoVisitante;
    private String comentarios;

    public Acceso() {
    }

    public int getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    public int getIdAccesoUsuario() {
        return idAccesoUsuario;
    }

    public void setIdAccesoUsuario(int idAccesoUsuario) {
        this.idAccesoUsuario = idAccesoUsuario;
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

    public String getFechaRegs() {
        return fechaRegs;
    }

    public void setFechaRegs(String fechaRegs) {
        this.fechaRegs = fechaRegs;
    }

    public String getFechaVisit() {
        return fechaVisit;
    }

    public void setFechaVisit(String fechaVisit) {
        this.fechaVisit = fechaVisit;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getTipoVisitante() {
        return tipoVisitante;
    }

    public void setTipoVisitante(String tipoVisitante) {
        this.tipoVisitante = tipoVisitante;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
