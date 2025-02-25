package br.edu.utfpr.alexandrefeitosa.room3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room3.modelo.TipoContato;
import br.edu.utfpr.alexandrefeitosa.room3.persistencia.PessoasDatabase;
import br.edu.utfpr.alexandrefeitosa.room3.utils.UtilsDate;
import br.edu.utfpr.alexandrefeitosa.room3.utils.UtilsGUI;

public class TipoContatoActivity extends AppCompatActivity {

    public static final String MODO    = "MODO";
    public static final String ID      = "ID";
    public static final int    NOVO    = 1;
    public static final int    ALTERAR = 2;

    private EditText editTexDescricao;
    private TextView textViewDataCadastro;
    private EditText editTextDataCadastro;

    private int         modo;
    private TipoContato tipoContato;

    public static void novo(Activity activity, int requestCode) {

        Intent intent = new Intent(activity, TipoContatoActivity.class);

        intent.putExtra(MODO, NOVO);

        activity.startActivityForResult(intent, requestCode);
    }

    public static void alterar(Activity activity, int requestCode, TipoContato tipoContato){

        Intent intent = new Intent(activity, TipoContatoActivity.class);

        intent.putExtra(MODO, ALTERAR);
        intent.putExtra(ID, tipoContato.getId());

        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editTexDescricao     = findViewById(R.id.editTextDescricao);
        textViewDataCadastro = findViewById(R.id.textViewDataCadastro);
        editTextDataCadastro = findViewById(R.id.editTextDataCadastro);

        editTextDataCadastro.setEnabled(false);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        modo = bundle.getInt(MODO, NOVO);

        if (modo == ALTERAR){

            setTitle(R.string.alterar_tipo_contato);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {

                    int id = bundle.getInt(ID);

                    PessoasDatabase database = PessoasDatabase.getDatabase(TipoContatoActivity.this);

                    tipoContato = database.tipoContatoDao().queryForId(id);

                    TipoContatoActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            editTexDescricao.setText(tipoContato.getDescricao());

                            String texto = UtilsDate.formatDate(TipoContatoActivity.this,
                                                                tipoContato.getDataCadastro());

                            editTextDataCadastro.setText(texto);
                        }
                    });
                }
            });

        }else{

            setTitle(R.string.novo_tipo_contato);

            tipoContato = new TipoContato("");

            textViewDataCadastro.setVisibility(View.INVISIBLE);
            editTextDataCadastro.setVisibility(View.INVISIBLE);
        }
    }

    private void salvar(){

        final String descricao  = UtilsGUI.validaCampoTexto(this,
                                                            editTexDescricao,
                                                            R.string.descricao_vazia);
        if (descricao == null){
            return;
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                PessoasDatabase database = PessoasDatabase.getDatabase(TipoContatoActivity.this);

                List<TipoContato> lista = database.tipoContatoDao().queryForDescricao(descricao);

                if (modo == NOVO) {

                    if (lista.size() > 0){

                        TipoContatoActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                UtilsGUI.avisoErro(TipoContatoActivity.this, R.string.descricao_usada);
                            }
                        });

                        return;
                    }

                    tipoContato.setDescricao(descricao);
                    tipoContato.setDataCadastro(new Date());

                    database.tipoContatoDao().insert(tipoContato);

                } else {

                    if (!descricao.equals(tipoContato.getDescricao())){

                        if (lista.size() >= 1){

                            TipoContatoActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UtilsGUI.avisoErro(TipoContatoActivity.this, R.string.descricao_usada);
                                }
                            });

                            return;
                        }

                        tipoContato.setDescricao(descricao);

                        database.tipoContatoDao().update(tipoContato);
                    }
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
