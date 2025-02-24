package br.edu.utfpr.alexandrefeitosa.sharedpreferences3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class PessoaActivity extends AppCompatActivity {

    public static final String MODO = "MODO";
    public static final String NOME = "NOME";

    public static final String TIPO = "TIPO";

    public static final int NOVO = 1;

    public static final int EDITAR = 2;

    private EditText editTextNome;

    private Spinner spinnerTipo;

    private int modo;

    private String nomeOriginal;

    private int tipoOriginal;

    public static final String SUGERIR_TIPO = "SUGERIR_TPO";

    public static final String ULTIMO_TIPO = "ULTIMO_TIPO";

    private boolean sugerirTipo = false;

    private int ultimoTipo = 0;

    public static void novaPessoa(AppCompatActivity activity, ActivityResultLauncher<Intent> launcher){

        Intent intent = new Intent(activity, PessoaActivity.class);

        intent.putExtra(MODO, NOVO);

        launcher.launch(intent);
    }

    public static void editarPessoa(AppCompatActivity activity, ActivityResultLauncher<Intent> launcher, Pessoa pessoa){

        Intent intent = new Intent(activity, PessoaActivity.class);

        intent.putExtra(MODO, EDITAR);
        intent.putExtra(NOME, pessoa.getNome());
        intent.putExtra(TIPO, pessoa.getTipo());

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
        spinnerTipo  = findViewById(R.id.spinnerTipo);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        lerSugerirTipo();
        lerUltimoTipo();

        if (bundle != null) {

            modo = bundle.getInt(MODO, NOVO);

            if (modo == NOVO) {
                setTitle(getString(R.string.nova_pessoa));

                if (sugerirTipo){
                    spinnerTipo.setSelection(ultimoTipo);
                }

            } else if (modo == EDITAR) {
                setTitle(getString(R.string.editar_pessoa));

                nomeOriginal = bundle.getString(NOME);

                editTextNome.setText(nomeOriginal);
                editTextNome.setSelection(editTextNome.getText().length());

                tipoOriginal = bundle.getInt(TIPO);
                spinnerTipo.setSelection(tipoOriginal);
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

        int tipo = spinnerTipo.getSelectedItemPosition();
        if (tipo < 0){
            Toast.makeText(this,
                           R.string.tipo_n_o_pode_ser_vazio,
                           Toast.LENGTH_LONG).show();
            return;
        }

        if (modo == EDITAR &&
            nome.equals(nomeOriginal) &&
            tipoOriginal == tipo){
            cancelar();
            return;
        }

        salvarUltimoTipo(tipo);

        Intent intent = new Intent();

        intent.putExtra(NOME, nome);
        intent.putExtra(TIPO, tipo);

        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    public void limpar(){
        editTextNome.setText(null);

        spinnerTipo.setSelection(0);

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
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem item = menu.findItem(R.id.menuItemSugerirTipo);

        item.setChecked(sugerirTipo);

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
                if (idMenuItem == R.id.menuItemSugerirTipo){

                    boolean valor = !item.isChecked();

                    salvarSugerirTipo(valor);
                    item.setChecked(valor);

                    if (sugerirTipo){
                        spinnerTipo.setSelection(ultimoTipo);
                    }

                    return true;
                }else
                    if (idMenuItem == android.R.id.home){
                        cancelar();
                        return true;
                    }else{
                        return super.onOptionsItemSelected(item);
                    }
    }

    private void lerSugerirTipo(){

        SharedPreferences shared = getSharedPreferences(PrincipalActivity.ARQUIVO, Context.MODE_PRIVATE);

        sugerirTipo = shared.getBoolean(SUGERIR_TIPO, sugerirTipo);
    }

    private void salvarSugerirTipo(boolean novoValor){

        SharedPreferences shared = getSharedPreferences(PrincipalActivity.ARQUIVO, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();

        editor.putBoolean(SUGERIR_TIPO, novoValor);

        editor.commit();

        sugerirTipo = novoValor;
    }

    private void lerUltimoTipo(){

        SharedPreferences shared = getSharedPreferences(PrincipalActivity.ARQUIVO, Context.MODE_PRIVATE);

        ultimoTipo = shared.getInt(ULTIMO_TIPO, ultimoTipo);
    }

    private void salvarUltimoTipo(int novoValor){

        SharedPreferences shared = getSharedPreferences(PrincipalActivity.ARQUIVO, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();

        editor.putInt(ULTIMO_TIPO, novoValor);

        editor.commit();

        ultimoTipo = novoValor;
    }
}