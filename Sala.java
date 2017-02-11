package kino;

public class Sala {

    private String tytulFilmu;
    private int liczbaMiejsc, dlugoscFilmu, numerSali;

    public Sala(String tytulFilmu, int numerSali, int liczbaMiejsc, int dlugoscFilmu) {
        this.tytulFilmu = tytulFilmu;
        this.numerSali = numerSali;
        this.liczbaMiejsc = liczbaMiejsc;
        this.dlugoscFilmu = dlugoscFilmu;
    }

    public int pobierzNumer() {
        return numerSali;
    }

    public String pobierzTytul() {
        return tytulFilmu;
    }

    public int pobierzMiejsca() {
        return liczbaMiejsc;
    }

    public int pobierzCzas() {
        return dlugoscFilmu;
    }

    public void ustawMiejsca(int liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }
}
