package br.edu.utfpr.alexandrefeitosa.room3;

import android.app.Activity;
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
import android.widget.ListView;

import java.util.List;

import br.edu.utfpr.alexandrefeitosa.room3.modelo.Contato;
import br.edu.utfpr.alexandrefeitosa.room3.modelo.TipoContato;
import br.edu.utfpr.alexandrefeitosa.room3.persistencia.PessoasDatabase;
import br.edu.utfpr.alexandrefeitosa.room3.utils.UtilsGUI;

public class TiposContatoActivity extends AppCompatActivity {

    private static final int REQUEST_NOVO_TIPO    = 1;
    private static final int REQUEST_ALTERAR_TIPO = 2;

    private ListView                  listViewTipos;
    private ArrayAdapter<TipoContato> listaAdapter;
    private List<TipoContato>         lista;

    public static void abrir(Activity activity){

        Intent intent = new Intent(activity, TiposContatoActivity.class);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        listViewTipos = findViewById(R.id.listViewItens);

        listViewTipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TipoContato tipoContato = (TipoContato) parent.getItemAtPosition(position);

                TipoContatoActivity.alterar(TiposContatoActivity.this,
                                            REQUEST_ALTERAR_TIPO,
                                            tipoContato);
            }
        });

        carregaTiposContato();

        registerForContextMenu(listViewTipos);

        setTitle(R.string.tipos_contato);
    }

    private void carregaTiposContato(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                PessoasDatabase database = PessoasDatabase.getDatabase(TiposContatoActivity.this);

                lista = database.tipoContatoDao().queryAll();

                TiposContatoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listaAdapter = new ArrayAdapter<>(TiposContatoActivity.this,
                                                          android.R.layout.simple_list_item_1,
                                                          lista);

                        listViewTipos.setAdapter(listaAdapter);
                    }
                });
            }
        });
    }

    private void verificaUsoTipoContato(final TipoContato tipoContato){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                PessoasDatabase database = PessoasDatabase.getDatabase(TiposContatoActivity.this);

                List<Contato> lista = database.contatoDao().queryForTipoContatoId(tipoContato.getId());

                if (lista != null && lista.size() > 0){

                    TiposContatoActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UtilsGUI.avisoErro(TiposContatoActivity.this, R.string.tipo_usado);
                        }
                    });

                    return;
                }

                TiposContatoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        excluirTipoContato(tipoContato);
                    }
                });
            }
        });
    }

    private void excluirTipoContato(final TipoContato tipoContato){

        String mensagem = getString(R.string.deseja_realmente_apagar) +
                                    "\n" + tipoContato.getDescricao();

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:

                                AsyncTask.execute(new Runnable() {
                                    @Override
                                    public void run() {

                                        PessoasDatabase database =
                                                PessoasDatabase.getDatabase(TiposContatoActivity.this);

                                        database.tipoContatoDao().delete(tipoContato);

                                        TiposContatoActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                listaAdapter.remove(tipoContato);
                                            }
                                        });
                                    }
                                });

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

        if ((requestCode == REQUEST_NOVO_TIPO || requestCode == REQUEST_ALTERAR_TIPO)
             && resultCode == Activity.RESULT_OK){

            carregaTiposContato();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lista_tipos, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.menuItemNovo:
                TipoContatoActivity.novo(this, REQUEST_NOVO_TIPO);
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

        TipoContato tipoContato = (TipoContato) listViewTipos.getItemAtPosition(info.position);

        switch(item.getItemId()){

            case R.id.menuItemAbrir:
                TipoContatoActivity.alterar(this,
                                            REQUEST_ALTERAR_TIPO,
                                            tipoContato);
                return true;

            case R.id.menuItemApagar:
                verificaUsoTipoContato(tipoContato);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
