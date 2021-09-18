package alterbrain.com.model;

public class detalleReciclaje {
    private int contPet;
    private int contAlum;


    public detalleReciclaje(int contPet, int contAlum) {
        this.contPet = contPet;
        this.contAlum = contAlum;

    }

    public detalleReciclaje() {
    }

    public int getContPet() {
        return contPet;
    }

    public void setContPet(int contPet) {
        this.contPet = contPet;
    }

    public int getContAlum() {
        return contAlum;
    }

    public void setTitulo(int contAlum) {
        this.contAlum = contAlum;
    }

}
