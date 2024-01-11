package br.edu.utfpr.javai.aula06.heranca.atividade3example;

public class Mae extends Genitores {
    private String corVestido;

    public Mae() {
        this.corVestido = "";
    }

    public Mae(String corVestido) {
        this.corVestido = corVestido;
    }

    public String getCorVestido() {
        return corVestido;
    }

    public void setCorVestido(String corVestido) {
        this.corVestido = corVestido;
    }

    @Override
    public int calcRg() {
        return this.getRgGens() * 1000;
    }

    public void imprimirDadosMae() {
        StringBuilder stringBuilder = new StringBuilder("***********************************\n");

        stringBuilder.append("\tDados da Mae");
        stringBuilder.append("\n***********************************");
        stringBuilder.append("\nRG: " + getRgGens());
        stringBuilder.append("\nRG calculado: " + calcRg());
        stringBuilder.append("\nNome: " + getNomeGens());
        stringBuilder.append("\nCor do Vestido: " + getCorVestido());
        stringBuilder.append("\nRG do filho: " + getFilhoGens().getRgFilho());
        stringBuilder.append("\nNome do filho: " + getFilhoGens().getNomeFilho());

        System.out.println(stringBuilder);
    }
}