package br.edu.utfpr.alexandrefeitosa.room3.persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room3.modelo.TipoContato;

@Dao
public interface TipoContatoDao {

    @Insert
    long insert(TipoContato tipoContato);

    @Delete
    void delete(TipoContato tipoContato);

    @Update
    void update(TipoContato tipoContato);

    @Query("SELECT * FROM tipos_contato WHERE id = :id")
    TipoContato queryForId(long id);

    @Query("SELECT * FROM tipos_contato WHERE descricao = :descricao ORDER BY descricao ASC")
    List<TipoContato> queryForDescricao(String descricao);

    @Query("SELECT * FROM tipos_contato ORDER BY descricao ASC")
    List<TipoContato> queryAll();

    @Query("SELECT count(*) FROM tipos_contato")
    int total();
}
