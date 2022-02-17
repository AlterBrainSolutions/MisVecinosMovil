package alterbrain.com.ui;

public class Oficio {

    //propiedades del usuario que brinda Oficios
    private int idOficio;
    private String nomOficio;
    private String direccion;
    private String telefono;
    private String email;
    private String imagenPer;
    private String imagenEstb;
    private String usuario;
    private String pass;

    public Oficio() {
    }

    public Oficio(int idOficio, String nomOficio, String direccion, String telefono, String email, String imagenPer, String imagenEstb) {
        this.idOficio = idOficio;
        this.nomOficio = nomOficio;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.imagenPer = imagenPer;
        this.imagenEstb = imagenEstb;
    }

    public Oficio(int idOficio, String nomOficio) {
        this.idOficio = idOficio;
        this.nomOficio = nomOficio;
    }

    public int getIdOficio() {
        return idOficio;
    }

    public void setIdOficio(int idOficio) {
        this.idOficio = idOficio;
    }

    public String getNomOficio() {
        return nomOficio;
    }

    public void setNomOficio(String nomOficio) {
        this.nomOficio = nomOficio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagenPer() {
        return imagenPer;
    }

    public void setImagenPer(String imagenPer) {
        this.imagenPer = imagenPer;
    }

    public String getImagenEstb() {
        return imagenEstb;
    }

    public void setImagenEstb(String imagenEstb) {
        this.imagenEstb = imagenEstb;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
