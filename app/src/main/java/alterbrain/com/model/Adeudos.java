package alterbrain.com.model;

public class Adeudos {
    private int idAbonoRecibido;
    private int idAbonoEstatus;
    private String numeroCasa;
    private int idFraccionamientoUsuarios;
    private int mesesDeDeuda;

    public Adeudos(int idAbonoRecibo, int idAbonoEstatus, String numeroCasa, int idFraccionamientoUsuarios) {
        this.idAbonoRecibido = idAbonoRecibo;
        this.idAbonoEstatus = idAbonoEstatus;
        this.numeroCasa = numeroCasa;
        this.idFraccionamientoUsuarios = idFraccionamientoUsuarios;
    }

    public Adeudos(String numeroCasa, int mesesDeDeuda) {
        this.numeroCasa = numeroCasa;
        this.idFraccionamientoUsuarios = idFraccionamientoUsuarios;
        this.mesesDeDeuda = mesesDeDeuda;
    }

    public Adeudos() {
    }

    public int getIdAbonoRecibido() {
        return idAbonoRecibido;
    }

    public void setIdAbonoRecibido(int idAbonoRecibido) {
        this.idAbonoRecibido = idAbonoRecibido;
    }

    public int getIdAbonoEstatus() {
        return idAbonoEstatus;
    }

    public void setIdAbonoEstatus(int idAbonoEstatus) {
        this.idAbonoEstatus = idAbonoEstatus;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public int getIdFraccionamientoUsuarios() {
        return idFraccionamientoUsuarios;
    }

    public void setIdFraccionamientoUsuarios(int idFraccionamientoUsuarios) { this.idFraccionamientoUsuarios = idFraccionamientoUsuarios; }

    public int getMesesDeDeuda() { return mesesDeDeuda; }

    public void setMesesDeDeuda(int mesesDeDeuda) { this.mesesDeDeuda = mesesDeDeuda; }
}
