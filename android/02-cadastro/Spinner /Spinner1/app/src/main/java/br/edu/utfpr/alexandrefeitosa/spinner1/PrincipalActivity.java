package br.edu.utfpr.alexandrefeitosa.spinner1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {

    private Spinner spinnerLinguagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        spinnerLinguagens = findViewById(R.id.spinnerLinguagens);

        popularSpinner();
    }

    private void popularSpinner(){
        ArrayList<String> lista = new ArrayList<>();

        lista.add(getString(R.string.java));
        lista.add(getString(R.string.cobol));
        lista.add(getString(R.string.pascal));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        lista);

        spinnerLinguagens.setAdapter(adapter);
    }

    public void mostrarSelecionado(View view){

        String ling = (String) spinnerLinguagens.getSelectedItem();

        String mensagem;

        if (ling != null){
            mensagem = ling + getString(R.string.foi_selecionado);
        }else{
            mensagem = getString(R.string.nenhuma_linguagem_selecionada);
        }

        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}
