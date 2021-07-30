package alterbrain.com.ui;

public class Autorizado {
    private int idautorizado;
    private String casaid;
    private String nombreinvi;
    private String imageninvi;

    public Autorizado(int idautorizado, String casaid, String nombreinvi, String imageninvi) {
        this.idautorizado = idautorizado;
        this.casaid = casaid;
        this.nombreinvi = nombreinvi;
        this.imageninvi = imageninvi;
    }

    public int getIdautorizado() {
        return idautorizado;
    }

    public void setIdautorizado(int idautorizado) {
        this.idautorizado = idautorizado;
    }

    public String getCasaid() {
        return casaid;
    }

    public void setCasaid(String casaid) {
        this.casaid = casaid;
    }

    public String getNombreinvi() {
        return nombreinvi;
    }

    public void setNombreinvi(String nombreinvi) {
        this.nombreinvi = nombreinvi;
    }

    public String getImageninvi() {
        return imageninvi;
    }

    public void setImageninvi(String imageninvi) {
        this.imageninvi = imageninvi;
    }
}
