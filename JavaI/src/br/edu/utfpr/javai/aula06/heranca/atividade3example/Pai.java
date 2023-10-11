package br.edu.utfpr.javai.aula06.heranca.atividade3example;

public class Pai extends Genitores {
    private String corCalca;

    public Pai() {
        this.corCalca = "";
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

    @Override
    public int calcRg() {
        return this.getRgGens() * 100;
    }

    public void imprimirDadosPai() {
        StringBuilder stringBuilder = new StringBuilder("***********************************\n");

        stringBuilder.append("\tDados do Pai");
        stringBuilder.append("\n***********************************");
        stringBuilder.append("\nRG: " + getRgGens());
        stringBuilder.append("\nRG calculado: " + calcRg());
        stringBuilder.append("\nNome: " + getNomeGens());
        stringBuilder.append("\nCor da Calça: " + getCorCalca());
        stringBuilder.append("\nRG do filho: " + getFilhoGens().getRgFilho());
        stringBuilder.append("\nNome do filho: " + getFilhoGens().getNomeFilho());

        System.out.println(stringBuilder);
    }
}