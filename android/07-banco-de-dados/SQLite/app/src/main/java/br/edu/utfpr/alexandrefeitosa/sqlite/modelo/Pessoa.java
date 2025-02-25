package br.edu.utfpr.alexandrefeitosa.sqlite.modelo;

import java.util.Comparator;

public class Pessoa {

    public static Comparator<Pessoa> comparador = new Comparator<Pessoa>() {

        @Override
        public int compare(Pessoa p1, Pessoa p2) {

            int compAlfabetica = p1.getNome().compareToIgnoreCase(p2.getNome());

            if (compAlfabetica == 0){

                if (p1.getIdade() < p2.getIdade()){
                    return -1;
                }else
                    if (p1.getIdade() > p2.getIdade()){
                        return 1;
                    }else{
                        return 0;
                    }
            }else{
                return compAlfabetica;
            }
        }
    };

    private long   id;
    private String nome;
    private int    idade;

    public Pessoa(String nome){
        setNome(nome);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString(){
        return getNome() + " - " + getIdade();
    }
}
