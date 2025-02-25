package br.edu.utfpr.alexandrefeitosa.room3.persistencia;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.concurrent.Executors;

import br.edu.utfpr.alexandrefeitosa.room3.R;
import br.edu.utfpr.alexandrefeitosa.room3.modelo.Contato;
import br.edu.utfpr.alexandrefeitosa.room3.modelo.Pessoa;
import br.edu.utfpr.alexandrefeitosa.room3.modelo.Tipo;
import br.edu.utfpr.alexandrefeitosa.room3.modelo.TipoContato;

@Database(entities = {Tipo.class, TipoContato.class, Pessoa.class, Contato.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class PessoasDatabase extends RoomDatabase {

    public abstract TipoDao tipoDao();

    public abstract TipoContatoDao tipoContatoDao();

    public abstract PessoaDao pessoaDao();

    public abstract ContatoDao contatoDao();

    private static PessoasDatabase instance;

    public static PessoasDatabase getDatabase(final Context context) {

        if (instance == null) {

            synchronized (PessoasDatabase.class) {
                if (instance == null) {
                   Builder builder =  Room.databaseBuilder(context,
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
                                    carregaTiposContatosIniciais(context);
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

            tipo.setDataCadastro(new Date());

            instance.tipoDao().insert(tipo);
        }
    }

    private static void carregaTiposContatosIniciais(final Context context){

        String[] descricoes = context.getResources().getStringArray(R.array.tipos_contatos_iniciais);

        for (String descricao : descricoes) {

            TipoContato tipoContato = new TipoContato(descricao);

            tipoContato.setDataCadastro(new Date());

            instance.tipoContatoDao().insert(tipoContato);
        }
    }
}
