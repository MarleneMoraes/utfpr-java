public abstract class Genitores {
    private int rgGens;
    private String nomeGens;
    private Filho filhoGens;

    public Genitores() { 
        this.rgGens = 0;
        this.nomeGens = "";
        this.filhoGens = new Filho();
    }

    public Genitores(int rgGens, String nomeGens, Filho filhoGens) {
        this.rgGens = rgGens;
        this.nomeGens = nomeGens;
        this.filhoGens = filhoGens; 
    }

    public int getRgGens() {
        return rgGens;
    }

    public void setRgGens(int rgGens) {
        this.rgGens = rgGens;
    }

    public String getNomeGens() {
        return nomeGens;
    }

    public void setNomeGens(String nomeGens) {
        this.nomeGens = nomeGens;
    }

    public Filho getFilhoGens() {
        return filhoGens;
    }

    public void setFilhoGens(Filho filhoGens) {
        this.filhoGens = filhoGens;
    }

    /*public abstract int calcRg() {
        return 0;
    }*/
}