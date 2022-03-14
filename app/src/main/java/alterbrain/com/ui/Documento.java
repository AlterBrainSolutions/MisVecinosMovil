package alterbrain.com.ui;

public class Documento {
    private int idDocument;
    private int idDocFracc;
    private String titulodoc;
    private String fechadoc;
    private String ruta;

    public Documento(int idDocument, int idDocFracc, String titulodoc, String fechadoc, String ruta) {
        this.idDocument = idDocument;
        this.idDocFracc = idDocFracc;
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

    public int getIdDocument() { return idDocument; }

    public void setIdDocument(int idDocument) { this.idDocument = idDocument; }

    public int getIdDocFracc() { return idDocFracc; }

    public void setIdDocFracc(int idDocFracc) { this.idDocFracc = idDocFracc; }
}
