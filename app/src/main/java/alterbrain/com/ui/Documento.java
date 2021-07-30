package alterbrain.com.ui;

public class Documento {
    private String titulodoc;
    private String fechadoc;
    private String ruta;

    public Documento(String titulodoc, String fechadoc, String ruta) {
        this.titulodoc = titulodoc;
        this.fechadoc = fechadoc;
        this.ruta = ruta;
    }

    public String getTitulodoc() {
        return titulodoc;
    }

    public void setTitulodoc(String titulodoc) {
        this.titulodoc = titulodoc;
    }

    public String getFechadoc() {
        return fechadoc;
    }

    public void setFechadoc(String fechadoc) {
        this.fechadoc = fechadoc;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
