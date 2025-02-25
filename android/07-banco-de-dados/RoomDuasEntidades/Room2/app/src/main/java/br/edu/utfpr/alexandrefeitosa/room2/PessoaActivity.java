package br.edu.utfpr.alexandrefeitosa.room2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room2.modelo.Pessoa;
import br.edu.utfpr.alexandrefeitosa.room2.modelo.Tipo;
import br.edu.utfpr.alexandrefeitosa.room2.persistencia.PessoasDatabase;
import br.edu.utfpr.alexandrefeitosa.room2.utils.UtilsGUI;

public class PessoaActivity extends AppCompatActivity {

    public static final String MODO    = "MODO";
    public static final String ID      = "ID";
    public static final int    NOVO    = 1;
    public static final int    ALTERAR = 2;

    private EditText editTextNome;
    private EditText editTextIdade;

    private Spinner    spinnerTipo;
    private List<Tipo> listaTipos;

    private int    modo;
    private Pessoa pessoa;

    public static void nova(Activity activity, int requestCode){

        Intent intent = new Intent(activity, PessoaActivity.class);

        intent.putExtra(MODO, NOVO);

        activity.startActivityForResult(intent, requestCode);
    }

    public static void alterar(Activity activity, int requestCode, Pessoa pessoa){

        Intent intent = new Intent(activity, PessoaActivity.class);

        intent.putExtra(MODO, ALTERAR);
        intent.putExtra(ID, pessoa.getId());

        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editTextNome  = findViewById(R.id.editTextNome);
        editTextIdade = findViewById(R.id.editTextIdade);
        spinnerTipo   = findViewById(R.id.spinnerTipo);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        modo = bundle.getInt(MODO, NOVO);

        carregaTipos();

        if (modo == ALTERAR){

            setTitle(R.string.alterar_pessoa);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    int id = bundle.getInt(ID);

                    PessoasDatabase database = PessoasDatabase.getDatabase(PessoaActivity.this);

                    pessoa = database.pessoaDao().queryForId(id);

                    PessoaActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            editTextNome.setText(pessoa.getNome());
                            editTextIdade.setText(String.valueOf(pessoa.getIdade()));

                            int posicao = posicaoTipo(pessoa.getTipoId());
                            spinnerTipo.setSelection(posicao);
                        }
                    });
                }
            });

        }else{

            setTitle(R.string.nova_pessoa);

            pessoa = new Pessoa("");
        }
    }

    private int posicaoTipo(int tipoId){

        for (int pos = 0; pos < listaTipos.size(); pos++){

            Tipo t = listaTipos.get(pos);

            if (t.getId() == tipoId){
                return pos;
            }
        }

        return -1;
    }

    private void carregaTipos(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                PessoasDatabase database = PessoasDatabase.getDatabase(PessoaActivity.this);

                listaTipos = database.tipoDao().queryAll();

                PessoaActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<Tipo> spinnerAdapter =
                                new ArrayAdapter<>(PessoaActivity.this,
                                                   android.R.layout.simple_list_item_1,
                                                   listaTipos);

                        spinnerTipo.setAdapter(spinnerAdapter);
                    }
                });
            }
        });
    }

    private void salvar(){

        String nome  = UtilsGUI.validaCampoTexto(this,
                                                 editTextNome,
                                                 R.string.nome_vazio);
        if (nome == null){
            return;
        }

        String txtIdade = UtilsGUI.validaCampoTexto(this,
                                                    editTextIdade,
                                                    R.string.idade_vazia);
        if (txtIdade == null){
            return;
        }

        int idade = Integer.parseInt(txtIdade);

        if (idade <= 0 || idade > 200){
            UtilsGUI.avisoErro(this, R.string.idade_invalida);
            editTextIdade.requestFocus();
            return;
        }

        pessoa.setNome(nome);
        pessoa.setIdade(idade);

        Tipo tipo = (Tipo) spinnerTipo.getSelectedItem();
        if (tipo != null){
            pessoa.setTipoId(tipo.getId());
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                PessoasDatabase database = PessoasDatabase.getDatabase(PessoaActivity.this);

                if (modo == NOVO) {

                    database.pessoaDao().insert(pessoa);

                } else {

                    database.pessoaDao().update(pessoa);
                }

                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    private void cancelar(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edicao_detalhes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuItemSalvar:
                salvar();
                return true;
            case R.id.menuItemCancelar:
                cancelar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
