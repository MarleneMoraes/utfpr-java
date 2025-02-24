package br.edu.utfpr.alexandrefeitosa.menucontextoflutuante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class PrincipalActivity extends AppCompatActivity {

    private ListView    listViewNomes;
    private EditText    editTextNome;
    private ImageButton imageButton1, imageButton2;

    private ArrayList<String>    lista;
    private ArrayAdapter<String> adapter;

    private int posicaoAlteracao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listViewNomes = findViewById(R.id.listViewNomes);
        editTextNome  = findViewById(R.id.editTextNome);
        imageButton1  = findViewById(R.id.imageButton1);
        imageButton2  = findViewById(R.id.imageButton2);

        imageButton2.setVisibility(View.INVISIBLE);

        popularLista();

        registerForContextMenu(listViewNomes);
    }

    private void popularLista(){

        lista = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,
                                     android.R.layout.simple_list_item_1,
                                     lista);

        listViewNomes.setAdapter(adapter);
    }

    public void adicionar(View view){

        String frase = editTextNome.getText().toString();

        if (frase.isEmpty()){
            return;
        }

        editTextNome.setText(null);

        if (posicaoAlteracao == -1){
            lista.add(frase);
        }else{
            lista.remove(posicaoAlteracao);
            lista.add(posicaoAlteracao, frase);

            imageButton1.setImageResource(android.R.drawable.ic_input_add);
            imageButton2.setVisibility(View.INVISIBLE);

            posicaoAlteracao = -1;

            listViewNomes.setEnabled(true);
        }

        Collections.sort(lista);

        adapter.notifyDataSetChanged();
    }

    public void cancelar(View view){

        editTextNome.setText(null);

        imageButton1.setImageResource(android.R.drawable.ic_input_add);
        imageButton2.setVisibility(View.INVISIBLE);

        listViewNomes.setEnabled(true);

        posicaoAlteracao = -1;
    }

    private void alterar(int posicao){

        String frase = lista.get(posicao);

        editTextNome.setText(frase);
        editTextNome.setSelection(editTextNome.getText().length());

        imageButton1.setImageResource(android.R.drawable.ic_menu_save);
        imageButton2.setVisibility(View.VISIBLE);

        listViewNomes.setEnabled(false);

        posicaoAlteracao = posicao;
    }

    private void excluir(int posicao){

        lista.remove(posicao);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.principal_menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info;

        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch(item.getItemId()){

            case R.id.menuItemAlterar:
                alterar(info.position);
                return true;

            case R.id.menuItemExcluir:
                excluir(info.position);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
