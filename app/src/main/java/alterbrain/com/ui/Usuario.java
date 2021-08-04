package alterbrain.com.ui;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String contra;
    private String codigo;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String email, String contra, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contra = contra;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
