package alterbrain.com.ui;

public class Fraccionamiento {
    private int idFraccionamiento;
    private String linkFracc;
    private String nombreFracc;
    private int noCasas;
    private int contPet;
    private int contAlum;

    public Fraccionamiento(int idFraccionamiento, String linkFracc, String nombreFracc, int noCasas, int contPet, int contAlum) {
        this.idFraccionamiento = idFraccionamiento;
        this.linkFracc = linkFracc;
        this.nombreFracc = nombreFracc;
        this.noCasas = noCasas;
        this.contPet = contPet;
        this.contAlum = contAlum;
    }

    public int getIdFraccionamiento() {
        return idFraccionamiento;
    }

    public void setIdFraccionamiento(int idFraccionamiento) {
        this.idFraccionamiento = idFraccionamiento;
    }

    public String getLinkFracc() {
        return linkFracc;
    }

    public void setLinkFracc(String linkFracc) {
        this.linkFracc = linkFracc;
    }

    public String getNombreFracc() {
        return nombreFracc;
    }

    public void setNombreFracc(String nombreFracc) {
        this.nombreFracc = nombreFracc;
    }

    public int getNoCasas() {
        return noCasas;
    }

    public void setNoCasas(int noCasas) {
        this.noCasas = noCasas;
    }

    public int getContPet() {
        return contPet;
    }

    public void setContPet(int contPet) {
        this.contPet = contPet;
    }

    public int getContAlum() {
        return contAlum;
    }

    public void setContAlum(int contAlum) {
        this.contAlum = contAlum;
    }
}
