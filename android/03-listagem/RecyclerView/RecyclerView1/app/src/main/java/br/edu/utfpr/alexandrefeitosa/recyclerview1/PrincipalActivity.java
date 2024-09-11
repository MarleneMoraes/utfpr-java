package br.edu.utfpr.alexandrefeitosa.recyclerview1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.utfpr.alexandrefeitosa.utils.RecyclerItemClickListener;

public class PrincipalActivity extends AppCompatActivity {

    private RecyclerView               recyclerViewNomes;
    private RecyclerView.LayoutManager layoutManager;
    private PessoaAdapter              pessoaAdapter;

    private ArrayList<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        recyclerViewNomes = findViewById(R.id.recyclerViewPessoas);

        layoutManager = new LinearLayoutManager(this);

        recyclerViewNomes.setLayoutManager(layoutManager);
        recyclerViewNomes.setHasFixedSize(true);
        recyclerViewNomes.addItemDecoration(new DividerItemDecoration(this,
                                                                      LinearLayout.VERTICAL));

        popularLista();

        recyclerViewNomes.addOnItemTouchListener(

                new RecyclerItemClickListener(getApplicationContext(),
                                              recyclerViewNomes,
                                              new RecyclerItemClickListener.OnItemClickListener() {

                            @Override
                            public void onItemClick(View view, int position) {

                                Pessoa pessoa = pessoas.get(position);

                                Toast.makeText(getApplicationContext(),
                                        pessoa.getNome() + getString(R.string.recebeu_click),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                                Pessoa pessoa = pessoas.get(position);

                                Toast.makeText(getApplicationContext(),
                                        pessoa.getNome() + getString(R.string.recebeu_click_longo),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                ));
    }

    private void popularLista(){

        String[] nomes  = getResources().getStringArray(R.array.nomes);
        int[]    idades = getResources().getIntArray(R.array.idades);

        pessoas = new ArrayList<>();

        for (int cont = 0; cont < nomes.length; cont++){
            pessoas.add(new Pessoa(nomes[cont], idades[cont]));
        }

        pessoaAdapter = new PessoaAdapter(pessoas);

        recyclerViewNomes.setAdapter(pessoaAdapter);
    }
}
