package br.edu.utfpr.alexandrefeitosa.room2.modelo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "pessoas",
        foreignKeys = @ForeignKey(entity = Tipo.class,
                                  parentColumns = "id",
                                  childColumns  = "tipoId"))
public class Pessoa {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String nome;

    private int idade;

    @ColumnInfo(index = true)
    private int tipoId;

    /* Para utilizar o Room, caso exista construtor com parâmetros as variáveis dos parâmetros
       devem ter o mesmo nome dos atributos persistidos da classe.

       Caso um atributo persistido não sejm público, deve existir os métodos get e set
       apropriadas para este atributo, e o nome dos métodos devem seguir as Convenções de código
       do Java

       É recomendável criar indices para os campos que armazenam chaves extrangeiras, caso não
       faça é gerado um warning*/

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

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    @Override
    public String toString(){
        return getNome();
    }
}
