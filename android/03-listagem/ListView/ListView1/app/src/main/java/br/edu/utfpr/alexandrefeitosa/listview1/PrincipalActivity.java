package br.edu.utfpr.alexandrefeitosa.listview1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    private ListView listViewNomes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listViewNomes = findViewById(R.id.listViewNomes);

        listViewNomes.setOnItemClickListener(

                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view,
                                            int position,
                                            long id) {

                        String nome = (String) listViewNomes.getItemAtPosition(position);

                        Toast.makeText(getApplicationContext(),
                                nome + getString(R.string.foi_clicado),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        popularLista();
    }

    private void popularLista(){
        ArrayList<String> nomes = new ArrayList<>();

        nomes.add(getString(R.string.joao));
        nomes.add(getString(R.string.maria));
        nomes.add(getString(R.string.pedro));
        nomes.add(getString(R.string.saulo));
        nomes.add(getString(R.string.lucas));
        nomes.add(getString(R.string.silva));
        nomes.add(getString(R.string.joana));
        nomes.add(getString(R.string.alexandre));

        ArrayAdapter<String> adapter =
                new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1,
                        nomes);

        listViewNomes.setAdapter(adapter);
    }
}
