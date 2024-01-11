package br.edu.utfpr.javai.aula06.heranca.abordagempratica;

public class TstPes {
    public static void main(String[] args) {
        Prof p = new Prof(); // objeto do tipo Prof (p) = classe Prof + classe Pessoa
        
        // Heranca
        p.setCpf("12345678910");
        p.setNome("Jesus");
        
        // Proprio 
        p.setSalario(1000);
        p.setTitulo("Mestre");

        System.out.println("\nCPF          - PROF: " + p.getCpf());
        System.out.println("\nNOME         - PROF: " + p.getNome());
        System.out.println("\nSALARIO      - PROF: " + p.getSalario());
        System.out.println("\nTITULO       - PROF: " + p.getTitulo());

        System.out.println("\n=========================================\n");
        
        Aluno a = new Aluno();

        // Heranca
        a.setCpf("01987654321");
        a.setNome("Pedro");
        
        // Proprio 
        a.setRa(43);
        a.setCurso("Espec. Java");

        System.out.println("\nCPF          - ALUNO: " + a.getCpf());
        System.out.println("\nNOME         - ALUNO: " + a.getNome());
        System.out.println("\nRA           - ALUNO: " + a.getRa());
        System.out.println("\nCURSO        - ALUNO: " + a.getCurso());
    }
}