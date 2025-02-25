package br.edu.utfpr.alexandrefeitosa.room3.modelo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Comparator;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "contatos",
        foreignKeys = {@ForeignKey(entity = TipoContato.class,
                                   parentColumns = "id",
                                   childColumns = "tipoContatoId"),
                       @ForeignKey(entity = Pessoa.class,
                                   parentColumns = "id",
                                   childColumns = "pessoaId",
                                   onDelete = CASCADE)})
public class Contato {

    public static Comparator<Contato> comparador = new Comparator<Contato>() {
        @Override
        public int compare(Contato c1, Contato c2) {

            if (c1.tipoContato != null && c2.tipoContato != null){

                int ordemTipo = c1.getTipoContato().getDescricao()
                                .compareToIgnoreCase(c2.getTipoContato().getDescricao());

                if (ordemTipo == 0){
                    return c1.getValor().compareToIgnoreCase(c2.getValor());
                }else{
                    return ordemTipo;
                }
            }else{
                return c1.getValor().compareToIgnoreCase(c2.getValor());
            }
        }
    };

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String valor;

    @ColumnInfo(index = true)
    private int pessoaId;

    @ColumnInfo(index = true)
    private int tipoContatoId;

    @Ignore
    private TipoContato tipoContato;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    public int getTipoContatoId() {
        return tipoContatoId;
    }

    public void setTipoContatoId(int tipoContatoId) {
        this.tipoContatoId = tipoContatoId;
    }

    public TipoContato getTipoContato(){
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato){
        this.tipoContato = tipoContato;
    }

    @Override
    public String toString(){

        if (tipoContato != null){
            return tipoContato.getDescricao() + ": " + getValor();
        }else{
            return getValor();
        }
    }
}
