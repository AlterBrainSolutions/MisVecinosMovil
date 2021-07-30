package alterbrain.com.ui;

public class Visitante {
    private int id;
    private String fraccionamientoid;
    private String casa;
    private String imagen;

    public Visitante(int id, String fraccionamientoid, String casa, String imagen) {
        this.id = id;
        this.fraccionamientoid = fraccionamientoid;
        this.casa = casa;
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }
}
