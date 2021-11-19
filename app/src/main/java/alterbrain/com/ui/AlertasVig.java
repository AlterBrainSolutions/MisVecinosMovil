package alterbrain.com.ui;

public class AlertasVig {
    private int idSeguridad;
    private String numCasa;
    private String fechaActivacion;
    private String horaActivacion;
    private int idVig;
    private String protocoloRealizado;

    public AlertasVig() {
    }

    public AlertasVig(int idSeguridad, String numCasa, String fechaActivacion) {
        this.idSeguridad = idSeguridad;
        this.numCasa = numCasa;
        this.fechaActivacion = fechaActivacion;
    }

    public AlertasVig(int idSeguridad, String numCasa, String fechaActivacion, String horaActivacion, int idVig, String protocoloRealizado) {
        this.idSeguridad = idSeguridad;
        this.numCasa = numCasa;
        this.fechaActivacion = fechaActivacion;
        this.horaActivacion = horaActivacion;
        this.idVig = idVig;
        this.protocoloRealizado = protocoloRealizado;
    }

    public int getIdSeguridad() {
        return idSeguridad;
    }

    public void setIdSeguridad(int idSeguridad) {
        this.idSeguridad = idSeguridad;
    }

    public String getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    public String getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(String fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public String getHoraActivacion() {
        return horaActivacion;
    }

    public void setHoraActivacion(String horaActivacion) {
        this.horaActivacion = horaActivacion;
    }

    public int getIdVig() {
        return idVig;
    }

    public void setIdVig(int idVig) {
        this.idVig = idVig;
    }

    public String getProtocoloRealizado() {
        return protocoloRealizado;
    }

    public void setProtocoloRealizado(String protocoloRealizado) {
        this.protocoloRealizado = protocoloRealizado;
    }
}
