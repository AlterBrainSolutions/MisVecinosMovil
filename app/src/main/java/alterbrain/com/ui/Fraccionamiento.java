package alterbrain.com.ui;

public class Fraccionamiento {
    private int idFraccionamiento;
    private String linkFracc;
    private String nombreFracc;
    private String direccion;
    private double latitud;
    private double longitud;
    private int noCasas;
    private String nombreRecolector;
    private int contPet;
    private int contAlum;
    private String codigoContenedor;
    private String imagenFrc;


    public Fraccionamiento(int idFraccionamiento, String linkFracc, String nombreFracc, String direccion, double latitud, double longitud, int noCasas, String nombreRecolector, int contPet, int contAlum, String codigoContenedor, String imagenFrc) {
        this.idFraccionamiento = idFraccionamiento;
        this.linkFracc = linkFracc;
        this.nombreFracc = nombreFracc;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.noCasas = noCasas;
        this.nombreRecolector = nombreRecolector;
        this.contPet = contPet;
        this.contAlum = contAlum;
        this.codigoContenedor = codigoContenedor;
        this.imagenFrc = imagenFrc;
    }

    public Fraccionamiento() {
    }

    public Fraccionamiento(int idFraccionamiento, String nombreFracc) {
        this.idFraccionamiento = idFraccionamiento;
        this.nombreFracc = nombreFracc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getNombreRecolector() {
        return nombreRecolector;
    }

    public void setNombreRecolector(String nombreRecolector) {
        this.nombreRecolector = nombreRecolector;
    }

    public String getCodigoContenedor() {
        return codigoContenedor;
    }

    public void setCodigoContenedor(String codigoContenedor) {
        this.codigoContenedor = codigoContenedor;
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

    public String getImagenFrc() {
        return imagenFrc;
    }

    public void setImagenFrc(String imagenFrc) {
        this.imagenFrc = imagenFrc;
    }
}
