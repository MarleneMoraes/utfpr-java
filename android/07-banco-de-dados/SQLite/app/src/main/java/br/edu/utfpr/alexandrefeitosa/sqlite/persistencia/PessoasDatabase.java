package br.edu.utfpr.alexandrefeitosa.sqlite.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PessoasDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME    = "pessoas.db";
    private static final int    DB_VERSION = 1;

    private static PessoasDatabase instance;

    private Context context;
    public  PessoaDAO pessoaDAO;

    public static PessoasDatabase getInstance(Context contexto){

        if (instance == null){
            instance = new PessoasDatabase(contexto);
        }

        return instance;
    }

    private PessoasDatabase(Context contexto){
        super(contexto, DB_NAME, null, DB_VERSION);

        context = contexto;

        pessoaDAO = new PessoaDAO(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        pessoaDAO.criarTabela(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        pessoaDAO.apagarTabela(db);

        onCreate(db);
    }
}