package br.edu.utfpr.alexandrefeitosa.activity2x;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

    ActivityResultLauncher<Intent> launcherNovaPessoa = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),

            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK){

                        Intent intent = result.getData();

                        Bundle bundle = intent.getExtras();

                        if (bundle != null){

                            String nome = bundle.getString(PessoaActivity.NOME);

                            Pessoa pessoa = new Pessoa(nome);

                            listaPessoas.add(pessoa);

                            listaAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

    public void novaPessoa(View view){
       PessoaActivity.novaPessoa(this, launcherNovaPessoa);
    }

    public void sobre(View view){
        SobreActivity.nova(this);
    }
}