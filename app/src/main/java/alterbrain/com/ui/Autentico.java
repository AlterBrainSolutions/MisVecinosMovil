package alterbrain.com.ui;

public class Autentico {

    private int idAcceso;
    private String nombre;

    public Autentico(int idAcceso, String nombre) {
        this.idAcceso = idAcceso;
        this.nombre = nombre;
    }

    public int getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
