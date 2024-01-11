package br.edu.utfpr.javai.aula06.heranca.atividade3example;

public class TesteFam {
    public static void main(String[] args) {
        Pai pai = new Pai();
        pai.setRgGens(1);
        pai.setNomeGens("João");
        pai.setCorCalca("Preto");
        pai.getFilhoGens().setRgFilho(11);
        pai.getFilhoGens().setNomeFilho("João Jr");

        Mae mae = new Mae();
        mae.setRgGens(2);
        mae.setNomeGens("Maria");
        mae.setCorVestido("Branco");
        mae.getFilhoGens().setRgFilho(22);
        mae.getFilhoGens().setNomeFilho("Maria Filha");

        pai.imprimirDadosPai();
        mae.imprimirDadosMae();
    }

}
