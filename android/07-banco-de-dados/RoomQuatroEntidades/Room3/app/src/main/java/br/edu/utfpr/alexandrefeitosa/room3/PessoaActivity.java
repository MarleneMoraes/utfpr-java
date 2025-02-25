package br.edu.utfpr.alexandrefeitosa.room3;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room3.modelo.Contato;
import br.edu.utfpr.alexandrefeitosa.room3.modelo.Pessoa;
import br.edu.utfpr.alexandrefeitosa.room3.modelo.Tipo;
import br.edu.utfpr.alexandrefeitosa.room3.modelo.TipoContato;
import br.edu.utfpr.alexandrefeitosa.room3.persistencia.PessoasDatabase;
import br.edu.utfpr.alexandrefeitosa.room3.utils.UtilsDate;
import br.edu.utfpr.alexandrefeitosa.room3.utils.UtilsGUI;

public class PessoaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final int REQUEST_NOVO_CONTATO    = 1;
    private static final int REQUEST_ALTERAR_CONTATO = 2;

    public static final String MODO    = "MODO";
    public static final String ID      = "ID";
    public static final int    NOVO    = 1;
    public static final int    ALTERAR = 2;

    private EditText   editTextNome;
    private EditText   editTextDataNascimento;
    private Spinner    spinnerTipo;
    private List<Tipo> listaTipos;

    private ListView              listViewContatos;
    private List<Contato>         listaContatos;
    private List<Contato>         listaContatosRemovidos;
    private ArrayAdapter<Contato> listaAdapter;

    private TextView textViewDataCadastro;
    private EditText editTextDataCadastro;

    private int      modo;
    private Pessoa   pessoa;
    private Calendar calendarDataNascimento;

    private int heightMinListViewContatos;

    private List<TipoContato> listaTiposContato;

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

        editTextNome           = findViewById(R.id.editTextNome);
        editTextDataNascimento = findViewById(R.id.editTextDataNascimento);
        spinnerTipo            = findViewById(R.id.spinnerTipo);
        listViewContatos       = findViewById(R.id.listViewContatos);
        textViewDataCadastro   = findViewById(R.id.textViewDataCadastro);
        editTextDataCadastro   = findViewById(R.id.editTextDataCadastro);

        editTextDataCadastro.setEnabled(false);

        listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contato contato = (Contato) parent.getItemAtPosition(position);

                ContatoActivity.alterar(PessoaActivity.this,
                                        REQUEST_ALTERAR_CONTATO,
                                        contato,
                                        listaContatos);
            }
        });

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        heightMinListViewContatos = getResources().getInteger(R.integer.altura_min_lista_contatos);

        modo = bundle.getInt(MODO, NOVO);

        listaContatosRemovidos = new ArrayList<>();
        listaTiposContato      = new ArrayList<>();

        calendarDataNascimento = Calendar.getInstance();
        calendarDataNascimento.add(Calendar.YEAR, -
                                   getResources().getInteger(R.integer.anos_para_tras));

        carregaTipos();

        registerForContextMenu(listViewContatos);

        editTextDataNascimento.setFocusable(false);
        editTextDataNascimento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /* Para fazer o DatePicker aparecer em modo Spinner e não Calendar
                   para SDKs iguais ou maiores que o API Level 21 foi utilizado um
                   estilo customizado, que está na pasta values-v21.

                   Versões anteriores já aparecem em modo Spinner por padrão.
                 */
                DatePickerDialog picker = new DatePickerDialog(PessoaActivity.this,
                        R.style.CustomDatePickerDialogTheme,
                        PessoaActivity.this,
                        calendarDataNascimento.get(Calendar.YEAR),
                        calendarDataNascimento.get(Calendar.MONTH),
                        calendarDataNascimento.get(Calendar.DAY_OF_MONTH));

                picker.show();
            }
        });

        if (modo == ALTERAR){

            setTitle(R.string.alterar_pessoa);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    int id = bundle.getInt(ID);

                    PessoasDatabase database = PessoasDatabase.getDatabase(PessoaActivity.this);

                    pessoa = database.pessoaDao().queryForId(id);

                    listaContatos = database.contatoDao().queryForPessoaId(pessoa.getId());

                    for (Contato c : listaContatos){

                        TipoContato tipoContato = getTipoContato(c.getTipoContatoId());

                        if (tipoContato == null){
                            tipoContato = database.tipoContatoDao().queryForId(c.getTipoContatoId());
                            listaTiposContato.add(tipoContato);
                        }

                        c.setTipoContato(tipoContato);
                    }

                    Collections.sort(listaContatos, Contato.comparador);

                    PessoaActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            editTextNome.setText(pessoa.getNome());

                            calendarDataNascimento.setTime(pessoa.getDataNascimento());

                            String textoData = UtilsDate.formatDate(PessoaActivity.this,
                                                                    pessoa.getDataNascimento());

                            editTextDataNascimento.setText(textoData);

                            int posicao = posicaoTipo(pessoa.getTipoId());
                            spinnerTipo.setSelection(posicao);

                            textoData = UtilsDate.formatDate(PessoaActivity.this,
                                                             pessoa.getDataCadastro());

                            editTextDataCadastro.setText(textoData);

                            criaAdapterListContatos();

                            UtilsGUI.setListViewHeightBasedOnChildren(listViewContatos,
                                                                      heightMinListViewContatos);
                        }
                    });
                }
            });

        }else{

            setTitle(R.string.nova_pessoa);

            pessoa = new Pessoa("");

            listaContatos = new ArrayList<>();

            criaAdapterListContatos();

            textViewDataCadastro.setVisibility(View.INVISIBLE);
            editTextDataCadastro.setVisibility(View.INVISIBLE);
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
                        ArrayAdapter<Tipo> spinnerAdapter = new ArrayAdapter<>(PessoaActivity.this,
                                android.R.layout.simple_list_item_1,
                                listaTipos);

                        spinnerTipo.setAdapter(spinnerAdapter);
                    }
                });
            }
        });
    }

    private TipoContato getTipoContato(int tipoId){

        for (TipoContato t : listaTiposContato){

            if (t.getId() == tipoId){
                return t;
            }
        }

        return null;
    }

    private Contato getContato(int tipoId){

        for (Contato c : listaContatos){

            if (c.getId() == tipoId){
                return c;
            }
        }

        return null;
    }

    private void criaAdapterListContatos(){

        listaAdapter = new ArrayAdapter<>(PessoaActivity.this,
                android.R.layout.simple_list_item_1,
                listaContatos);

        listViewContatos.setAdapter(listaAdapter);
    }

    private void salvar(){
        String nome  = UtilsGUI.validaCampoTexto(this,
                                                 editTextNome,
                                                 R.string.nome_vazio);
        if (nome == null){
            return;
        }

        String txtDataNascimento = UtilsGUI.validaCampoTexto(this,
                                                             editTextDataNascimento,
                                                             R.string.data_nascimento_vazia);
        if (txtDataNascimento == null){
            return;
        }

        int idade = UtilsDate.totalAnos(calendarDataNascimento);

        if (idade <= 0 || idade > 200){
            UtilsGUI.avisoErro(this,
                               getString(R.string.idade_invalida) + "\n" +
                               getResources().getQuantityString(R.plurals.idade, idade, idade));
            return;
        }

        pessoa.setNome(nome);
        pessoa.setDataNascimento(calendarDataNascimento.getTime());

        Tipo tipo = (Tipo) spinnerTipo.getSelectedItem();
        if (tipo != null){
            pessoa.setTipoId(tipo.getId());
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                PessoasDatabase database = PessoasDatabase.getDatabase(PessoaActivity.this);

                if (modo == NOVO) {

                    pessoa.setDataCadastro(new Date());

                    int novoId = (int) database.pessoaDao().insert(pessoa);

                    pessoa.setId(novoId);

                } else {

                    database.pessoaDao().update(pessoa);
                }

                for (Contato c : listaContatosRemovidos){

                    if (c.getId() != 0){
                        database.contatoDao().delete(c);
                    }
                }

                for (Contato c : listaContatos){

                    if (c.getPessoaId() != pessoa.getId()){
                        c.setPessoaId(pessoa.getId());
                    }

                    if (c.getId() == 0){
                        database.contatoDao().insert(c);
                    }else{
                        database.contatoDao().update(c);
                    }
                }

                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    public void novoContato(View view){
        ContatoActivity.novo(this, REQUEST_NOVO_CONTATO, listaContatos);
    }

    private void excluirContato(final Contato contato){

        String mensagem = getString(R.string.deseja_realmente_apagar)
                          + "\n" + contato.getValor();

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:

                                listaContatos.remove(contato);
                                listaContatosRemovidos.add(contato);

                                UtilsGUI.setListViewHeightBasedOnChildren(listViewContatos,
                                                                         heightMinListViewContatos);

                                listaAdapter.notifyDataSetChanged();

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

        UtilsGUI.confirmaAcao(this, mensagem, listener);
    }

    private void cancelar(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK){

            Bundle bundle = data.getExtras();

            int idContato     = bundle.getInt(ContatoActivity.ID_CONTATO);
            int idTipoContato = bundle.getInt(ContatoActivity.ID_TIPO_CONTATO);
            String valor      = bundle.getString(ContatoActivity.VALOR);

            Contato contato;

            if (requestCode == REQUEST_NOVO_CONTATO) {

                contato = new Contato();

                listaContatos.add(contato);

            } else {

                contato = getContato(idContato);
            }

            contato.setValor(valor);
            contato.setTipoContatoId(idTipoContato);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {

                    PessoasDatabase database = PessoasDatabase.getDatabase(PessoaActivity.this);

                    for (Contato c : listaContatos){

                        TipoContato tipoContato = getTipoContato(c.getTipoContatoId());

                        if (tipoContato == null){
                            tipoContato = database.tipoContatoDao().queryForId(c.getTipoContatoId());
                            listaTiposContato.add(tipoContato);
                        }

                        c.setTipoContato(tipoContato);
                    }

                    Collections.sort(listaContatos, Contato.comparador);

                    PessoaActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UtilsGUI.setListViewHeightBasedOnChildren(listViewContatos,
                                                                      heightMinListViewContatos);

                            listaAdapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        }
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.item_selecionado, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info;

        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Contato contato = (Contato) listViewContatos.getItemAtPosition(info.position);

        switch(item.getItemId()){

            case R.id.menuItemAbrir:
                ContatoActivity.alterar(this,
                        REQUEST_ALTERAR_CONTATO,
                        contato,
                        listaContatos);
                return true;

            case R.id.menuItemApagar:
                excluirContato(contato);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        calendarDataNascimento.set(year, month, dayOfMonth);

        String textoData = UtilsDate.formatDate(this, calendarDataNascimento.getTime());

        editTextDataNascimento.setText(textoData);
    }
}
