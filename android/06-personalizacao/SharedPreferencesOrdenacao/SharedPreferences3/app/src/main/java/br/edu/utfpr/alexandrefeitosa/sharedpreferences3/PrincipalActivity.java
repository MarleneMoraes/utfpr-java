package br.edu.utfpr.alexandrefeitosa.sharedpreferences3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.ArrayList;
import java.util.Collections;

public class PrincipalActivity extends AppCompatActivity {

    private ListView listViewPessoas;

    private ArrayAdapter<Pessoa> listaAdapter;

    private ArrayList<Pessoa> listaPessoas;

    private ActionMode actionMode;

    private View viewSelecionada;

    private int posicaoSelecionada = -1;

    public static final String ARQUIVO = "br.edu.utfpr.alexandrefeitosa.sharedpreferences3.PREFERENCIAIS";

    public static final String ORDENACAO_ASCENDENTE = "ORDENACAO_ASCENDENTE";

    private boolean ordenacaoAscendente = true;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.principal_item_selecionado, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            int idMenuItem = item.getItemId();

            if (idMenuItem == R.id.menuItemEditar){
                editarPessoa();
                mode.finish();
                return true;
            }else
                if (idMenuItem == R.id.menuItemExcluir){
                    excluirPessoa();
                    mode.finish();
                    return true;
                }else{
                    return false;
                }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            if (viewSelecionada != null){
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }

            actionMode = null;
            viewSelecionada = null;

            listViewPessoas.setEnabled(true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        setTitle(getString(R.string.controle_de_pessoas));

        listViewPessoas = findViewById(R.id.listViewPessoas);

        listViewPessoas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listViewPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionada = position;
                editarPessoa();
            }
        });

        listViewPessoas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view,
                                           int position,
                                           long id) {

                if (actionMode != null){
                    return false;
                }

                posicaoSelecionada = position;

                view.setBackgroundColor(Color.LTGRAY);

                viewSelecionada = view;

                listViewPessoas.setEnabled(false);

                actionMode = startSupportActionMode(mActionModeCallback);

                return false;
            }
        });

        lerPreferenciaOrdenacaoAscendente();

        popularLista();
    }

    private void popularLista(){

        listaPessoas = new ArrayList<>();

        listaAdapter = new ArrayAdapter<>(this,
                                          android.R.layout.simple_list_item_1,
                                          listaPessoas);

        listViewPessoas.setAdapter(listaAdapter);
    }

    private void excluirPessoa(){
        listaPessoas.remove(posicaoSelecionada);
        listaAdapter.notifyDataSetChanged();
    }

    ActivityResultLauncher<Intent> launcherEditarPessoa = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),

            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK){

                        Intent intent = result.getData();

                        Bundle bundle = intent.getExtras();

                        if (bundle != null){

                            String nome = bundle.getString(PessoaActivity.NOME);
                            int    tipo = bundle.getInt(PessoaActivity.TIPO);

                            Pessoa pessoa = listaPessoas.get(posicaoSelecionada);
                            pessoa.setNome(nome);
                            pessoa.setTipo(tipo);

                            posicaoSelecionada = -1;

                            ordenarLista();
                        }
                    }
                }
            });

    private void editarPessoa(){

        Pessoa pessoa = listaPessoas.get(posicaoSelecionada);

        PessoaActivity.editarPessoa(this, launcherEditarPessoa, pessoa);
    }

    ActivityResultLauncher<Intent> launcherNovaPessoa = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),

            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK){

                        Intent intent = result.getData();

                        Bundle bundle = intent.getExtras();

                        if (bundle != null){

                            String nome = bundle.getString(PessoaActivity.NOME);
                            int tipo    = bundle.getInt(PessoaActivity.TIPO);

                            Pessoa pessoa = new Pessoa(nome, tipo);

                            listaPessoas.add(pessoa);

                            ordenarLista();
                        }
                    }
                }
            });

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem menuItemOrdenacao = menu.findItem(R.id.menuItemOrdenacao);

        atualizarIconeOrdenacao(menuItemOrdenacao);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idMenuItem = item.getItemId();

        if (idMenuItem == R.id.menuItemAdicionar){
            PessoaActivity.novaPessoa(this, launcherNovaPessoa);
            return true;
        }else
            if (idMenuItem == R.id.menuItemOrdenacao){
                salvarPreferenciaOrdenacaoAscendente(!ordenacaoAscendente);
                atualizarIconeOrdenacao(item);
                ordenarLista();
                return true;
            }else
                if (idMenuItem == R.id.menuItemSobre){
                    SobreActivity.nova(this);
                    return true;
                }else{
                    return super.onOptionsItemSelected(item);
                }
    }

    private void atualizarIconeOrdenacao(MenuItem menuItemOrdenacao){

        if (ordenacaoAscendente){
            menuItemOrdenacao.setIcon(R.drawable.ic_action_ordenacao_ascendente);
        }else{
            menuItemOrdenacao.setIcon(R.drawable.ic_action_ordenacao_descendente);
        }
    }

    private void ordenarLista(){

        if (ordenacaoAscendente){
            Collections.sort(listaPessoas, Pessoa.ordenacaoCrescente);
        }else{
            Collections.sort(listaPessoas, Pessoa.ordenacaoDecrescente);
        }

        listaAdapter.notifyDataSetChanged();
    }

    private void lerPreferenciaOrdenacaoAscendente(){

        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        ordenacaoAscendente = shared.getBoolean(ORDENACAO_ASCENDENTE, ordenacaoAscendente);
    }

    private void salvarPreferenciaOrdenacaoAscendente(boolean novoValor){

        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();

        editor.putBoolean(ORDENACAO_ASCENDENTE, novoValor);

        editor.commit();

        ordenacaoAscendente = novoValor;
    }
}