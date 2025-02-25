package br.edu.utfpr.alexandrefeitosa.sqlite;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.edu.utfpr.alexandrefeitosa.sqlite.modelo.Pessoa;
import br.edu.utfpr.alexandrefeitosa.sqlite.persistencia.PessoasDatabase;
import br.edu.utfpr.alexandrefeitosa.sqlite.utils.UtilsGUI;

public class PessoasActivity extends AppCompatActivity {

    private static final int REQUEST_NOVA_PESSOA    = 1;
    private static final int REQUEST_ALTERAR_PESSOA = 2;

    private ListView             listViewPessoa;
    private ArrayAdapter<Pessoa> listaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        PessoasDatabase database = PessoasDatabase.getInstance(this);
        database.pessoaDAO.carregarTudo();

        listViewPessoa = findViewById(R.id.listViewPessoas);

        listViewPessoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Pessoa pessoa = (Pessoa) parent.getItemAtPosition(position);

                PessoaActivity.alterar(PessoasActivity.this,
                                       REQUEST_ALTERAR_PESSOA,
                                       pessoa);
            }
        });

        popularLista();

        registerForContextMenu(listViewPessoa);
    }

    private void popularLista(){

        PessoasDatabase database = PessoasDatabase.getInstance(this);

        listaAdapter = new ArrayAdapter<>(this,
                                          android.R.layout.simple_list_item_1,
                                          database.pessoaDAO.lista);

        listViewPessoa.setAdapter(listaAdapter);
    }

    private void excluirPessoa(final Pessoa pessoa){

        String mensagem = getString(R.string.deseja_realmente_apagar) + "\n" + pessoa.getNome();

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:

                                PessoasDatabase database =
                                        PessoasDatabase.getInstance(PessoasActivity.this);

                                database.pessoaDAO.apagar(pessoa);

                                listaAdapter.notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

        UtilsGUI.confirmaAcao(this, mensagem, listener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((requestCode == REQUEST_NOVA_PESSOA || requestCode == REQUEST_ALTERAR_PESSOA) &&
             resultCode == Activity.RESULT_OK){

            listaAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuItemNovo:
                PessoaActivity.nova(this, REQUEST_NOVA_PESSOA);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.pessoa_selecionada, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info;

        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Pessoa pessoa = listaAdapter.getItem(info.position);

        switch(item.getItemId()){

            case R.id.menuItemAbrir:
                PessoaActivity.alterar(this,
                                       REQUEST_ALTERAR_PESSOA,
                                       pessoa);
                return true;

            case R.id.menuItemApagar:
                excluirPessoa(pessoa);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
