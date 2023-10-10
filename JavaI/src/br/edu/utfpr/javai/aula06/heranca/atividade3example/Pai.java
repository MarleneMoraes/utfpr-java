public class Pai extends Genitores {
    private String corCalca;

    public Pai(){
        this.corCalca = "branco"; 
    }

    public Pai(String corCalca) {
        this.corCalca = corCalca;
    }

    public String getCorCalca() {
        return corCalca;
    }

    public void setCorCalca(String corCalca) {
        this.corCalca = corCalca;
    }
}