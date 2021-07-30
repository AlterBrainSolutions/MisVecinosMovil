package alterbrain.com.ui;

public class Conceptotr {
    private String concepto;
    private String pagocnc;

    public Conceptotr(String concepto, String pagocnc) {
        this.concepto = concepto;
        this.pagocnc = pagocnc;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getPagocnc() {
        return pagocnc;
    }

    public void setPagocnc(String pagocnc) {
        this.pagocnc = pagocnc;
    }
}
