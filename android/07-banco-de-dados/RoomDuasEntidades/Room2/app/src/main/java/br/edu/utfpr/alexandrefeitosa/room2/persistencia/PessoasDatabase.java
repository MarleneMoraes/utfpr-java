package br.edu.utfpr.alexandrefeitosa.room2.persistencia;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

import br.edu.utfpr.alexandrefeitosa.room2.R;
import br.edu.utfpr.alexandrefeitosa.room2.modelo.Pessoa;
import br.edu.utfpr.alexandrefeitosa.room2.modelo.Tipo;

@Database(entities = {Pessoa.class, Tipo.class}, version = 1)
public abstract class PessoasDatabase extends RoomDatabase {

    public abstract PessoaDao pessoaDao();

    public abstract TipoDao tipoDao();

    private static PessoasDatabase instance;

    public static PessoasDatabase getDatabase(final Context context) {

        if (instance == null) {

            synchronized (PessoasDatabase.class) {
                if (instance == null) {
                   RoomDatabase.Builder builder =  Room.databaseBuilder(context,
                                                                        PessoasDatabase.class,
                                                                        "pessoas.db");

                   builder.addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    carregaTiposIniciais(context);
                                }
                            });
                        }
                   });

                   instance = (PessoasDatabase) builder.build();
                }
            }
        }

        return instance;
    }

    private static void carregaTiposIniciais(final Context context){

        String[] descricoes = context.getResources().getStringArray(R.array.tipos_iniciais);

        for (String descricao : descricoes) {

            Tipo tipo = new Tipo(descricao);

            instance.tipoDao().insert(tipo);
        }
    }
}
