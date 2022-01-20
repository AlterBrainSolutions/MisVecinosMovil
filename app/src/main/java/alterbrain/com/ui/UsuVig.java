package alterbrain.com.ui;

public class UsuVig {
     private int idVigilante;
     private int idFraccDVig;
     private String nombre;
     private String imagen;
     private String direccion;
     private double telefono;
     private String horario;

    public UsuVig() {
    }

    public int getIdVigilante() {
        return idVigilante;
    }

    public void setIdVigilante(int idVigilante) {
        this.idVigilante = idVigilante;
    }

    public int getIdFraccDVig() {
        return idFraccDVig;
    }

    public void setIdFraccDVig(int ifFraccDVig) {
        this.idFraccDVig = ifFraccDVig;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
