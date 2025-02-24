package br.edu.utfpr.alexandrefeitosa.botaoup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PessoaActivity extends AppCompatActivity {

    public static final String MODO = "MODO";
    public static final String NOME = "NOME";

    public static final int NOVO = 1;

    public static final int EDITAR = 2;

    private EditText editTextNome;

    private int modo;

    private String nomeOriginal;

    public static void novaPessoa(AppCompatActivity activity, ActivityResultLauncher<Intent> launcher){

        Intent intent = new Intent(activity, PessoaActivity.class);

        intent.putExtra(MODO, NOVO);

        launcher.launch(intent);
    }

    public static void editarPessoa(AppCompatActivity activity, ActivityResultLauncher<Intent> launcher, Pessoa pessoa){

        Intent intent = new Intent(activity, PessoaActivity.class);

        intent.putExtra(MODO, EDITAR);
        intent.putExtra(NOME, pessoa.getNome());

        launcher.launch(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editTextNome = findViewById(R.id.editTextTextNome);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {

            modo = bundle.getInt(MODO, NOVO);

            if (modo == NOVO) {
                setTitle(getString(R.string.nova_pessoa));
            } else if (modo == EDITAR) {
                setTitle(getString(R.string.editar_pessoa));

                nomeOriginal = bundle.getString(NOME);

                editTextNome.setText(nomeOriginal);
                editTextNome.setSelection(editTextNome.getText().length());
            }
        }
    }

    public void salvar(){

        String nome = editTextNome.getText().toString();

        if (nome == null || nome.trim().isEmpty()){
            Toast.makeText(this,
                           R.string.nome_n_o_pode_ser_vazio,
                           Toast.LENGTH_LONG).show();
            return;
        }

        if (modo == EDITAR && nome.equals(nomeOriginal)){
            cancelar();
            return;
        }

        Intent intent = new Intent();

        intent.putExtra(NOME, nome);

        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    public void limpar(){
        editTextNome.setText(null);

        Toast.makeText(this,
                       R.string.as_entradas_foram_apagadas,
                       Toast.LENGTH_SHORT).show();
    }

    public void cancelar(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pessoa_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idMenuItem = item.getItemId();

        if (idMenuItem == R.id.menuItemSalvar){
            salvar();
            return true;
        }else
            if (idMenuItem == R.id.menuItemLimpar){
                limpar();
                return true;
            }else
                if (idMenuItem == android.R.id.home){
                    cancelar();
                    return true;
                }else{
                    return super.onOptionsItemSelected(item);
                }
    }
}