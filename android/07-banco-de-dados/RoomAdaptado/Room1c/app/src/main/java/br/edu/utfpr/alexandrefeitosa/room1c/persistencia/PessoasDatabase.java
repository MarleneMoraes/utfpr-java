package br.edu.utfpr.alexandrefeitosa.room1c.persistencia;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.edu.utfpr.alexandrefeitosa.room1c.modelo.Pessoa;

@Database(entities = {Pessoa.class}, version = 1, exportSchema = false)
public abstract class PessoasDatabase extends RoomDatabase {

    public abstract PessoaDao getPessoaDao();

    private static PessoasDatabase instance;

    public static PessoasDatabase getDatabase(final Context context){

        if (instance == null){

            synchronized (PessoasDatabase.class){

                if (instance == null){

                    instance = Room.databaseBuilder(context,
                                                    PessoasDatabase.class,
                                                    "pessoas.db").allowMainThreadQueries().build();
                }
            }
        }

        return instance;
    }
}
