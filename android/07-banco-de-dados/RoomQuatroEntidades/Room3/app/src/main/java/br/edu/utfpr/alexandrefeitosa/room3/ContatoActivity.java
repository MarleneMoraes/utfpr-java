package br.edu.utfpr.alexandrefeitosa.room3;

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

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room3.modelo.Contato;
import br.edu.utfpr.alexandrefeitosa.room3.modelo.Tipo;
import br.edu.utfpr.alexandrefeitosa.room3.modelo.TipoContato;
import br.edu.utfpr.alexandrefeitosa.room3.persistencia.PessoasDatabase;
import br.edu.utfpr.alexandrefeitosa.room3.utils.UtilsGUI;

public class ContatoActivity extends AppCompatActivity {

    public static final String ID_CONTATO            = "ID_CONTATO";
    public static final String VALOR                 = "VALOR";
    public static final String ID_TIPO_CONTATO       = "ID_TIPO_CONTATO";
    public static final String VALORES_USADOS        = "VALORES_USADOS";
    public static final String TIPOS_CONTATOS_USADOS = "TIPOS_CONTATOS_USADOS";

    public static final String MODO    = "MODO";
    public static final int    NOVO    = 1;
    public static final int    ALTERAR = 2;

    private EditText editTextValor;

    private List<TipoContato> listaTiposContato;
    private ArrayAdapter<TipoContato> spinnerAdapter;
    private Spinner spinnerTipoContato;

    private int modo;
    private int idContato;
    private int idTipoContato;

    private int[]    tiposUsados;
    private String[] valoresUsados;


    public static void novo(Activity activity, int requestCode, List<Contato> lista){

        Intent intent = new Intent(activity, ContatoActivity.class);

        intent.putExtra(MODO, NOVO);

        incorporaUsados(intent, lista);

        activity.startActivityForResult(intent, requestCode);
    }

    public static void alterar(Activity activity, int requestCode,
                               Contato contato, List<Contato> lista){

        Intent intent = new Intent(activity, ContatoActivity.class);

        intent.putExtra(MODO, ALTERAR);
        intent.putExtra(ID_CONTATO,      contato.getId());
        intent.putExtra(VALOR,           contato.getValor());
        intent.putExtra(ID_TIPO_CONTATO, contato.getTipoContatoId());

        ArrayList<Contato> cloneList = new ArrayList<>(lista);

        cloneList.remove(contato);

        incorporaUsados(intent, cloneList);

        activity.startActivityForResult(intent, requestCode);
    }

    private static Intent incorporaUsados(Intent intent, List<Contato> lista){

        if (lista != null && lista.size() > 0){

            int[]    tiposUsados   = new int[lista.size()];
            String[] valoresUsados = new String[lista.size()];

            for (int cont = 0; cont < lista.size(); cont++){

                Contato c = lista.get(cont);

                tiposUsados[cont]   = c.getTipoContatoId();
                valoresUsados[cont] = c.getValor();
            }

            intent.putExtra(VALORES_USADOS, valoresUsados);
            intent.putExtra(TIPOS_CONTATOS_USADOS, tiposUsados);
        }

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editTextValor      = findViewById(R.id.editTextValor);
        spinnerTipoContato = findViewById(R.id.spinnerTipoContato);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        modo = bundle.getInt(MODO, NOVO);

        tiposUsados   = bundle.getIntArray(TIPOS_CONTATOS_USADOS);
        valoresUsados = bundle.getStringArray(VALORES_USADOS);

        if (modo == ALTERAR){

            setTitle(R.string.alterar_contato);

            idContato     = bundle.getInt(ID_CONTATO);
            idTipoContato = bundle.getInt(ID_TIPO_CONTATO);

            editTextValor.setText(bundle.getString(VALOR));

        }else{

            setTitle(R.string.novo_contato);

            idContato = -1;
        }

        carregaTiposContato();
    }

    private int posicaoTipoContatoId(int tipoContatoId){

        for (int pos = 0; pos < listaTiposContato.size(); pos++){

            Tipo t = listaTiposContato.get(pos);

            if (t.getId() == tipoContatoId){
                return pos;
            }
        }

        return -1;
    }

    private void carregaTiposContato(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                PessoasDatabase database = PessoasDatabase.getDatabase(ContatoActivity.this);

                listaTiposContato = database.tipoContatoDao().queryAll();

                ContatoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        spinnerAdapter = new ArrayAdapter<>(ContatoActivity.this,
                                android.R.layout.simple_list_item_1,
                                listaTiposContato);

                        spinnerTipoContato.setAdapter(spinnerAdapter);
                        spinnerTipoContato.setSelection(posicaoTipoContatoId(idTipoContato));
                    }
                });
            }
        });
    }

    private void salvar(){

        String valor  = UtilsGUI.validaCampoTexto(this,
                                                  editTextValor,
                                                  R.string.descricao_vazia);
        if (valor == null){
            return;
        }

        TipoContato tipoContato = (TipoContato) spinnerTipoContato.getSelectedItem();
        if (tipoContato == null){
            UtilsGUI.avisoErro(this, R.string.tipo_contato_vazio);
            return;
        }

        if (tiposUsados != null){

            for (int cont = 0; cont < tiposUsados.length; cont++){

                if (tipoContato.getId() == tiposUsados[cont] &&
                    valor.equalsIgnoreCase(valoresUsados[cont])){
                    UtilsGUI.avisoErro(this, R.string.contato_valor_repetido);
                    return;
                }
            }
        }

        Intent intent = new Intent();

        intent.putExtra(MODO,  modo);
        intent.putExtra(ID_CONTATO, idContato);
        intent.putExtra(VALOR, valor);
        intent.putExtra(ID_TIPO_CONTATO, tipoContato.getId());

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void cancelar(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edicao_detalhes, menu);

        menu.getItem(0).setIcon(R.drawable.ic_check_mark);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuItemSalvar:
                salvar();
                return true;
            case R.id.menuItemCancelar:
            case android.R.id.home:
                cancelar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
