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

    }
}