package alterbrain.com.ui;

public class Deuda {
    private int id;
    private String fraccionamientoid;
    private String casa;
    private int mesesdeuda;
    private String fecha;

    public Deuda(int id, String fraccionamientoid, String casa, int mesesdeuda, String fecha) {
        this.id = id;
        this.fraccionamientoid = fraccionamientoid;
        this.casa = casa;
        this.mesesdeuda = mesesdeuda;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFraccionamientoid() {
        return fraccionamientoid;
    }

    public void setFraccionamientoid(String fraccionamientoid) {
        this.fraccionamientoid = fraccionamientoid;
    }

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public int getMesesdeuda() {
        return mesesdeuda;
    }

    public void setMesesdeuda(int mesesdeuda) {
        this.mesesdeuda = mesesdeuda;
    }
}
