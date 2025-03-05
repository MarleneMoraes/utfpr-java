package br.edu.utfpr.minhas_figurinhas.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.edu.utfpr.minhas_figurinhas.model.Album;

@Dao
public interface AlbumDao {
    @Insert
    long insert(Album album);

    @Delete
    int delete(Album album);

    @Update
    int update(Album album);

    @Query("SELECT * FROM album WHERE id=:id")
    Album queryForId(long id);
    @Query("SELECT * FROM album ORDER BY title ASC")
    List<Album> queryAllAscending();

    @Query("SELECT * FROM album ORDER BY title DESC")
    List<Album> queryAllDownward();
}
