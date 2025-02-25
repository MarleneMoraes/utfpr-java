package br.edu.utfpr.alexandrefeitosa.sqlite.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.utfpr.alexandrefeitosa.sqlite.modelo.Pessoa;

public class PessoaDAO {

    public static final String TABELA = "PESSOAS";
    public static final String NOME   = "NOME";
    public static final String ID     = "ID";
    public static final String IDADE  = "IDADE";

    private PessoasDatabase conexao;
    public  List<Pessoa>    lista;

    public PessoaDAO(PessoasDatabase pessoasDatabase){
        conexao = pessoasDatabase;
        lista   = new ArrayList<>();
    }

    public void criarTabela(SQLiteDatabase database){

        String sql = "CREATE TABLE " + TABELA + "(" +
                     ID    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                     NOME  + " TEXT NOT NULL, " +
                     IDADE + " INTEGER)";

        database.execSQL(sql);
    }

    public void apagarTabela(SQLiteDatabase database){

        String sql = "DROP TABLE IF EXISTS " + TABELA;

        database.execSQL(sql);
    }

    public boolean inserir(Pessoa pessoa){

        ContentValues values = new ContentValues();

        values.put(NOME, pessoa.getNome());
        values.put(IDADE, pessoa.getIdade());

        long id = conexao.getWritableDatabase().insert(TABELA,
                                                       null,
                                                       values);

        pessoa.setId(id);

        lista.add(pessoa);

        ordenarLista();

        return true;
    }

    public boolean alterar(Pessoa pessoa){

        ContentValues values = new ContentValues();

        values.put(NOME,  pessoa.getNome());
        values.put(IDADE, pessoa.getIdade());

        String[] args = {String.valueOf(pessoa.getId())};

        conexao.getWritableDatabase().update(TABELA,
                                             values,
                                             ID + " = ?",
                                             args);

        ordenarLista();

        return true;
    }

    public boolean apagar(Pessoa pessoa){

        String[] args = {String.valueOf(pessoa.getId())};

        conexao.getWritableDatabase().delete(TABELA,
                                             ID + " = ?",
                                             args);
        lista.remove(pessoa);

        return true;
    }

    public void carregarTudo(){

        lista.clear();

        String sql = "SELECT * FROM " + TABELA + " ORDER BY " + NOME;

        Cursor cursor = conexao.getReadableDatabase().rawQuery(sql, null);

        int colunaNome  = cursor.getColumnIndex(NOME);
        int colunaId    = cursor.getColumnIndex(ID);
        int colunaIdade = cursor.getColumnIndex(IDADE);

        while(cursor.moveToNext()){

            Pessoa pessoa = new Pessoa(cursor.getString(colunaNome));

            pessoa.setId(cursor.getLong(colunaId));
            pessoa.setIdade(cursor.getInt(colunaIdade));

            lista.add(pessoa);
        }

        cursor.close();

        ordenarLista();
    }

    public Pessoa pessoaPorId(long id){

        for (Pessoa p: lista){

            if (p.getId() == id){
                return p;
            }
        }

        return null;
    }

    public void ordenarLista(){
        Collections.sort(lista, Pessoa.comparador);
    }
}