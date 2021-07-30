package alterbrain.com.ui;

public class Noticia {
    private String fraccionamiento;
    private String asunto;
    private String  urlPhoto;
    private String fecha;
    private String idNoticia;

    public Noticia(String fraccionamiento, String asunto, String urlPhoto, String fecha, String idNoticia) {
        this.fraccionamiento = fraccionamiento;
        this.asunto = asunto;
        this.urlPhoto = urlPhoto;
        this.fecha = fecha;
        this.idNoticia = idNoticia;
    }

    public String getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(String idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getFraccionamiento() {
        return fraccionamiento;
    }

    public void setFraccionamiento(String fraccionamiento) {
        this.fraccionamiento = fraccionamiento;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
