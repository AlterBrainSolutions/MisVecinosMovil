package alterbrain.com.ui;

public class CasasVig {
    private int idUsu;
    private int idFraccionamientoUsu;
    private int idRol;
    private String numeroCasa;
    private String nombreUsu;
    private String email;
    private String contrasenia;
    private String codAcces;

    public CasasVig() {
    }

    public CasasVig(int idUsu, String numeroCasa) {
        this.idUsu = idUsu;
        this.numeroCasa = numeroCasa;
    }

    public CasasVig(int idUsu, int idFraccionamientoUsu, int idRol, String numeroCasa, String nombreUsu, String email, String contrasenia, String codAcces) {
        this.idUsu = idUsu;
        this.idFraccionamientoUsu = idFraccionamientoUsu;
        this.idRol = idRol;
        this.numeroCasa = numeroCasa;
        this.nombreUsu = nombreUsu;
        this.email = email;
        this.contrasenia = contrasenia;
        this.codAcces = codAcces;
    }

    public int getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(int idUsu) {
        this.idUsu = idUsu;
    }

    public int getIdFraccionamientoUsu() {
        return idFraccionamientoUsu;
    }

    public void setIdFraccionamientoUsu(int idFraccionamientoUsu) {
        this.idFraccionamientoUsu = idFraccionamientoUsu;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCodAcces() {
        return codAcces;
    }

    public void setCodAcces(String codAcces) {
        this.codAcces = codAcces;
    }
}
