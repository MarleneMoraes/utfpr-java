package br.edu.utfpr.alexandrefeitosa.room3.modelo;

import android.arch.persistence.room.Entity;

@Entity(tableName = "tipos_contato", inheritSuperIndices = true)
public class TipoContato extends Tipo {

    public TipoContato(String descricao) {
        super(descricao);
    }
}
