package br.edu.utfpr.alexandrefeitosa.room1c;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import br.edu.utfpr.alexandrefeitosa.room1c.modelo.MaoUsada;
import br.edu.utfpr.alexandrefeitosa.room1c.modelo.Pessoa;
import br.edu.utfpr.alexandrefeitosa.room1c.persistencia.PessoasDatabase;
import br.edu.utfpr.alexandrefeitosa.room1c.utils.UtilsGUI;

public class PessoaActivity extends AppCompatActivity {

    public static final String MODO = "MODO";

    public static final String ID = "ID";

    public static final int NOVO = 1;

    public static final int EDITAR = 2;

    private EditText editTextNome;

    private Spinner spinnerTipo;

    private CheckBox checkBoxBolsista;

    private RadioGroup radioGroupMaoUsada;

    private int modo;

    private Pessoa pessoaOriginal;

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
        intent.putExtra(ID, pessoa.getId());

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

        editTextNome       = findViewById(R.id.editTextTextNome);
        spinnerTipo        = findViewById(R.id.spinnerTipo);
        checkBoxBolsista   = findViewById(R.id.checkBoxBolsista);
        radioGroupMaoUsada = findViewById(R.id.radioGroupMaoUsada);

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

                long id = bundle.getLong(ID);

                PessoasDatabase database = PessoasDatabase.getDatabase(this);

                pessoaOriginal = database.getPessoaDao().queryForId(id);

                editTextNome.setText(pessoaOriginal.getNome());
                editTextNome.setSelection(editTextNome.getText().length());

                spinnerTipo.setSelection(pessoaOriginal.getTipo());

                checkBoxBolsista.setChecked(pessoaOriginal.isBolsista());

                MaoUsada maoUsadaOriginal = pessoaOriginal.getMaoUsada();

                RadioButton button = null;

                if (maoUsadaOriginal == MaoUsada.Direita){
                    button = findViewById(R.id.radioButtonDireita);
                }else
                    if (maoUsadaOriginal == MaoUsada.Esquerda){
                        button = findViewById(R.id.radioButtonEsquerda);
                    }else
                        if (maoUsadaOriginal == MaoUsada.Ambas){
                            button = findViewById(R.id.radioButtonAmbas);
                        }

                if (button != null){
                    button.setChecked(true);
                }
            }
        }
    }

    public void salvar(){

        String nome = editTextNome.getText().toString();

        if (nome == null || nome.trim().isEmpty()){
            UtilsGUI.aviso(this, R.string.nome_n_o_pode_ser_vazio);
            editTextNome.requestFocus();
            return;
        }

        int tipo = spinnerTipo.getSelectedItemPosition();
        if (tipo < 0){
            UtilsGUI.aviso(this, R.string.tipo_n_o_pode_ser_vazio);
            return;
        }

        boolean bolsista = checkBoxBolsista.isChecked();

        int radioButtonId = radioGroupMaoUsada.getCheckedRadioButtonId();

        MaoUsada maoUsada;

        if (radioButtonId == R.id.radioButtonDireita){
            maoUsada = MaoUsada.Direita;
        }else
            if (radioButtonId == R.id.radioButtonEsquerda){
                maoUsada = MaoUsada.Esquerda;
            }else
                if (radioButtonId == R.id.radioButtonAmbas){
                    maoUsada = MaoUsada.Ambas;
                }else{
                    UtilsGUI.aviso(this, R.string.a_mao_usada_nao_pode_ser_vazia);
                    return;
                }

        if (modo == EDITAR &&
            nome.equals(pessoaOriginal.getNome())    &&
            tipo     == pessoaOriginal.getTipo()     &&
            maoUsada == pessoaOriginal.getMaoUsada() &&
            bolsista == pessoaOriginal.isBolsista()){
            cancelar();
            return;
        }

        salvarUltimoTipo(tipo);

        Intent intent = new Intent();

        PessoasDatabase database = PessoasDatabase.getDatabase(this);

        if (modo == NOVO){

            Pessoa pessoa = new Pessoa(nome, tipo, bolsista, maoUsada);

            long novoId = database.getPessoaDao().insert(pessoa);

            if (novoId <= 0){
                UtilsGUI.aviso(this, R.string.erro_ao_tentar_inserir);
                return;
            }

            pessoa.setId(novoId);

            intent.putExtra(ID, pessoa.getId());

        }else{

            Pessoa pessoaAlterada = new Pessoa(nome, tipo, bolsista, maoUsada);

            pessoaAlterada.setId(pessoaOriginal.getId());

            int quantidadeAlterada = database.getPessoaDao().update(pessoaAlterada);

            if (quantidadeAlterada == 0){
                UtilsGUI.aviso(this, R.string.erro_ao_tentar_alterar);
                return;
            }

            intent.putExtra(ID, pessoaAlterada.getId());
        }

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void limpar(){
        editTextNome.setText(null);

        spinnerTipo.setSelection(0);

        checkBoxBolsista.setChecked(false);

        radioGroupMaoUsada.clearCheck();

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