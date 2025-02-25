package br.edu.utfpr.alexandrefeitosa.room2.persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room2.modelo.Tipo;

@Dao
public interface TipoDao {

    @Insert
    long insert(Tipo tipo);

    @Delete
    void delete(Tipo tipo);

    @Update
    void update(Tipo tipo);

    @Query("SELECT * FROM tipos WHERE id = :id")
    Tipo queryForId(long id);

    @Query("SELECT * FROM tipos ORDER BY descricao ASC")
    List<Tipo> queryAll();

    @Query("SELECT * FROM tipos WHERE descricao = :descricao ORDER BY descricao ASC")
    List<Tipo> queryForDescricao(String descricao);

    @Query("SELECT count(*) FROM tipos")
    int total();
}
