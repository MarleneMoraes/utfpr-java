package br.edu.utfpr.minhas_figurinhas.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.edu.utfpr.minhas_figurinhas.model.Album;

@Database(entities = {Album.class}, version = 1, exportSchema = false)
public abstract class AlbumDatabase extends RoomDatabase {
    public abstract AlbumDao getAlbumDao();

    private static AlbumDatabase INSTANCE;

    public static AlbumDatabase getInstance(final Context context) {
        if(INSTANCE == null) {
            synchronized (AlbumDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AlbumDatabase.class, "album.db")
                                                        .allowMainThreadQueries().build();
                }
            }
        }

        return INSTANCE;
    }
}
