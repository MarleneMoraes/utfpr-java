package br.edu.utfpr.alexandrefeitosa.listview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    private ListView listViewPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listViewPessoas = findViewById(R.id.listViewPessoas);

        popularLista();
    }

    private void popularLista(){

        String[] nomes  = getResources().getStringArray(R.array.nomes);
        int[]    idades = getResources().getIntArray(R.array.idades);

        ArrayList<Pessoa> pessoas = new ArrayList<>();

        for (int cont = 0; cont < nomes.length; cont++){
            pessoas.add(new Pessoa(nomes[cont],idades[cont]));
        }

        ArrayAdapter<Pessoa> adapter =
                new ArrayAdapter(this,
                                 android.R.layout.simple_list_item_1,
                                 pessoas);

        listViewPessoas.setAdapter(adapter);
    }
}
