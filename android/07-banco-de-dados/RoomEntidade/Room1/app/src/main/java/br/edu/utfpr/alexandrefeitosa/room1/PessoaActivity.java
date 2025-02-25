package br.edu.utfpr.alexandrefeitosa.room1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.edu.utfpr.alexandrefeitosa.room1.modelo.Pessoa;
import br.edu.utfpr.alexandrefeitosa.room1.persistencia.PessoasDatabase;
import br.edu.utfpr.alexandrefeitosa.room1.utils.UtilsGUI;

public class PessoaActivity extends AppCompatActivity {

    public static final String MODO    = "MODO";
    public static final String ID      = "ID";
    public static final int    NOVO    = 1;
    public static final int    ALTERAR = 2;

    private EditText editTexNome;
    private EditText editTextIdade;

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

        editTexNome   = findViewById(R.id.editTextNome);
        editTextIdade = findViewById(R.id.editTextIdade);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        modo = bundle.getInt(MODO, NOVO);

        if (modo == ALTERAR){

            setTitle(R.string.alterar_pessoa);

            int id = bundle.getInt(ID);

            PessoasDatabase database = PessoasDatabase.getDatabase(this);

            pessoa = database.pessoaDao().queryForId(id);

            editTexNome.setText(pessoa.getNome());
            editTextIdade.setText(String.valueOf(pessoa.getIdade()));

        }else{

            setTitle(R.string.nova_pessoa);

            pessoa = new Pessoa("");
        }
    }

    private void salvar(){

        String nome  = UtilsGUI.validaCampoTexto(this,
                                                 editTexNome,
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

        PessoasDatabase database = PessoasDatabase.getDatabase(this);

        if (modo == NOVO) {

            database.pessoaDao().insert(pessoa);

        } else {

            database.pessoaDao().update(pessoa);
        }

        setResult(Activity.RESULT_OK);
        finish();
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