package br.edu.utfpr.alexandrefeitosa.room3.persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room3.modelo.Contato;

@Dao
public interface ContatoDao {

    @Insert
    long insert(Contato contato);

    @Delete
    void delete(Contato contato);

    @Update
    void update(Contato contato);

    @Query("SELECT * FROM contatos WHERE id = :id")
    Contato queryForId(long id);

    @Query("SELECT * FROM contatos WHERE pessoaId = :pessoaId")
    List<Contato> queryForPessoaId(long pessoaId);

    @Query("SELECT * FROM contatos WHERE tipoContatoId = :id ORDER BY valor ASC")
    List<Contato> queryForTipoContatoId(long id);
}
