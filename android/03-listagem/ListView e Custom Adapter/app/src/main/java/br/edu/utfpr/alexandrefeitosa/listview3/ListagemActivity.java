package br.edu.utfpr.alexandrefeitosa.listview3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {

    private ListView          listViewPessoas;
    private ArrayList<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listViewPessoas = findViewById(R.id.listViewPessoas);

        listViewPessoas.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id) {

                        Pessoa pessoa = (Pessoa) listViewPessoas.getItemAtPosition(position);

                        Toast.makeText(getApplicationContext(),
                                getString(R.string.pessoa_nome) + pessoa.getNome() + getString(R.string.foi_clicada),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        popularLista();
    }

    private void popularLista(){

        String[] nomes         = getResources().getStringArray(R.array.nomes);
        String[] rendasMensais = getResources().getStringArray(R.array.rendas_mensais);
        int[]    tipos_posicao = getResources().getIntArray(R.array.tipos_posicao);
        int[]    brasileiros   = getResources().getIntArray(R.array.brasileiros);

        pessoas = new ArrayList<>();

        Pessoa  pessoa;
        float   rendaMensal;

        // Gera um Array com todos as Constantes Enum de Tipo, na ordem (posição) que foram declaradas
        Tipo[]  tipos = Tipo.values();

        for (int cont = 0; cont < nomes.length; cont++){

            pessoa = new Pessoa(nomes[cont]);

            rendaMensal = Float.parseFloat(rendasMensais[cont]);

            pessoa.setRendaMensal(rendaMensal);

            /* Através da posição que veio do xml (tipos_posicao[cont]) recupera-se do array tipos o objeto
               Enum correspondente a constante,

               Por exemplo, se tipos_posicao[cont] valer 0, significa que será recuperado do array tipos
               o objeto da posição 0, ou seja, o elemento Enum que representa a constante Aluno  */

            pessoa.setTipo(tipos[tipos_posicao[cont]]);

            if (brasileiros[cont] == 0){
                pessoa.setBrasileiro(false);
            }else{
                pessoa.setBrasileiro(true);
            }

            pessoas.add(pessoa);
        }

        PessoaAdapter pessoaAdapter = new PessoaAdapter(this, pessoas);

        listViewPessoas.setAdapter(pessoaAdapter);
    }
}