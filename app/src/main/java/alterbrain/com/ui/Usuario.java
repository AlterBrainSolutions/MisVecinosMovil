package alterbrain.com.ui;

public class Usuario {
    private int id;
    private int idFraccUsu;
    private String nombre;
    private String email;
    private String contra;
    private String codigo;
    private String numCasa;
    private String imagenUsu;

    public Usuario() {
    }

    public Usuario(int id, int idFraccUsu, String nombre, String email, String contra, String codigo, String numCasa, String imagenUsu) {
        this.id = id;
        this.idFraccUsu = idFraccUsu;
        this.nombre = nombre;
        this.email = email;
        this.contra = contra;
        this.codigo = codigo;
        this.numCasa = numCasa;
        this.imagenUsu = imagenUsu;
    }

    public String getImagenUsu() {
        return imagenUsu;
    }

    public void setImagenUsu(String imagenUsu) {
        this.imagenUsu = imagenUsu;
    }

    public int getIdFraccUsu() {
        return idFraccUsu;
    }

    public void setIdFraccUsu(int idFraccUsu) {
        this.idFraccUsu = idFraccUsu;
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

    public String getNumCasa() { return numCasa; }

    public void setNumCasa(String numCasa) { this.numCasa = numCasa; }

}
