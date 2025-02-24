package br.edu.utfpr.alexandrefeitosa.activity2s;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    private ListView listViewPessoas;

    private ArrayAdapter<Pessoa> listaAdapter;

    private ArrayList<Pessoa> listaPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listViewPessoas = findViewById(R.id.listViewPessoas);

        popularLista();
    }

    private void popularLista(){

        listaPessoas = new ArrayList<>();

        listaAdapter = new ArrayAdapter<>(this,
                                          android.R.layout.simple_list_item_1,
                                          listaPessoas);

        listViewPessoas.setAdapter(listaAdapter);
    }

    public void novaPessoa(View view){
       PessoaActivity.novaPessoa(this);
    }

    public void sobre(View view){
        SobreActivity.nova(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){

            Bundle bundle = data.getExtras();

            if (bundle != null){

                String nome = bundle.getString(PessoaActivity.NOME);

                Pessoa pessoa = new Pessoa(nome);

                listaPessoas.add(pessoa);

                listaAdapter.notifyDataSetChanged();
            }
        }
    }
}