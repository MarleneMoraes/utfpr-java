package br.edu.utfpr.javai.aula06.heranca.abstracao;

public class TstPes {
    public static void main(String[] args) {
        // Prof p = new Prof();
        // p.impLocal();

        Pessoa pes1 = new Pessoa();
        System.out.println("\n==== Impressao de pes1 ====\n");
        pes1.impLocal();

        Pessoa pes2 = new Pessoa();
        System.out.println("\n==== Impressao de pes2 ====\n");
        pes2.impLocal();
    }
}