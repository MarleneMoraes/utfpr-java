package br.edu.utfpr.alexandrefeitosa.room1.modelo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Pessoa {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String nome;

    private int idade;

    /* Para utilizar o Room, caso exista construtor com parâmetros as variáveis dos parâmetros
       devem ter o mesmo nome dos atributos persistidos da classe.

       Caso um atributo persistido não sejm público, deve existir os métodos get e set
       apropriadas para este atributo, e o nome dos métodos devem seguir as Convenções de código
       do Java */

    public Pessoa(String nome){
        setNome(nome);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
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
        return getNome();
    }
}