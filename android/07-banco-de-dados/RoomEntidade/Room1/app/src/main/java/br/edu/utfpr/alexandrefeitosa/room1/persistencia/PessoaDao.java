package br.edu.utfpr.alexandrefeitosa.room1.persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room1.modelo.Pessoa;

@Dao
public interface PessoaDao {

    @Insert
    long insert(Pessoa pessoa);

    @Delete
    void delete(Pessoa pessoa);

    @Update
    void update(Pessoa pessoa);

    @Query("SELECT * FROM pessoa WHERE id = :id")
    Pessoa queryForId(long id);

    @Query("SELECT * FROM pessoa ORDER BY nome ASC")
    List<Pessoa> queryAll();
}