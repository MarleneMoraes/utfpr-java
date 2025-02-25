package br.edu.utfpr.alexandrefeitosa.room1;

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

import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room1.modelo.Pessoa;
import br.edu.utfpr.alexandrefeitosa.room1.persistencia.PessoasDatabase;
import br.edu.utfpr.alexandrefeitosa.room1.utils.UtilsGUI;

public class PessoasActivity extends AppCompatActivity {

    private static final int REQUEST_NOVA_PESSOA    = 1;
    private static final int REQUEST_ALTERAR_PESSOA = 2;

    private ListView             listViewPessoa;
    private ArrayAdapter<Pessoa> listaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoas);

        listViewPessoa = findViewById(R.id.listViewItens);

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

        PessoasDatabase database = PessoasDatabase.getDatabase(this);

        List<Pessoa> lista = database.pessoaDao().queryAll();

        listaAdapter = new ArrayAdapter<>(this,
                                          android.R.layout.simple_list_item_1,
                                          lista);

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
                                        PessoasDatabase.getDatabase(PessoasActivity.this);

                                database.pessoaDao().delete(pessoa);

                                listaAdapter.remove(pessoa);
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

            popularLista();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lista_pessoas, menu);
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

        getMenuInflater().inflate(R.menu.item_selecionado, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info;

        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Pessoa pessoa = (Pessoa) listViewPessoa.getItemAtPosition(info.position);

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
